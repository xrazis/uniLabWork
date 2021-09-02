/*
   ============================================================================
   Name        : server.c
   Author      : Haris Razis - cs161072
   Version     : 1.0.0
   Description : Distributed Systems Lab01
   ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <netinet/in.h>
#include "ds1.h"

void error(char *msg) {
								perror(msg);
								exit(1);
}

void distributed_systems_1_average(char *host, char *port, int newsockfd) {

								int n2, n3, array_length;
								float avg = 0.0;
								int *y;

								CLIENT *clnt;
								float *result_1;
								average case1_1_arg;


								clnt = clnt_create(host, DISTRIBUTED_SYSTEMS, DS_V1, "udp");
								if (clnt == NULL) {
																clnt_pcreateerror(host);
																exit(1);
								}

								//Get array_length
								n2 = recv(newsockfd, &array_length, sizeof(int), 0);
								//Initialize array
								y = (int*) malloc(array_length * sizeof(int));
								//Get array
								n3 = recv(newsockfd, y, array_length * sizeof(int), 0);

								case1_1_arg.y.y_len = array_length;
								case1_1_arg.y.y_val = (int*) malloc(2 * sizeof(int));

								for (int i = 0; i < array_length; i++) {
																case1_1_arg.y.y_val[i] = y[i];
								}

								result_1 = case1_1(&case1_1_arg, clnt);
								if (result_1 == (float*) NULL) {
																clnt_perror(clnt, "call failed");
								} else {
																avg = *result_1;
																send(newsockfd, &avg, sizeof(float), 0);
								}

								free(y);
								// xdr_free((xdrproc_t)xdr_float, (char *)&result_1);
								clnt_destroy(clnt);
}

void distributed_systems_1_minmax(char *host, char *port, int newsockfd) {

								int n2, n3, n4, min, max, mm_array[2], array_length;
								int *y;

								CLIENT *clnt;
								minmax *result_2;
								minmax case2_1_arg;

								clnt = clnt_create(host, DISTRIBUTED_SYSTEMS, DS_V1, "udp");
								if (clnt == NULL) {
																clnt_pcreateerror(host);
																exit(1);
								}
								//Get array_length
								n2 = recv(newsockfd, &array_length, sizeof(int), 0);
								//Initialize array
								y = (int*) malloc(array_length * sizeof(int));
								//Get array
								n3 = recv(newsockfd, y, array_length * sizeof(int), 0);

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

								free(y);
								clnt_destroy(clnt);
}

void distributed_systems_1_product(char *host, char *port, int newsockfd) {

								int n2, n3, n4, user_choice, array_length;
								int *y;
								float a;
								float *ret_array;

								CLIENT *clnt;
								product *result_3;
								product case3_1_arg;

								clnt = clnt_create(host, DISTRIBUTED_SYSTEMS, DS_V1, "udp");
								if (clnt == NULL) {
																clnt_pcreateerror(host);
																exit(1);
								}
								//Get array_length
								n2 = recv(newsockfd, &array_length, sizeof(int), 0);
								//Initialize array
								y = (int*) malloc(array_length * sizeof(int));
								//Get array
								n3 = recv(newsockfd, y, array_length * sizeof(int), 0);
								//Get float
								n4 = recv(newsockfd, &a, sizeof(float), 0);

								case3_1_arg.x.x_len = 1;
								case3_1_arg.x.x_val[0] = a;
								case3_1_arg.y.y_len = array_length;
								case3_1_arg.y.y_val = (int*) malloc(array_length * sizeof(int));

								for (int i = 0; i < array_length; i++) {
																case3_1_arg.y.y_val[i] = y[i];
								}

								//Product of a*y
								result_3 = case3_1(&case3_1_arg, clnt);
								if (result_3 == (product*) NULL) {
																clnt_perror(clnt, "call failed");
								} else {
																ret_array = (float*) malloc(array_length * sizeof(float));
																for (int i = 0; i < array_length; i++) {
																								ret_array[i] = result_3->x.x_val[i];
																								printf("%f\n", ret_array[i]);
																}
																send(newsockfd, ret_array, array_length * sizeof(float), 0);
								}

								free(y);

								clnt_destroy(clnt);
}

void launchFunction(int newsockfd, char *host, char *port){

								int n1, user_choice = 0;

								//Launch suitable function
								do {
																// Get user_choice for calculation
																n1 = recv(newsockfd, &user_choice, sizeof(int), 0);

																switch (user_choice) {
																case 1:
																								distributed_systems_1_average(host, port, newsockfd);
																								break;
																case 2:
																								distributed_systems_1_minmax(host, port, newsockfd);
																								break;
																case 3:
																								distributed_systems_1_product(host, port, newsockfd);
																								break;
																default:
																								printf("Server waiting for user choice");
																								sleep(1);
																								break;
																}
								} while(user_choice !=9);

								exit(0);
}


int main(int argc, char *argv[]) {

								int pid, sockfd, newsockfd, portno, clilen, done, user_choice;
								char *host, *port;

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

								serv_addr.sin_family = AF_INET;

								serv_addr.sin_port = htons(portno);

								serv_addr.sin_addr.s_addr = INADDR_ANY;

								if (bind(sockfd, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) < 0)
																error("ERROR on binding");

								listen(sockfd, 5);
								clilen = sizeof(cli_addr);

								while (1) {
																newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);


																if (newsockfd < 0) {
																								perror("ERROR on accept");
																								exit(1);
																}

																pid = fork();

																if (pid < 0) {
																								perror("ERROR on fork");
																								exit(1);
																}

																if (pid == 0) {
																								/* This is the client process */
																								close(sockfd);
																								launchFunction(newsockfd, host, port);
																								exit(0);
																}
																else {
																								close(newsockfd);
																}
								};

								return 0;
}
