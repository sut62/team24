<template>
<div>
  <!-- <div v-if="true">
    <PaymentChooser/>
  </div> -->
  <Alert :open="saveSuccess" topic="แจ้งเตือน" desc="บันทึกสำเร็จ" :callback="goToFlight"/>
  <Alert :open="dataFail" topic="แจ้งเตือน" desc="กรุณากรอกข้อมูลให้ครบ" :callback="()=>this.dataFail = false"/>
  <Alert :open="codeNotFound" topic="แจ้งเตือน" desc="Code ไม่ถูกต้อง" :callback="()=>this.codeNotFound = false"/>
  <div class="payment" v-if="checkToLoad()">
    <div class="header-bg"></div>
    <div class="content">
      <UserNavbar />
      <div class="container" style="margin-top:100px;">
        <div class="mx-auto">
          <div>
            <div class="text-white">
              <div>
                <h1 class="mt-5">เลือกชำระเที่ยวบินของคุณ</h1>
              </div>
            </div>
          </div>

          <div>
            <v-card class="text-center mt-4">
              <v-toolbar flat color="primary" dark>
                <v-toolbar-title>Booking details</v-toolbar-title>
              </v-toolbar>
              <v-row class="text-left pl-7">
                <v-col cols="6">
                  <v-row>
                    <v-icon slot="icon" size="36">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                  </v-row>
                  <p>{{data.flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY")}}</p>
                  <p>
                    {{data.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                    <v-icon color="error">mdi-arrow-right</v-icon>
                    {{data.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                  </p>
                  <p>
                    {{data.flightBooking.flightBookingLinks[0].flight.depart | moment("HH:mm")}} - {{data.flightBooking.flightBookingLinks[0].flight.arrive | moment("HH:mm")}}
                    | {{getDuration(data.flightBooking.flightBookingLinks[0].flight.arrive,data.flightBooking.flightBookingLinks[0].flight.depart)}}
                  </p>
                </v-col>
                <v-col>
                  <v-row>
                    <i class="airasia-icon icon-return-flight"></i>
                    <v-icon slot="icon" size="36" class="flip">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                  </v-row>
                  <p>{{data.flightBooking.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY")}}</p>
                  <p>
                    {{data.flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.city.name}}
                    <v-icon color="error">mdi-arrow-right</v-icon>
                    {{data.flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.city.name}}
                  </p>
                  <p>
                    {{data.flightBooking.flightBookingLinks[1].flight.depart | moment("HH:mm")}} - {{data.flightBooking.flightBookingLinks[1].flight.arrive | moment("HH:mm")}}
                    | {{getDuration(data.flightBooking.flightBookingLinks[1].flight.arrive,data.flightBooking.flightBookingLinks[1].flight.depart)}}
                  </p>
                </v-col>
              </v-row>
            </v-card>
          </div>
          <!-- +++++++++++++++++++++++++++++++++++++++ -->
          <div>
            <v-row>
              <v-col cols="8" v-if="paymentTypesLoaded">
                <div>
                  <div class="mt-5">
                    <v-card>
                      <v-toolbar flat color="primary" dark>
                        <v-toolbar-title>Payment</v-toolbar-title>
                      </v-toolbar>
                      <v-tabs vertical>
                        <v-tab v-for="(paymentType,index) in data.paymentTypes" :key="index">
                          <v-icon left>mdi-check-all</v-icon>
                          {{paymentType.name}}
                        </v-tab>

                        <v-tab-item v-for="(paymentType,index) in data.paymentTypes" :key="index">
                          <v-card flat>
                            <v-card-text>
                              <p>Please Select</p>

                              <v-radio-group v-model="select.paymentWayId" column>
                                <v-radio
                                  v-for="(paymentWay,index) in paymentType.paymentWays"
                                  :key="index"
                                  :label="paymentWay.name"
                                  :value="paymentWay.id"
                                ></v-radio>
                              </v-radio-group>
                            </v-card-text>
                          </v-card>
                        </v-tab-item>
                      </v-tabs>
                    </v-card>
                  </div>
                </div>
              </v-col>
              <!-- *************************************** -->
              <v-col>
                <v-card class="text-center mt-5">
                  <v-toolbar flat color="primary" dark>
                    <v-toolbar-title class="pt-1">
                      <v-row>
                        <v-col cols="4">
                          <h4 class="text-left pt-2">Total</h4>
                        </v-col>
                        <v-col cols="4">
                          <h4 class="text-center pt-2">{{getDiff}}</h4>
                        </v-col> 
                        <v-col cols="4">
                          <h4 class="text-right pt-2">THB</h4>
                        </v-col>
                      </v-row>
                    </v-toolbar-title>
                  </v-toolbar>
                  <div class="px-3">
                    <div class="d-flex pt-2">
                      <div class="mr-auto p-2" style="color:grey">Subtotal</div>
                      <div class="p-2">{{totalPrice}}</div>
                    </div>
                    <div class="d-flex pt-2" v-if="this.discountPer > 0">
                      <div class="mr-auto p-2" style="color:grey">Discount</div>
                      <div class="p-2">{{getDiscount}}</div>
                    </div>
                    <div class="d-flex pt-2" v-if="this.discountPer > 0">
                      <div class="mr-auto p-2" style="color:grey">Total</div>
                      <div class="p-2">{{getDiff}}</div>
                    </div>
                    <hr />

                    <div class="d-flex">
                      <div class="mr-auto p-2" style="color:grey">E-Gift Voucher</div>
                      <div class="my-2">
                        <v-btn
                          @click="showVoucher=!showVoucher"
                          text
                          color="error"
                        >{{showVoucher?'Cancel' : 'Apply'}}</v-btn>
                      </div>
                    </div>
                    <div class="input-group mb-3" v-show="showVoucher">
                      <input
                        type="text"
                        v-model="data.promotionCode"
                        class="form-control"
                        placeholder="code"
                        aria-describedby="button-addon2"
                      />
                      <div class="input-group-append">
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-addon2"
                          @click="checkPromotion"
                        >Search</button>
                      </div>
                    </div>

                    <hr />

                    <div class="text-center p-3">
                      <v-btn @click="save" large color="error">Purchase</v-btn>
                    </div>
                  </div>
                </v-card>
              </v-col>
            </v-row>
          </div>
          <!-- +++++++++++++++++++++++++++++++++++++++ -->

          <!-- +++++++++++++++++++++++++++++++++++++++ -->
        </div>
        <br />
      </div>
      <UserFooter />
    </div>
  </div>
</div>
</template>

<script>
import UserNavbar from "../components/UserNavbar";
import UserFooter from "../components/UserFooter";
// import PaymentChooser from "../components/Payment/PaymentByFindBookingId";
import axios from "axios";
import Alert from '../components/Alert';
import router from '../router'
var numeral = require("numeral");

let http = axios.create({
  baseURL: "http://localhost:9000/api",
  timeout: 120000,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-type": "application/json"
  }
});

export default {
  name: "payment",
  props:{
    bookId:{
      type: String
    }
  },
  data: () => ({
    saveSuccess: false,
    dataFail: false,
    codeNotFound: false,
    showVoucher: false,
    flightBookingLoaded: false,
    paymentWaysLoaded: false,
    paymentTypesLoaded: false,
    select: {
      paymentWayId: -1
    },
    data: {
      flightBooking: {},
      paymentWays: [], // no used
      paymentTypes: [],
      promotionCode: '',
    },
    discountPer: 0
  }),
  components: {
    UserNavbar,
    UserFooter,
    Alert
    // PaymentChooser
  },
  computed: {
    
    getDiscount(){
      let discount = 
        (this.data.flightBooking.flightBookingLinks[0].flight.price + 
        this.data.flightBooking.flightBookingLinks[1].flight.price) * this.discountPer / 100
      return numeral(discount).format("0,0.00");
    },
    getDiff(){
      let sum =
        this.data.flightBooking.flightBookingLinks[0].flight.price +
        this.data.flightBooking.flightBookingLinks[1].flight.price;
      let discount = 
        (this.data.flightBooking.flightBookingLinks[0].flight.price + 
        this.data.flightBooking.flightBookingLinks[1].flight.price) * this.discountPer / 100
      let diff = sum - discount;
       return numeral(diff).format("0,0.00");
    },
    totalPrice() {
      let sum =
        this.data.flightBooking.flightBookingLinks[0].flight.price +
        this.data.flightBooking.flightBookingLinks[1].flight.price;
      return numeral(sum).format("0,0.00");
    }
  },
  methods: {
    save(){
      if (this.getDiscount > 0) 
        this.savePaymentWithPromotionCode()
      else 
        this.savePayment()
    },
    goToFlight(){
      router.push({name:'bookFlight'})
    },
    checkPromotion(){
        this.data.promotionCode
        http.get('/promotion-code/'+this.data.promotionCode)
      .then(res => {
        
        console.log(res.data)
        if(this.isRealValue(res.data))
          this.discountPer = res.data.discount;
        else
          this.codeNotFound = true;
      }).catch(e=>{console.log(e)})
  
      }
      
      ,
    checkToLoad(){
      if(this.flightBookingLoaded && this.paymentTypesLoaded){
        if(!this.isRealValue(this.data.flightBooking)){
          alert("Book ID " + this.bookId + " not founded")
          router.push({name:'bookFlight'})
        }
        return true
      }
      return false
    },
    isRealValue(obj)
    {
      return obj && obj !== 'null' && obj !== 'undefined';
    },
    savePayment() {
      if (
        this.select.paymentWayId == "-1"
      ){
        this.dataFail = true;
      }else{
         http
           .post(
             "/payment/" +
               this.data.flightBooking.id +
               "/" +
               this.select.paymentWayId
           )
           .then(res => {
             console.log("Success");
             console.log(res.data);
             this.saveSuccess = true;
             //alert("บันทึกสำเร็จ ขอบคุณครับ/ค่ะ");
          })
           .catch(e =>{
             console.log(e)
            //alert("เกิดข้อผิดพลาด " + e);
          });
      }
    },
    savePaymentWithPromotionCode() {
      if (
        this.select.paymentWayId == "-1"
      ){
        alert("กรุณากรอกข้อมูลให้ครบ");
      }else{
         http
           .post(
             "/payment/" +
               this.data.flightBooking.id +
               "/" +
               this.select.paymentWayId +
               "/" +
               this.data.promotionCode
           )
           .then(res => {
             console.log("Success");
             console.log(res.data);
             //alert("บันทึกสำเร็จ ขอบคุณครับ/ค่ะ");
             this.saveSuccess = true;
          })
           .catch(e =>{
             console.log(e)
             //alert("เกิดข้อผิดพลาด " + e);
          });
      }
    },
    getDuration(end, start) {
      let arrive = new Date(end);
      let depart = new Date(start);
      const diffTime = Math.abs(arrive - depart);
      const diffMinute = Math.ceil(diffTime / (1000 * 60));
      const diffHour = Math.ceil(diffMinute / 60) - 1;
      const minute = diffMinute % 60;
      const hour = diffHour;
      if (diffHour == 0) {
        return minute + " นาที";
      }
      return hour + " ชม. " + minute + " นาที";
    },
    getFlightBooking() {
      http
      .get("/flight-booking/"+this.bookId)
      .then(res => {
        this.data.flightBooking = res.data
        console.log(this.data.flightBooking)
        this.flightBookingLoaded = true
      })
      .catch(e=>console.log(e))
    },
    getPaymentWay() {
      http
        .get("/payment-way")
        .then(res => {
          this.data.paymentWays = res.data;
          console.log(this.data.paymentWays);
          this.paymentWaysLoaded = true;
        })
        .catch(e => console.log(e));
    },
    getPaymentTypes() {
      http
        .get("/payment-type")
        .then(res => {
          this.data.paymentTypes = res.data;
          console.log(this.data.paymentTypes);
          this.paymentTypesLoaded = true;
        })
        .catch(e => console.log(e));
    }
  },
  created() {
    this.getFlightBooking();
    this.getPaymentTypes();
  }
};
</script>

<style scoped>
.flip {
  -webkit-transform: scaleX(-1);
  transform: scaleX(-1);
}
.header-bg {
  height: 300px;
  width: 100%;
  position: absolute;
  top: 1px;
  background-size: cover;
  /* background-color: rgb(8, 2, 43); */
  background-image: url("../assets/head-bg.jpg");
}
.content {
  z-index: 1;
  position: relative;
}
.my-right {
  position: absolute;
  right: 1px;
}
.my-content {
  position: absolute;
  top: 150px;
  width: 70%;
}
</style>