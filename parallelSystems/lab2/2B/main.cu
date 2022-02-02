
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/time.h>
#include <cuda_profiler_api.h>
#include "book.h"

// Problem size
#define NI 4096
#define NJ 4096
#define blockSize 32

__global__ void convolution(const double *A, double *B) {
    int i, j;
    double c11, c12, c13, c21, c22, c23, c31, c32, c33;

    c11 = +0.2;
    c21 = +0.5;
    c31 = -0.8;
    c12 = -0.3;
    c22 = +0.6;
    c32 = -0.9;
    c13 = +0.4;
    c23 = +0.7;
    c33 = +0.10;

    // Idiomatic CUDA
    int row = blockIdx.x * blockDim.x + threadIdx.x;
    int col = blockIdx.y * blockDim.y + threadIdx.y;
    int stepRow = blockDim.x * gridDim.x;
    int stepCol = blockDim.y * gridDim.y;

    for (i = row; i < NI; i += stepRow) {
        for (j = col; j < NJ; j += stepCol) {
            B[i * NJ + j] =
                    c11 * A[(i - 1) * NJ + (j - 1)] + c12 * A[(i + 0) * NJ + (j - 1)] +
                    c13 * A[(i + 1) * NJ + (j - 1)] + c21 * A[(i - 1) * NJ + (j + 0)] +
                    c22 * A[(i + 0) * NJ + (j + 0)] + c23 * A[(i + 1) * NJ + (j + 0)] +
                    c31 * A[(i - 1) * NJ + (j + 1)] + c32 * A[(i + 0) * NJ + (j + 1)] +
                    c33 * A[(i + 1) * NJ + (j + 1)];
        }
    }
}

// Definitely not the fastest way. % operator is very slow and highly divergent
// warps are very inefficient (see https://developer.download.nvidia.com/assets/cuda/files/reduction.pdf)
__global__ void findMaxInDiagonal(double *B, double *max) {
    // Idiomatic CUDA
    int col = blockIdx.x * blockDim.x + threadIdx.x;
    int row = blockIdx.y * blockDim.y + threadIdx.y;
    int c_t = threadIdx.x;
    int r_t = threadIdx.y;

    // 2D -> 1D index
    int pos_1D = row * NJ + col;
    int pos_1D_t = r_t * blockDim.x + c_t;

    // Shared memory
    extern __shared__ double shared[];
    shared[pos_1D_t] = B[pos_1D];

    // Do reduction in shared memory
    __syncthreads();
    for (int s = (blockDim.x * blockDim.y) / 2; s > 0; s >>= 1) {
        if (pos_1D_t < s)
            shared[pos_1D_t] = fmax(shared[pos_1D_t], shared[pos_1D_t + s]);
        __syncthreads();
    }

    if (r_t == 0 && c_t == 0)
        max[blockIdx.y * gridDim.x + blockIdx.x] = shared[0];
}

void init(double *A) {
    int i, j;

    for (i = 0; i < NI; ++i) {
        for (j = 0; j < NJ; ++j) {
            A[i * NJ + j] = (double) rand() / RAND_MAX;
        }
    }
}

int main() {
    double *local_A, *A, *local_B, *B, *local_max, *max;
    struct timeval cpu_start{}, cpu_end{};
    int size = NI * NJ * sizeof(double);

    gettimeofday(&cpu_start, nullptr);

    // Allocate CPU memory
    local_A = (double *) malloc(size);
    local_B = (double *) malloc(size);
    local_max = (double *) malloc(sizeof(double));

    // Allocate GPU memory - Unified memory won't work with older GPU architectures, see requirements ->
    // https://docs.nvidia.com/cuda/cuda-c-programming-guide/index.html#um-requirements
    HANDLE_ERROR(cudaMalloc((void **) &A, size));
    HANDLE_ERROR(cudaMalloc((void **) &B, size));
    HANDLE_ERROR(cudaMalloc((void **) &max, sizeof(double)));

    // Initialize local_A on the host
    init(local_A);

    // Copy memory from host to device
    HANDLE_ERROR(cudaMemcpy(A, local_A, size, cudaMemcpyHostToDevice));
    HANDLE_ERROR(cudaMemcpy(B, local_B, size, cudaMemcpyHostToDevice));

    // Setup execution parameters
    dim3 threads(blockSize, blockSize);
    dim3 grid(NI / threads.x, NJ / threads.y);

    // Invoke the kernel
    convolution<<<1, threads>>>(A, B);

    // Copy memory from host to device
    HANDLE_ERROR(cudaMemcpy(max, local_max, sizeof(double), cudaMemcpyHostToDevice));

    // Invoke the kernel
    findMaxInDiagonal<<<1, 2, blockSize * blockSize * sizeof(double)>>>(B, max);

    // Copy max back from the GPU to the CPU
    HANDLE_ERROR(cudaMemcpy(local_B, B, size, cudaMemcpyDeviceToHost));
    HANDLE_ERROR(cudaMemcpy(local_max, max, sizeof(double), cudaMemcpyDeviceToHost));

    cudaDeviceSynchronize();

    gettimeofday(&cpu_end, nullptr);

    fprintf(stdout, "CPU Runtime: %0.6lfs\n",
            ((cpu_end.tv_sec - cpu_start.tv_sec) * 1000000.0 + (cpu_end.tv_usec - cpu_start.tv_usec)) / 1000000.0);

    printf("Max:\t%f\t%f\n", *local_max, local_B[0]);

    // Free memory
    free(local_A);
    free(local_B);
    free(local_max);
    cudaFree(A);
    cudaFree(B);
    cudaFree(max);

    return 0;
}

// /usr/local/cuda-11.5/bin/nvcc -arch=compute_50 -code=sm_50 ./main.cu -o main && /usr/local/cuda-11.5/bin/nvprof ./main