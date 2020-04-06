/*
 ============================================================================
 Name        : client.c
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
#include <netdb.h>

void error(char *msg) {
	perror(msg);
	exit(0);
}

int main(int argc, char *argv[]) {
	int sockfd, portno, n, n1, t, done, array_callback, user_choice,
			array_length, mm_array[2];
	float a, avg;
	int *y;
	float *ret_array;

	struct sockaddr_in serv_addr;
	struct hostent *server;

	if (argc < 3) {
		fprintf(stderr, "usage %s hostname port\n", argv[0]);
		exit(0);
	}
	portno = atoi(argv[2]);
	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if (sockfd < 0)
		error("ERROR opening socket");

	server = gethostbyname(argv[1]);
	if (server == NULL) {
		fprintf(stderr, "ERROR, no such host\n");
		exit(0);
	}

	bzero((char*) &serv_addr, sizeof(serv_addr));
	serv_addr.sin_family = AF_INET;
	bcopy((char*) server->h_addr,
	(char *)&serv_addr.sin_addr.s_addr,
	server->h_length);
	serv_addr.sin_port = htons(portno);

	printf("Trying to connect...\n");

	if (connect(sockfd, (struct sockaddr*) &serv_addr, sizeof(serv_addr)) < 0)
		error("ERROR connecting");

	printf("Connected.\n");

	done = 0;
	do {
		printf("~~  MENU  ~~ \n Choose one of the following: \n");
		printf("\t 1. Average price of array Y");
		printf("\t 2. Min and Max of array Y");
		printf("\t 3. Product of a*Y \n >");
		scanf(" %d", &user_choice);

		send(sockfd, &user_choice, sizeof(int), 0);

		printf("Give the length of the array, n \n > ");
		scanf(" %d", &array_length);

		send(sockfd, &array_length, sizeof(int), 0);

		y = (int*) malloc(array_length * sizeof(int));

		for (int i = 0; i < array_length; ++i) {
			printf("Give Y[%d] : \t", i + 1);
			scanf(" %d", &y[i]);
		}

		send(sockfd, y, array_length * sizeof(int), 0);

		switch (user_choice) {
		case 1:
			//Average of array y
			n1 = recv(sockfd, &avg, sizeof(float), 0);
			printf("Average for array y is : \t %.2f \n", avg);

			break;
		case 2:
			//Min and Max of array y
			n1 = recv(sockfd, mm_array, sizeof(mm_array), 0);
			printf("\t Min for array y is : %d \t Max for array y is : %d",
					mm_array[0], mm_array[1]);

			break;
		case 3:
			//Product of a*y
			printf("Give float, a \n >");
			scanf(" %f", &a);
			send(sockfd, &a, sizeof(float), 0);

			ret_array = (float*) malloc(array_length * sizeof(float));
			n1 = recv(sockfd, ret_array, array_length * sizeof(float), 0);

			printf("\t Product of a*Y : \n");
			for (int i = 0; i < array_length; ++i) {
				printf("\t (a*y)[i]: %.2f", ret_array[i]);
			}

			break;
		default:
			printf("Wrong input!");
			break;
		}

		printf("\n\n\n Do you want to continue? ( 0-YES  /  1-NO)  \n >");
		scanf(" %d", &done);

	} while (!done);

	pclose(sockfd);

	return 0;
}

