<template>
  <div class="mt-12 mx-auto" id="flight-list">
    <h4 class="mb-5 mycard" >{{topic}}</h4>
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

<style scope>
  .mycard{
    /* border-style: solid; */
    /* background-color: rgb(255, 255, 255); */
    /* border-color: rgb(240, 240, 240); */
    /* border-width: 3px; */
    padding:20px;
    border-radius: 20px;
  }
</style>