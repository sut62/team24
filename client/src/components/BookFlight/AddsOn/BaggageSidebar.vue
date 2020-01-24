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
          <!-- Alert -->
          <div class="alert alert-info w-100">
            <span class=""><v-icon color="white">mdi-alert-circle-outline</v-icon> สัมภาระพกพา 7 กก. รวมอยู่ในค่าโดยสารแล้ว</span>
          </div>
          <BaggageCard v-show="menu == 1" :menu="1"/>
          <BaggageCard v-show="menu == 2" :menu="2"/>
        </div>
        <!-- footer -->
        <div style="position:fixed;bottom:0px;width:60vw">
          <hr class="hr">
          <div class="text-right d-flex mx-6 justify-space-between mb-4">
            <div class="text-black text-left">รวม
              <div class="h5 text-info"> {{getBaggagesPrice | price}} THB</div>
            </div>
            <div @click="next" class="btn btn-danger btn-lg pt-3">{{menu == 1?'เที่ยวบินถัดไป':'เสร็จสิ้น'}} <v-icon color="white">mdi-chevron-right</v-icon></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaggageCard from './BaggegeCard'
import {mapState,mapGetters} from 'vuex'
var numeral = require("numeral");

export default {
  components:{
    BaggageCard
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
      airportDepart: state => state.BookFlight.data.airportDepart,
      airportArrive: state => state.BookFlight.data.airportArrive
    }),
    ...mapGetters({
      getBaggagesPrice: 'BookFlight/getBaggagesPrice'
    })
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