<template>
  <nav class="panel is-primary">
    <p class="panel-heading">
      Points
    </p>
    <div class="panel-block">
      <div class="field is-grouped">
        <p class="control">
          <button class="button is-success is-outlined" @click="getData">
            Fetch Data
          </button>
        </p>
        <p class="control">
          <button class="button is-success is-outlined" @click="createChart">
            Create Chart
          </button>
        </p>
        <p class="control">
          <button class="button is-success is-outlined" @click="createTable">
            Create Table
          </button>
        </p>
        <p class="control">
          <button class="button is-outlined" @click="clearData">
            Clear
          </button>
        </p>
      </div>
    </div>
    <div v-if="isChart">
      <Canva :points="points" />
    </div>
    <div v-else-if="isTable">
      <Table :points="points" />
    </div>
    <div v-else-if="hasData">
      <span class="panel-block">
        Fetched data, try creating a chart or table!
      </span>
    </div>
    <div v-else-if="error">
      <span class="panel-block">{{ error }}</span>
    </div>
    <div v-else>
      <span class="panel-block">No data yet, try fetching!</span>
    </div>
  </nav>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import axios from 'axios';
import Canva from '@/components/Canva.vue';
import Table from '@/components/Table.vue';

@Options({
  components: {
    Canva,
    Table,
  },
})
export default class Points extends Vue {
  private error = '';
  private isChart = false;
  private isTable = false;
  private hasData = false;
  private points = [];
  private formatedPoints = [];
  private clients: Array<string> = [];

  getData() {
    axios
      .get(`${process.env.VUE_APP_SERVER_URL}/measurements`)
      .then((response) => {
        this.hasData = true;
        this.points = response.data;
      })
      .catch((error) => {
        this.error = error.message;
      });
  }

  clearData() {
    this.isChart = false;
    this.isTable = false;
  }

  getClient() {
    axios
      .get(`${process.env.VUE_APP_SERVER_URL}/devices`)
      .then((response) => {
        this.clients = response.data;
      })
      .catch((error) => {
        this.error = error.message;
      });
  }

  createChart() {
    this.isTable = false;
    this.isChart = true;
  }

  createTable() {
    this.isChart = false;
    this.isTable = true;
  }
}
</script>
