/*
 ============================================================================
 Name        : RPC_Server.c
 Author      : Haris Razis - cs161072
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include "ds1.h"

float*
case1_1_svc(average *argp, struct svc_req *rqstp) {

	static int sum = 0;
	static float avg = 0;

	//Average of array y
	for (int i = 0; i < argp->y.y_len; ++i) {
		sum += argp->y.y_val[i];
	}

	avg = sum / (float) argp->y.y_len;

	return &avg;
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
		result.x.x_val[i] = (float) argp->y.y_val[i] * argp->a;
	}

	return &result;
}
