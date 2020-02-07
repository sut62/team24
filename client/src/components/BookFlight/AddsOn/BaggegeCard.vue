<template>
  <!-- Menu card -->
  <div class="card">
    <div class="card-header">
      <v-icon>mdi-account</v-icon> ผู้โดยสาร 1 ท่าน
    </div>
    <div v-if="this.baggages.length == 0" class="card-body">
      <div class="p-4">
        <h3>สนามบินนี้ไม่มีบริการสัมภาระเช็คอินพิเศษ</h3>
      </div>
    </div>
    <div v-else class="card-body">
      <!-- menu type selection -->
      <div class="btn-group" role="group" aria-label="Basic example">
        <button disabled class="btn btn-secondary">ประเภทสัมภาระ</button>
        <button v-for="(type,index) in baggageTypes" :key="index"  @click="selected.typeId = type.id" type="button" :class="{'btn ':true,'btn-warning':selected.typeId != type.id,'btn-danger':selected.typeId == type.id}">{{type.btypename}}</button>
      </div>
      <!-- card selected menu -->
      <div class="d-flex mt-3 flex-wrap">
        <div @click="menu == 1 ? selectBaggageDepart(null) : selectBaggageReturn(null)" :class="{'btn card my-img m-1':true,'my-selected':menu == 1? baggageDepart == null : baggageReturn == null}">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class="baggageIcon"/>
          <v-icon v-show="menu == 1? baggageDepart == null : baggageReturn == null" color="green" class="icon-check">mdi-check-circle</v-icon>
          <div class="text-secondary">0 กก.</div>
          <div>ไม่มีสัมภาระเช็คอิน</div>
        </div>
        <div @click="menu == 1 ? selectBaggageDepart(baggage) : selectBaggageReturn(baggage)" v-for="(baggage,index) in baggagesByType" :key="index" :class="{'btn card my-img m-1':true,'my-selected':menu == 1? baggageDepart != null && baggageDepart.id == baggage.id : baggageReturn != null && baggageReturn.id == baggage.id}">
          <img :src="baggage.baggageImage.url" class="baggageIcon"/>
          <v-icon v-show="menu == 1? baggageDepart != null && baggageDepart.id == baggage.id : baggageReturn != null && baggageReturn.id == baggage.id" color="green" class="icon-check">mdi-check-circle</v-icon>
          <div class="text-secondary">{{baggage.maxWeight}} กก.</div>
          <div>{{baggage.price | price}} THB</div>
        </div>
      </div>
      <hr>
      <div class="text-right text-success">เลือกสัมภาระของเที่ยวบินขา{{menu==1?'ไป':'กลับ'}}</div>
    </div>
    
  </div>
</template>

<script>
import {BookFlightService} from '../../../api'
import {mapState,mapMutations} from 'vuex'
var numeral = require("numeral");
export default {
  data:()=>({
    baggages:[],
    baggageTypes:[],
    selected:{
      typeId:0
    }
  }),
  computed:{
    ...mapState({
      airportDepart: state => state.BookFlight.data.airportDepart,
      airportArrive: state => state.BookFlight.data.airportArrive,
      baggageDepart: state => state.BookFlight.data.baggageDepart,
      baggageReturn: state => state.BookFlight.data.baggageReturn,
    }),
    baggagesByType(){
      return this.baggages.filter(bag => bag.baggageType.id == this.selected.typeId)
    }
  },
  filters:{
    price(price){
      // add this on top
      // var numeral = require("numeral");
      return numeral(price).format("0,0.00");
    }
  },
  methods:{
    ...mapMutations({
      selectBaggageDepart: 'BookFlight/SELECT_BAGGAGE_DEPART',
      selectBaggageReturn: 'BookFlight/SELECT_BAGGAGE_RETURN'
    })
  },
  props:{
    menu:{
      type: Number
    },
  },
  mounted(){
    BookFlightService.getBaggageType().then(
      res => {
        this.baggageTypes = res.data
        this.selected.typeId = this.baggageTypes[0].id
      }
    )
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
  .my-selected{
    border-color: green!important;
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
    border-width: 3px;
  }
  .my-img {
    border-color: rgb(255, 255, 255);
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
    border-radius: 10px;
    border-width: 3px;
  }
  .my-img:hover {
    border-color: green;
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
    border-width: 3px;
  }
  .baggageIcon{
    width: 150px;
  }
  .icon-check{
    position: absolute;;
    top:4px;
    right:4px;
  }
</style>