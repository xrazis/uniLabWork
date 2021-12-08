import { exit } from 'process';
import chalk from 'chalk';
import io from 'socket.io-client';

export class ClientSocket {
  private socket!: SocketIOClient.Socket;

  constructor(private path: string, private eventName: string) {
    this.initSocket();
    this.connStatus();
  }

  private initSocket(): void {
    this.socket = io(this.path, {
      timeout: 10000,
      reconnectionAttempts: 200,
      transports: ['websocket'],
    });
    console.log(chalk.yellow('Initialized socket...'));
  }

  private connStatus(): void {
    this.socket.on('connect', () => {
      console.log(chalk.green('Connected to server!'));
      this.socket.emit('subscribe', 'pi-iot');
    });

    this.socket.on('disconnect', (reason: string) => {
      console.log(chalk.red('Lost connection!'));

      if (reason === 'io server disconnect') this.socket.connect();

      if (reason === 'io client disconnect') {
        console.log(chalk.red('Server kicked you!'));
        exit(1);
      }

      console.log(chalk.yellow('Reconecting...'));
    });

    this.socket.on('closeConn', () => {
      this.closeConn();
    });
  }

  closeConn(): void {
    console.log(chalk.yellow('Closing socket...'));
    this.socket.disconnect();
  }

  sendMeasure(measurement: number, pointName: string): void {
    this.socket.emit(this.eventName, { measurement, pointName });
  }
}
