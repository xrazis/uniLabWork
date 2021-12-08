import { Server } from 'http';
import { Database } from './Database';
import { Socket } from './Socket';

export class Service {
  database!: Database;
  socket!: Socket<Express.Application>;

  constructor(private http: Server) {
    this.initDatabase();
    this.initSocket();
  }

  private initDatabase() {
    this.database = new Database();
  }

  private initSocket() {
    this.socket = new Socket<Server>('measurement', this.http, this.database);
  }
}
