<template>
  <div class="mx-10 mt-10 mb-12" id="adds-on">
    <BaggageSidebar v-if="showBaggage" :closeSidebar="()=> showBaggage = false"/>
    <h3>บริการเสริมพิเศษ</h3>
    <div class="card btn my-menu mt-4" @click="showBaggage = true">
      <div class="d-flex p-4 justify-center">
        <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" alt="">
        <div class="w-100 mx-10">
          <div class="d-flex justify-space-between">
            <div>
              <p class="text-left h2">สัมภาระเช็คอิน</p>
              <span class="alert alert-secondary"><v-icon>mdi-alert-circle-outline</v-icon> สัมภาระพกพา 7 กก. รวมอยู่ในค่าโดยสารแล้ว</span>
            </div>
            <div>
              <div class="text-success h4">ค่าใช้จ่าย {{getBaggagesPrice | price }} บาท <v-icon>mdi-chevron-right</v-icon></div> 
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaggageSidebar from './BaggageSidebar'
import {mapGetters} from 'vuex'
var numeral = require("numeral");

export default {
  data: ()=>({
    showBaggage: false
  }),
  components:{
    BaggageSidebar
  },
  computed:{
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
}
</script>

<style scoped>
  .my-menu:hover{
    box-shadow: 5px 5px 10px rgb(148, 148, 148);
  }
</style>