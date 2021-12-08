require('dotenv').config();
import chalk from 'chalk';
import { ClientService } from './class/ClientService';

console.log(chalk.cyan('Started Anchiale Client...'));

const service = new ClientService();

service.tempTestService();
