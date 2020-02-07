<template>
  <div>
    <FlightDetailSidebar v-if="showSideBar" :closeSidebar="this.closeSidebar"/>
    <MyAlert id="alert" :open="showGuestAlert" :topic="topicAlert" :desc="msgAlert" :callback="closeAlert" />
    <div :v-if="this.bookResult != null">
      <MyAlert id="alert_success" :open="this.bookResult != null" topic="จองเที่ยวบินสำเร็จ" desc="กด ตกลง เพื่อไปหน้าชำระเงิน" :callback="goToPayment" />
    </div>
    <div class="footer" >
      <v-row>
        <v-col cols="6">
          <div class="mt-5 float-right">
            <i class="fas fa-shopping-cart" ></i> <div @click="showSideBar = true" class="badge badge-info btn btn-info">detail</div>  <span class="ml-3">ราคารวม </span><span class="h5"> {{getTotalPrice | price}} THB</span>
          </div>
        </v-col>
        <v-col cols="6">
          <div class="mx-12">
            <div @click="nextBtnClick" :class="{'btn btn-danger btn-block btn-lg pt-4':getIsNextBtnAllow,'btn btn-danger btn-disabled btn-block btn-lg pt-4':!getIsNextBtnAllow,}" style="height:100%;">
              <v-icon color="white" size="30">mdi-chevron-right-circle-outline</v-icon> ดำเนินการต่อ 
            </div>
          </div>
        </v-col>
      </v-row>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions,mapState} from 'vuex'
import FlightDetailSidebar from './DetailSidebar/FlightDetailSidebar'
import MyAlert from '../Alert'
import router from '../../router'
var numeral = require("numeral");

export default {
  name: "cartFooter",
  components: {
    MyAlert,
    FlightDetailSidebar
  },
  data:()=>({
    showGuestAlert: false,
    msgAlert: '',
    topicAlert:'',
    showSideBar: false
  }),
  computed: {
    ...mapState({
      flightDepart: state => state.BookFlight.data.flightDepart,
      flightReturn: state => state.BookFlight.data.flightReturn,
      bookResult: state => state.BookFlight.result.bookResult,
      pageLocation: state => state.BookFlight.pageLocation,
    }),
    ...mapGetters({
      getTotalPrice: 'BookFlight/getTotalPrice',
      getIsNextBtnAllow: 'BookFlight/getIsNextBtnAllow'
    }),
  },
  
  filters:{
    price(price){
      // add this on top
      // var numeral = require("numeral");
      return numeral(price).format("0,0.00");
    }
  },
  methods: {
    ...mapActions({
      'nextPage' : 'BookFlight/nextPage'
    }),
    getDesc(){
      return 'Booking ID ของคุณ คือ ' + this.bookResult.bookId
    },
    closeSidebar(){
      this.showSideBar = false
    },
    closeAlert(){
      this.showGuestAlert = false
    },
    goToPayment(){
      router.push({ name: 'payment', params: {bookId: this.bookResult.bookId }})
    },
    nextBtnClick(){
      if(this.flightDepart == null && this.flightReturn == null){
        this.showGuestAlert = true
        this.topicAlert = "แจ้งเตือนจากระบบ"
        this.msgAlert = "กรุณาเลือกเที่ยวบินขาไปและขากลับ"
      }else
      if(this.flightDepart == null){
        this.showGuestAlert = true
        this.topicAlert = "แจ้งเตือนจากระบบ"
        this.msgAlert = "กรุณาเลือกเที่ยวบินขาไป"
      }else
      if(this.flightReturn == null){
        this.showGuestAlert = true
        this.topicAlert = "แจ้งเตือนจากระบบ"
        this.msgAlert = "กรุณาเลือกเที่ยวบินขากลับ"
      }else{
        this.nextPage().catch(err=>{
          this.showGuestAlert = true
          this.topicAlert = "เกิดข้อผิดพลาด"
          this.msgAlert = err.toString()
        })
      }
    }
  },
}
</script>

<style scoped>
.btn-disabled:hover{
  background-color: darkgray;
  color:white;
}
.btn-disabled{
  background-color: darkgray;
  color:white;
  border-width: 0px;
}
.btn{
  max-width: 300px
}
.footer {
  position: fixed;
  height:80px;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: rgb(41, 41, 41);
  color: white;
  text-align: center;
}
</style>