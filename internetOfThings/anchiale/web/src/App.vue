<template>
  <div>
    <Navbar />
    <Hero />
    <div class="section">
      <div class="notification is-primary is-light">
        Welcome to Anchiale's Dashboard! On the <b>Devices</b> tab you can see
        all the clients that are curently connected to the server. On the
        <b>Points</b> tab you can display the points writen withing the last
        hour to the database. Try fetching first and then display them on a
        chart or a table! Keep in mind that the API may take some time to
        respond, so be patient when fetching.
      </div>
    </div>
    <div class="columns mb-6 mx-4">
      <div class="column is-two-fifths mx-2">
        <Devices />
      </div>
      <div class="column mx-2">
        <Points />
      </div>
    </div>
    <Footer />
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';
import Navbar from '@/components/Navbar.vue';
import Hero from '@/components/Hero.vue';
import Devices from '@/components/Devices.vue';
import Points from '@/components/Points.vue';
import Footer from '@/components/Footer.vue';

@Options({
  components: {
    Navbar,
    Hero,
    Devices,
    Points,
    Footer,
  },
})
export default class App extends Vue {}
</script>
