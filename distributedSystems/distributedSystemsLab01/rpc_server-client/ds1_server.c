/*
   ============================================================================
   Name        : RPC_Server.c
   Author      : Haris Razis - cs161072
   Version     : 1.0.0
   Description : Distributed Systems Lab01
   ============================================================================
 */
#include "ds1.h"

float*
case1_1_svc(average *argp, struct svc_req *rqstp) {

								static int sum;
								static float result;

								xdr_free((xdrproc_t)xdr_float, (char *)&result);

								//Average of array y
								for (int i = 0; i < argp->y.y_len; ++i) {
																sum += argp->y.y_val[i];
								}

								result = (float) sum / argp->y.y_len;
								return &result;
}

minmax*
case2_1_svc(minmax *argp, struct svc_req *rqstp) {

								static int min, max;
								static minmax result;

								//Min and Max of array y
								min = argp->y.y_val[0];
								max = argp->y.y_val[1];

								for (int i = 0; i < argp->y.y_len; ++i) {
																if (argp->y.y_val[i] < min) {
																								min = argp->y.y_val[i];
																}

																if (argp->y.y_val[i] > max) {
																								max = argp->y.y_val[i];
																}
								}

								result.y.y_len = 2;
								result.y.y_val = (int*) malloc(2 * sizeof(int));
								result.y.y_val[0] = min;
								result.y.y_val[1] = max;
								return &result;
}

product*
case3_1_svc(product *argp, struct svc_req *rqstp) {

								static product result;

								result.x.x_len = argp->y.y_len;
								result.x.x_val = (float*) malloc(result.x.x_len * sizeof(float));

								for (int i = 0; i < argp->y.y_len; ++i) {
																result.x.x_val[i] = (float) argp->y.y_val[i] * argp->x.x_val[0];
								}

								return &result;
}
