FROM node:latest

WORKDIR /usr/src/anchiale

COPY package*.json ./

RUN npm install && npm install typescript -g

COPY . .

EXPOSE 3000

RUN tsc

CMD ["node", "./build/index.js"]