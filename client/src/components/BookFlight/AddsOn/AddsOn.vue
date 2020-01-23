<template>
  <div class="mx-10 mt-10 mb-12" id="adds-on">
    <BaggageSidebar v-if="showBaggage" :closeSidebar="()=> showBaggage = false"/>
    <h3>บริการเสริมพิเศษ</h3>
    <div class="card btn" @click="showBaggage = true">
      <div class="d-flex p-4 justify-center">
        <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" alt="">
        <div class="w-100 mx-10">
          <div class="d-flex justify-space-between">
            <div>
              <p class="text-left h2">สัมภาระเช็คอิน</p>
              <span class="alert alert-secondary"><v-icon>mdi-alert-circle-outline</v-icon> สัมภาระพกพา 7 กก. รวมอยู่ในค่าโดยสารแล้ว</span>
            </div>
            <div>
              <div class="text-success h4">ค่าใช้จ่าย 770.00 บาท <v-icon>mdi-chevron-right</v-icon></div> 
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaggageSidebar from './BaggageSidebar'
export default {
  data: ()=>({
    showBaggage: false
  }),
  components:{
    BaggageSidebar
  },
  mounted(){
  }
}
</script>

<style>

</style>