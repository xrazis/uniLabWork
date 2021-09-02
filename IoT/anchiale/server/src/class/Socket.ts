import chalk from 'chalk';

interface database {
  write(uuid: string, measurement: number, pointName: string): void;
  closeWrite(): void;
}

interface server extends SocketIO.Server {
  allSockets(): Promise<{}>;
}

interface packet {
  uuid: string;
  measurement: number;
  pointName: string;
}

interface device {
  device: string;
}

export class Socket<T> {
  private io!: server;

  constructor(
    private eventName: string,
    private server: T,
    private database: database
  ) {
    this.initSocket();
    this.initConn();
  }

  initSocket(): void {
    this.io = require('socket.io')(this.server);
    console.log(chalk.yellow('Initialized Socket...'));
  }

  initConn(): void {
    this.io.on('connect', (socket: SocketIO.Socket) => {
      console.log(chalk.green('Client connected!'));

      socket.on('subscribe', (room) => {
        socket.join(room);
        console.log(
          chalk.magenta(`Client ${socket.id} joined room "${room}". `)
        );
      });

      socket.on('disconnect', () => {
        console.log(chalk.red('Client disconected!'));
      });

      socket.on(this.eventName, (data: packet) => {
        const { measurement, pointName } = data;
        this.database.write(socket.id, measurement, pointName);
      });
    });
  }

  get rooms(): Array<String> {
    let roomsArray: Array<String> = [];
    this.io.allSockets().then((msg: any) => {
      const iterator = msg.entries();
      for (const entry of iterator) {
        roomsArray.push(entry[0]);
      }
    });
    return roomsArray;
  }

  removeDevice(deviceToRemove: device): boolean {
    this.io.to(deviceToRemove.device).emit('closeConn');
    return true;
  }

  closeSocket(): void {
    console.log(chalk.red('Closing socket...'));
    this.io.close();
  }
}
