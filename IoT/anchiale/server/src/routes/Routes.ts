import * as express from 'express';
import { Service } from '../class/Service';

export class Routes {
  constructor(public app: express.Application, public service: Service) {
    this.app = app;
    this.setStaticDir();
  }

  setStaticDir() {}

  private devices(): void {
    this.app.get('/devices', async (req, res) => {
      const data = await this.service.socket.rooms;

      res.header('Content-Type', 'application/json');
      if (data) {
        res.send(JSON.stringify(data));
      } else {
        res.send(JSON.stringify({}));
      }
    });

    this.app.post('/devices', (req, res) => {
      req.on('data', async (data) => {
        const deviceToRemove = JSON.parse(data);
        const status = await this.service.socket.removeDevice(deviceToRemove);

        res.header('Content-Type', 'application/json');
        if (status) {
          res.send('done');
        } else {
          res.send('error');
        }
      });
    });
  }

  private measurements(): void {
    this.app.get('/measurements', async (req, res) => {
      const points = await this.service.database.query('temperature', '1h'); // make temperature to something more abstract

      res.header('Content-Type', 'application/json');
      if (points) {
        res.send(JSON.stringify(points));
      } else {
        res.send(JSON.stringify({}));
      }
    });
  }

  private socket(): void {
    this.app.post('/socket', (req, res) => {
      req.on('data', async (data) => {
        const reset = JSON.parse(data).action;
        await this.service.socket.closeSocket();

        if (reset === 'reset') {
          await this.service.socket.initSocket();
          await this.service.socket.initConn();
        }

        res.header('Content-Type', 'application/json');
        res.send('done');
      });
    });
  }

  public routes(): void {
    this.devices();
    this.measurements();
    this.socket();
  }
}
