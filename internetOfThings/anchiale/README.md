# Anchiale

## About

**Anchiale was the Titan goddess of the warming heat of fire.**

Anchiale is an internet of things solution for temperature sampling with raspberry pi. It is designed around a scalable architecture, so it is capable of handling a big sample rate. 


# Installation

Clone the project:

    git clone git@github.com:xrazis/anchiale.git
## Run Locally

The server connects to an influx-db instance. Make sure you first start the database and correctly set the .env file on the server.

**Start** 
Install npm packages: 

    npm install --prefix ./server
    npm install --prefix ./client

**Running the server:**

    npm start --prefix ./server


> Set the server URL in the /client/.env to http://localhost:3000/ 

**Running the client:**

    npm start --prefix ./client
## Development mode

**Running the server:**

    npm run dev prefix ./server

**Running the client:**

    npm run dev prefix ./client


## Run in Docker

**Start the database:**

    bash ./database/database.sh


> Configure the database on localhost:8086. Be sure to set and export the token, org and bucket in the .env file. 

**Start the server**

    cd server
    bash server.sh


> Set the server URL in the .env file.

**Start the client**

    cd client
    bash client.sh


## Connecting to the anchiale server running on [swarmlab.io](http://swarmlab.io)

A server with a database is running over at swarmlab. You can connect your local client running on your pi! 


1. Set the URL in the client .env file to XXXXXXXXXX.
2. Start up the client with one of the ways described above.
3. Done 😄 ! 
# Web console

On the web console you can see the devices connected and display the latest measurements in a chart or table. Installation and running instructions are in a separate markdown file, in the /web sub-directory.



