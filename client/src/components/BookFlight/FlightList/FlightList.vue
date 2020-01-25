<template>
  <div class="mx-auto mt-10" id="flight-list">
    <v-row class="mb-3">
      <h4 v-if="this.isDepart" class="mycard mt-2 mr-4 ml-5"><v-icon>mdi-airplane-takeoff</v-icon> ขาไป</h4>
      <h4 v-else class="mycard mr-4 mt-2 ml-5 "><v-icon class="flip">mdi-airplane-takeoff</v-icon> ขากลับ</h4>
      <p class="mycard alert alert-success" >{{topic}}</p>
      <v-icon class="ml-3 icon" @click="showSearchBar()">mdi-lead-pencil</v-icon>
    </v-row>
    <FlightDate :isDepart="isDepart"/>
    <div v-if="flights == null || flights.length == 0">
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
import {mapMutations} from 'vuex'

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
    ...mapMutations({
      'showSearchBar':'BookFlight/showSearchBar'
    }),
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
  .icon{
    position:relative;
    top:-15px;
  }
  .flip {
    -webkit-transform: scaleX(-1);
    transform: scaleX(-1);
  }
</style>