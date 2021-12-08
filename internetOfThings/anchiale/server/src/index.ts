require('dotenv').config();
import express from 'express';
import chalk from 'chalk';
var cors = require('cors');
import { Service } from './class/Service';
// import { AppRouter } from './AppRouter';
import { Routes } from './routes/Routes';

console.log(chalk.cyan('Started Anchiale Server...'));

const app = express();
const http = require('http').Server(app);
const service = new Service(http);

app.use(cors());

const route = new Routes(app, service);
route.routes();

http.listen(3000, () => {
  console.log(chalk.yellow('Listening on 3000...'));
});
