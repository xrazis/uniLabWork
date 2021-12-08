import {
  InfluxDB,
  Point,
  QueryApi,
  WriteApi,
} from '@influxdata/influxdb-client';
import chalk from 'chalk';

export class Database {
  private client!: InfluxDB;
  private writeApi!: WriteApi;
  private queryApi!: QueryApi;

  constructor() {
    this.initDatabase();
  }

  private initDatabase(): void {
    this.client = new InfluxDB({
      url: process.env.DB_URL!,
      token: process.env.DB_TOKEN!,
    });
    this.writeApi = this.client.getWriteApi(
      process.env.DB_ORG!,
      process.env.DB_BUCKET!
    );
    this.queryApi = this.client.getQueryApi(process.env.DB_ORG!);
    console.log(chalk.yellow('Initialized Database connection...'));
  }

  write(uuid: string, measurement: number, pointName: string): void {
    const point = new Point(pointName)
      .tag('client', uuid)
      .floatField('value', measurement);
    this.writeApi.writePoint(point);
    this.writeApi.flush();
  }

  closeWrite(): void {
    this.writeApi
      .close()
      .then(() => {
        console.log(chalk.magenta('Write finished'));
      })
      .catch((e) => {
        console.error(e);
        console.log(chalk.red('Write ERROR'));
      });
  }

  query(filter: string, timeFrame: string) {
    const query = `from(bucket: "${process.env.DB_BUCKET}") |> range(start: -${timeFrame}) |> group(columns: ["client"])
      |> filter(fn: (r) => r._measurement == "${filter}")`;

    const data = this.queryApi
      .collectRows(query)
      .then(async (result) => {
        const formatedData = await this.formatData(result);
        return formatedData;
      })
      .catch(() => {
        return [{ Error: 'Error occured' }];
      });

    return data;
  }

  formatData(rawData: any): Array<{}> {
    let formatedData: Array<{}> = [];

    rawData.forEach((element: any) => {
      formatedData.push({
        client: element.client,
        time: element._time,
        value: element._value,
      });
    });

    return formatedData;
  }
}
