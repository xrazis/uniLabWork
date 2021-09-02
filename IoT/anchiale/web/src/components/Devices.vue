<template>
  <nav class="panel is-primary">
    <p class="panel-heading">
      Devices
    </p>
    <div class="panel-block">
      <div class="field is-grouped">
        <p class="control">
          <button class="button is-success is-outlined " v-on:click="getClient">
            Fetch Clients
          </button>
        </p>
        <p class="control">
          <button class="button is-outlined" v-on:click="clearClient">
            Clear
          </button>
        </p>
      </div>
    </div>
    <div v-if="hasClients">
      <div class="mx-4 py-2" v-for="(client, index) in clients" :key="index">
        <nav class="level">
          <div class="level-left">
            <span class="icon is-medium">
              <i class="fab fa-raspberry-pi" />
            </span>
            <p class="is-family-monospace">{{ client }}</p>
          </div>
          <div class="level-right">
            <a class="delete" v-on:Click="deleteClient(index)"></a>
          </div>
        </nav>
      </div>
    </div>
    <div v-else-if="error">
      <span class="panel-block">{{ error }}</span>
    </div>
    <div v-else>
      <span class="panel-block">No clients yet, try fetching!</span>
    </div>
    <div class="panel-block">
      <p class="control">
        <button
          class="button is-danger is-outlined is-fullwidth"
          v-on:click="resetSocket"
        >
          Close server socket
        </button>
      </p>
    </div>
  </nav>
</template>

<script lang="ts">
import { Vue } from 'vue-class-component';
import axios from 'axios';

export default class Devices extends Vue {
  private error = '';
  private hasClients = false;
  private clients: Array<string> = [];

  getClient() {
    axios
      .get(`${process.env.VUE_APP_SERVER_URL}/devices`)
      .then((response) => {
        this.hasClients = true;
        this.clients = response.data;
      })
      .catch((error) => {
        this.error = error.message;
      });
  }

  clearClient() {
    this.hasClients = false;
  }

  deleteClient(key: number) {
    axios
      .post(`${process.env.VUE_APP_SERVER_URL}/devices`, {
        device: this.clients[key],
      })
      .then((res) => {
        if (res.data === 'done') {
          this.getClient();
        }
      })
      .catch((error) => {
        console.log(error);
      });
  }

  resetSocket() {
    axios
      .post(`${process.env.VUE_APP_SERVER_URL}/socket`, {
        action: 'close',
      })
      .then((res) => {
        this.hasClients = false;
      })
      .catch((error) => {
        console.log(error);
      });
  }
}
</script>
