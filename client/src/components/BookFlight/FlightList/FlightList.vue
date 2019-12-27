<template>
  <div class="mt-12 mx-auto" id="flight-list">
    <h2 class="mb-5" >{{topic}}</h2>
    <div v-for="(item, index) in flights" :key="index">
      <FlightCard :flight="item"/>
    </div>
  </div>
</template>

<script>
import FlightCard from './FlightCard'

// import {mapGetters} from 'vuex'
export default {
  name: "flightList",
  components: {
    FlightCard
  },
  props: {
    flights: {
      type: Array
    },
    topic:{
      type: String
    }
  },
}
</script>

<style>

</style>