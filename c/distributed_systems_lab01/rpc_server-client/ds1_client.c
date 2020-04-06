/*
 ============================================================================
 Name        : server.c
 Author      : Haris Razis - cs161072
 Version     : 1.0.0
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include "ds1.h"

void error(char *msg) {
	perror(msg);
	exit(1);
}

void distributed_systems_1(char *host, char *port, int newsockfd) {

	int n1, n2, n3, n4, min, max, mm_array[2], user_choice, array_length, done;
	float a, avg = 0.0, sum = 0.0;
	int *y;
	float *ret_array;

	CLIENT *clnt;
	float *result_1;
	average case1_1_arg;
	minmax *result_2;
	minmax case2_1_arg;
	product *result_3;
	product case3_1_arg;

#ifndef	DEBUG
	clnt = clnt_create(host, DISTRIBUTED_SYSTEMS, DS_V1, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror(host);
		exit(1);
	}
#endif	/* DEBUG */

	done = 0;
	do {

		// Get user_choice for calculation
		n1 = recv(newsockfd, &user_choice, sizeof(int), 0);

		//Get array_length
		n2 = recv(newsockfd, &array_length, sizeof(int), 0);

		//Initialize array
		y = (int*) malloc(array_length * sizeof(int));

		//Get array
		n3 = recv(newsockfd, y, array_length * sizeof(int), 0);
		//Depending on user_choice run respective calculations
		switch (user_choice) {
		case 1:

			case1_1_arg.y.y_len = array_length;
			case1_1_arg.y.y_val = (int*) malloc(2 * sizeof(int));

			for (int i = 0; i < array_length; i++) {
				case1_1_arg.y.y_val[i] = y[i];
			}

			result_1 = case1_1(&case1_1_arg, clnt);
			if (result_1 == (float*) NULL) {
				clnt_perror(clnt, "call failed");
			} else {
				printf("%f\n", *result_1);
				avg = *result_1;
				send(newsockfd, &avg, sizeof(float), 0);
			}

			break;
		case 2:

			case2_1_arg.y.y_len = array_length;
			case2_1_arg.y.y_val = (int*) malloc(2 * sizeof(int));

			for (int i = 0; i < array_length; i++) {
				case2_1_arg.y.y_val[i] = y[i];
			}

			result_2 = case2_1(&case2_1_arg, clnt);
			if (result_2 == (minmax*) NULL) {
				clnt_perror(clnt, "call failed");
			} else {
				mm_array[0] = result_2->y.y_val[0];
				mm_array[1] = result_2->y.y_val[1];

				send(newsockfd, mm_array, sizeof(mm_array), 0);
			}

			break;
		case 3:
			//Product of a*y
			n4 = recv(newsockfd, &a, sizeof(float), 0);
			ret_array = (float*) malloc(array_length * sizeof(float));

			case3_1_arg.a = a;
			case3_1_arg.y.y_len = array_length;
			case3_1_arg.y.y_val = (int*) malloc(2 * sizeof(int));

			for (int i = 0; i < array_length; i++) {
				case3_1_arg.y.y_val[i] = y[i];
			}

			result_3 = case3_1(&case3_1_arg, clnt);
			if (result_3 == (product*) NULL) {
				clnt_perror(clnt, "call failed");
			} else {
				for (int i = 0; i < array_length; i++) {
					ret_array[i] = result_3->x.x_val[i];
				}
				send(newsockfd, ret_array, sizeof(ret_array), 0);
			}

			break;
		default:
			printf("Something went wrong!");
			done = 1;
			break;
		}

	} while (!done);

#ifndef	DEBUG
	//clnt_destroy(clnt);
#endif	 /* DEBUG */
}

int main(int argc, char *argv[]) {

	char *host, *port;
	int sockfd, newsockfd, portno, clilen;

	if (argc < 2) {
		printf("usage: %s server_host\n", argv[0]);
		exit(1);
	}

	host = argv[1];
	port = argv[2];

	struct sockaddr_in serv_addr, cli_addr;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("ERROR opening socket");

	bzero((char*) &serv_addr, sizeof(serv_addr));

	portno = atoi(port);
	printf("%d\n", portno);

	serv_addr.sin_family = AF_INET;

	serv_addr.sin_port = htons(portno);

	serv_addr.sin_addr.s_addr = INADDR_ANY;

	if (bind(sockfd, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) < 0)
		error("ERROR on binding");

	listen(sockfd, 5);

	for (;;) {
		printf("Waiting for a connection...\n");
		clilen = sizeof(cli_addr);
		newsockfd = accept(sockfd, (struct sockaddr*) &cli_addr, &clilen);
		if (newsockfd < 0)
			error("ERROR on accept");

		if (fork() == 0) {
			close(sockfd);
			printf("Connected.\n");
		}

		distributed_systems_1(host, port, newsockfd);

		close(newsockfd);
		exit(0);
	}
}
