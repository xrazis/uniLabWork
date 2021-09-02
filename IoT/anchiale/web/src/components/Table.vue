<template>
  <table
    class="table 
        table is-striped
        table is-hoverable
        has-text-left
        table is-fullwidth"
  >
    <thead>
      <tr>
        <th><abbr title="Device">Device id</abbr></th>
        <th><abbr title="Time">Time</abbr></th>
        <th><abbr title="Temp">Measurement</abbr></th>
      </tr>
    </thead>
    <tr v-for="(point, index) in points" :key="index">
      <td class="is-family-monospace">{{ point.client }}</td>
      <td>{{ new Date(point.time).toLocaleString() }}</td>
      <td>{{ point.value }}°C</td>
    </tr>
  </table>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import { Component, Prop } from 'vue-property-decorator';

export default class Table extends Vue {
  @Prop() readonly points!: [];
}
</script>

<style></style>
