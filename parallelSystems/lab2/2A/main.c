#pragma clang diagnostic push
#pragma ide diagnostic ignored "openmp-use-default-none"

#include <stdio.h>
#include <malloc.h>
#include <omp.h>
#include <stdlib.h>
#include "omp.h"

void merge(int *array, const int *arraySize, int *tmp, const int *tmpSize, int *result) {
    int i = 0;

    while (array <= arraySize && tmp <= tmpSize) {
        if ((*array) < (*tmp)) {
            result[i++] = *array;
            array++;
        } else {
            result[i++] = *tmp;
            tmp++;
        }
    }

    //finishing up the lower half
    while (array <= arraySize) {
        result[i++] = *array;
        array++;
    }

    //finishing up the upper half
    while (tmp <= tmpSize) {
        result[i++] = *tmp;
        tmp++;
    }
}

int compareFunction(const void *a, const void *b) {
    return (*(int *) a - *(int *) b);
}

void multisort(int *array, int *space, int arraySize) {
    // If there are not enough elements, use quicksort
    if (arraySize < 4) {
        qsort(array, arraySize, sizeof(int), compareFunction);
        return;
    }

    int quarter = arraySize / 4;

    int *startA = array;
    int *spaceA = space;

    int *startB = startA + quarter;
    int *spaceB = spaceA + quarter;

    int *startC = startB + quarter;
    int *spaceC = spaceB + quarter;

    int *startD = startC + quarter;
    int *spaceD = spaceC + quarter;

#pragma omp task
    multisort(startA, spaceA, quarter);
#pragma omp task
    multisort(startB, spaceB, quarter);
#pragma omp task
    multisort(startC, spaceC, quarter);
#pragma omp task
    multisort(startD, spaceD, arraySize - 3 * quarter);

#pragma omp taskwait

#pragma omp task
    merge(startA, startA + quarter - 1, startB, startB + quarter - 1, spaceA);

#pragma omp task
    merge(startC, startC + quarter - 1, startD, array + arraySize - 1, spaceC);

#pragma omp taskwait

    merge(spaceA, spaceC - 1, spaceC, spaceA + arraySize - 1, startA);
}

int main() {
    int i, arraySize, numThreads;
    int *array, *space;
    double start, end, timeInMilliseconds;

    printf("-------------------------------\n");
    printf("Parallel Systems - Assignment 2A\n");
    printf("-------------------------------\n\n");

    printf("What array size fits you? N=");
    scanf("%d", &arraySize);

    array = (int *) malloc(arraySize * sizeof(int));
    space = (int *) malloc(arraySize * sizeof(int));

    printf("\nEnter number of threads for OpenMP: ");
    scanf("%d", &numThreads);

    printf("\nSetting %d threads", numThreads);
    omp_set_num_threads(numThreads);

    for (i = 0; i < arraySize; i++) {
        printf("\nGive element [%d]=", i);
        scanf("%d", &array[i]);
    }

    // Measure performance
    start = omp_get_wtime();

#pragma omp parallel
#pragma omp single
    multisort(array, space, arraySize);

    end = omp_get_wtime();
    timeInMilliseconds = (end - start) * 1000;

    printf("\nArray after multisort: [ ");
    for (i = 0; i < arraySize; i++) printf("%d ", array[i]);
    printf("]\n");

    printf("\n-----------------------------------------\n");
    printf("Functions executed in %.4f milliseconds\n", timeInMilliseconds);
    printf("-----------------------------------------\n");

    FILE *f = fopen("results.txt", "a");
    if (f == NULL) {
        printf("Error opening file!\n");
        exit(1);
    }

    fprintf(f, "N: %d\t Threads: %d\t Time: %.4f ms\n", arraySize, numThreads, timeInMilliseconds);

    fclose(f);
    free(array);

    return 0;
}

#pragma clang diagnostic pop