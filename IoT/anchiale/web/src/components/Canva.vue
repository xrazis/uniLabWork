<template>
  <canvas id="chart" />
</template>

<script lang="ts">
import { Vue } from 'vue-class-component';
import { Prop } from 'vue-property-decorator';
import Chart from 'chart.js';

interface points {
  client: string;
  value: string;
  time: string;
}

export default class Canva extends Vue {
  @Prop() readonly points!: [points];

  mounted() {
    // spaggheti code, MUST refactor
    let dataset = [];
    let data = [];
    let counter = 0;
    let client_id = this.points[0].client;

    //bad code - assumes clients come grouped
    for (const point of this.points) {
      if (point.client != client_id || counter === this.points.length - 1) {
        dataset.push({
          label: client_id,
          borderColor: '#808080',
          backgroundColor: this.getRandomColor(),
          data: data,
        });
        client_id = point.client;
        data = [];
        counter++;
      }

      const time =
        new Date(point.time).getHours() +
        '.' +
        new Date(point.time).getMinutes();

      data.push({
        x: parseFloat(time),
        y: parseInt(point.value),
      });
    }

    const ctx = document.getElementById('chart') as HTMLCanvasElement;

    new Chart(ctx, {
      type: 'scatter',
      data: {
        datasets: dataset,
      },
      options: {
        scales: {
          xAxes: [
            {
              type: 'linear',
              position: 'bottom',
            },
          ],
        },
      },
    });
  }

  getRandomColor(): string {
    let letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }
}
</script>

<style></style>
