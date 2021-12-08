export class Sensor {
  constructor() {}

  get takeMeasure(): number {
    return 0;
  }

  get takeTestMeasure(): number {
    return Math.floor(Math.random() * 50);
  }
}
