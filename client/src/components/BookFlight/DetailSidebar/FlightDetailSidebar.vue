<template>
  <div class="bg-dark">
    <div class="sidebar" id="baggage">
      <div class="card w-60 h-100">
        <v-icon @click="closeSidebar" color="white" class="close-btn btn">mdi-close</v-icon>
        <!-- This is menu bar -->
        <div class="d-flex justify-space-between">
          <div @click="menu = 1" :class="{'w-50 text-center p-4 btn btn-dark':true,'btn-selected':menu==1}" style="border-radius:0px">
            <v-icon large color="white">mdi-airplane-takeoff</v-icon>
            <div>{{this.airportDepart.name}} - {{this.airportArrive.name}}</div>
          </div>
          <div @click="menu = 2" :class="{'w-50 text-center p-4 btn btn-dark':true,'btn-selected':menu==2}" style="border-radius:0px">
            <v-icon large color="white" class="flip">mdi-airplane-takeoff</v-icon>
            <div>{{this.airportArrive.name}} - {{this.airportDepart.name}}</div>
          </div>
        </div>
        <!-- This is content -->
        <div class="p-4">
          <!-- this is flight detail -->
          <h3>เที่ยวบิน</h3>
          <FlightCardDetail v-if="flightDepart != null && menu==1" :flight="flightDepart"/>
          <BaggageCardDetail v-if="baggageDepart !=null && menu ==1" :baggage="baggageDepart"/>
          <FlightCardDetail v-if="flightReturn != null && menu==2" :flight="flightReturn"/>
          <BaggageCardDetail v-if="baggageReturn !=null && menu ==2" :baggage="baggageReturn"/>
        </div>
        <!-- footer -->
        <div style="position:fixed;bottom:0px;width:60vw">
          <hr class="hr">
          <div class="text-right d-flex mx-6 justify-space-between mb-4">
            <div></div>
            <div @click="closeSidebar" class="btn btn-danger btn-lg pt-3">ปิด</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState,mapGetters} from 'vuex'
var numeral = require("numeral");
import FlightCardDetail from './FlightCardDetail'
import BaggageCardDetail from './BaggageCardDetail'

export default {
  components:{
    FlightCardDetail,
    BaggageCardDetail
  },
  data:()=>({
    menu: 1 // 1 Depart , 2 Return
  }),
  props:{
    closeSidebar:{
      type: Function
    }
  },
  computed:{
    ...mapState({
        flightDepart: state=>state.BookFlight.data.flightDepart,
        flightReturn: state=>state.BookFlight.data.flightReturn,
        baggageDepart: state=>state.BookFlight.data.baggageDepart,
        baggageReturn: state=>state.BookFlight.data.baggageReturn,
        airportDepart: state => state.BookFlight.data.airportDepart,
        airportArrive: state => state.BookFlight.data.airportArrive
    }),
    ...mapGetters({
        getBaggagesPrice: 'BookFlight/getBaggagesPrice'
    }),
  },
  filters:{
    price(price){
      // add this on top
      // var numeral = require("numeral");
      return numeral(price).format("0,0.00");
    }
  },
  methods:{
    next(){
      if(this.menu == 2){
        this.closeSidebar()
      }
      if(this.menu == 1) {
        this.menu = 2 ;
      }
    }
  }
}
</script>

<style scoped>
  .sidebar{
    background-color: rgba(0, 0, 0, 0.562);
    padding-left: 40vw;
    height: 100vh;
    width: 100vw;
    position: fixed;
    right:0px;
    top:0px;
    z-index: 1;
  }
  .btn-dark.btn-selected{
    background-color: black;
  }
  .my-bottom{
    position: relative;
    bottom: 0px;
  }
  hr.hr{
    height: 10px;
    border: 0;
    box-shadow: 0 -10px 10px -10px #8c8c8c inset;
  }
  .close-btn{
    position:absolute;
    right:0px;
  }
  .flip {
    -webkit-transform: scaleX(-1);
    transform: scaleX(-1);
  }
</style>