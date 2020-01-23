<template>
  <!-- Menu card -->
  <div class="card">
    <div class="card-header">
      <v-icon>mdi-account</v-icon> ผู้โดยสาร 1 ท่าน
    </div>
    <div class="card-body">
      <!-- card seeletec menu -->
      <div class="d-flex flex-wrap">
        <div class="btn card my-img m-1">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class="baggageIcon"/>
          <div class="text-secondary">0 กก.</div>
          <div>ไม่มีสัมภาระเช็คอิน</div>
        </div>
        <div v-for="(baggage,index) in baggages" :key="index" class="btn card my-img m-1">
          <img :src="baggage.baggageImage.url" class="baggageIcon"/>
          <div class="text-secondary">{{baggage.maxWeight}} กก.</div>
          <div>{{baggage.price}}.00 THB</div>
        </div>
      </div>
      <hr>
      <div class="text-right text-success">เลือกสัมภาระของเที่ยวบินขา{{menu==1?'ไป':'กลับ'}}</div>
    </div>
  </div>
</template>

<script>
import {BookFlightService} from '../../../api'
import {mapState} from 'vuex'
export default {
  data:()=>({
    baggages:[]
  }),
  computed:{
    ...mapState({
      airportDepart: state => state.BookFlight.data.airportDepart,
      airportArrive: state => state.BookFlight.data.airportArrive
    })
  },
  props:{
    menu:{
      type: Number
    },
  },
  mounted(){
    if(this.menu == 2){
      BookFlightService.getAddsOnByAirport(this.airportArrive.id).then(res => {
        console.log(res.data)
        this.baggages = res.data
      })
    }else{
      BookFlightService.getAddsOnByAirport(this.airportDepart.id).then(res => {
        console.log(res.data)
        this.baggages = res.data
      })
    }
  }
}
</script>

<style scoped>
  .my-img {
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
    border-radius: 10px;
  }
  .my-img:hover {
    border-color: green;
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
  }
  .baggageIcon{
    width: 150px;
  }
</style>