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

void error(char *msg) {
	perror(msg);
	exit(1);
}

int main(int argc, char *argv[]) {
	int sockfd, newsockfd, portno, clilen, n1, n2, n3, n4, min, max,
			mm_array[2], user_choice, array_length, done;
	float a, avg = 0.0, sum = 0.0;
	int *y;
	float *ret_array;

	struct sockaddr_in serv_addr, cli_addr;

	if (argc < 2) {
		fprintf(stderr, "ERROR, no port provided\n");
		exit(1);
	}

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("ERROR opening socket");

	bzero((char*) &serv_addr, sizeof(serv_addr));

	portno = atoi(argv[1]);

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
					//Average of array y
					for (int i = 0; i < array_length; ++i) {
						sum += y[i];
					}
					avg = sum / (float) array_length;
					send(newsockfd, &avg, sizeof(float), 0);

					break;
				case 2:
					//Min and Max of array y
					min = y[0];
					max = y[0];

					for (int i = 0; i < array_length; ++i) {
						if (y[i] < min) {
							min = y[i];
						}

						if (y[i] > max) {
							max = y[i];
						}
					}
					mm_array[0] = min;
					mm_array[1] = max;
					send(newsockfd, mm_array, sizeof(mm_array), 0);

					break;
				case 3:
					//Product of a*y
					n4 = recv(newsockfd, &a, sizeof(float), 0);
					ret_array = (float*) malloc(array_length * sizeof(float));

					for (int i = 0; i < array_length; ++i) {
						ret_array[i] = y[i] * a;
					}
					send(newsockfd, ret_array, sizeof(ret_array), 0);

					break;
				default:
					printf("Something went wrong!");
					done = 1;
					break;
				}

			} while (!done);

			exit(0);
		}
		close(newsockfd);
	}

	return 0;
}
