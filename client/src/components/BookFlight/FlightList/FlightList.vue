<template>
  <div class="mt-12 mx-auto" id="flight-list">
    <h4 class="mb-5 mycard" >{{topic}}</h4>
    <FlightDate :isDepart="isDepart"/>
    <div v-if="flights == null">
      <v-card class="text-center mt-4 py-12">
        <p style="color:grey">
          ไม่มีเที่ยวบินให้บริการค่ะ
          <br>ขออภัย เราไม่มีเที่ยวบินให้บริการในวันดังกล่าว กรุณาค้นหาโดยเลือกวันเดินทางอื่น
          <br>บางที ท่านอาจจะพบบัตรโดยสารในราคาคุ้มกว่าเดิม
        </p>
      </v-card>
    </div>
    <div v-if="flights != null">
      <div v-for="(item, index) in flights" :key="index">
        <FlightCard :isSelected="isSelected(item.id)" :selectFlight="selectFlight" :flight="item"/>
      </div>
    </div>
  </div>
</template>

<script>
import FlightCard from './FlightCard'
import FlightDate from './FlightDateGroup'
export default {
  name: "flightList",
  components: {
    FlightCard,
    FlightDate
  },
  props: {
    isDepart:{
      type: Boolean
    },
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