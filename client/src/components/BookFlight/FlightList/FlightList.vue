<template>
  <div class="mt-12 mx-auto" id="flight-list">
    <h2 class="mb-5" >{{topic}}</h2>
    <div v-for="(item, index) in flights" :key="index">
      <FlightCard :isSelected="isSelected(item.id)" :selectFlight="selectFlight" :flight="item"/>
    </div>
  </div>
</template>

<script>
import FlightCard from './FlightCard'

export default {
  name: "flightList",
  components: {
    FlightCard
  },
  props: {
    selectedFlight:{
      type: Object
    },
    flights: {
      type: Array
    },
    topic:{
      type: String
    },
    selectFlight:{
      type: Function
    }
  },
  methods:{
    isSelected(id){
      if(this.selectedFlight != null){
        if(this.selectedFlight.id == id){
          return true;
        }
      }
      return false;
    }
  }
}
</script>

<style>

</style>