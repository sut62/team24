<template>
  <div class="payment">
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
              <v-row v-if="flightBookingLoaded" class="text-left pl-7">
                <v-col cols="6">
                  <v-row>
                    <v-icon slot="icon" size="36">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                  </v-row>
                  <p>{{data.flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY")}}</p>
                  <p>{{data.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.city.name + " -> " + data.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}</p>
                  <p>{{data.flightBooking.flightBookingLinks[0].flight.depart | moment("HH:mm")}} - {{data.flightBooking.flightBookingLinks[0].flight.arrive | moment("HH:mm")}}</p>
                  <p>{{getDuration(data.flightBooking.flightBookingLinks[0].flight.arrive,data.flightBooking.flightBookingLinks[0].flight.depart)}}</p>

                </v-col>
                <v-col>
                  <v-row>
                    <i class="airasia-icon icon-return-flight"></i>
                    <v-icon slot="icon" size="36" class="flip">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                  </v-row>
                  <p>{{data.flightBooking.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY")}}</p>
                  <p>{{data.flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.city.name + " -> " + data.flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.city.name}}</p>
                  <p>{{data.flightBooking.flightBookingLinks[1].flight.depart | moment("HH:mm")}} - {{data.flightBooking.flightBookingLinks[1].flight.arrive | moment("HH:mm")}}</p>

                  <p>{{getDuration(data.flightBooking.flightBookingLinks[1].flight.arrive,data.flightBooking.flightBookingLinks[1].flight.depart)}}</p>

                </v-col>
              </v-row>
            </v-card>
          </div>
          <!-- +++++++++++++++++++++++++++++++++++++++ -->
          <div>
            <v-row>
              <v-col cols="8">
                <div id="app">
                  <v-app id="inspire" class="mt-5">
                    <v-card>
                      <v-toolbar flat color="primary" dark>
                        <v-toolbar-title>Payment</v-toolbar-title>
                      </v-toolbar>
                      <v-tabs vertical>
                        <v-tab>
                          <v-icon left>mdi-store</v-icon>Over the counter
                        </v-tab>
                        <v-tab>
                          <v-icon left>mdi-monitor</v-icon>internet banking
                        </v-tab>

                        <v-tab-item>
                          <v-card flat>
                            <v-card-text>
                              <p>Please Select</p>

                              <v-radio-group v-model="select.paymentWayId" column>
                                <v-radio label="7-11 || เค้าเตอร์เซอร์วิส" value="radio-1"></v-radio>
                                <v-radio label="Tesco Lotus" value="radio-2"></v-radio>
                                <v-radio label="Big C" value="radio-3"></v-radio>
                                <v-radio label="true money" value="radio-4"></v-radio>
                              </v-radio-group>
                            </v-card-text>
                          </v-card>
                        </v-tab-item>
                        <v-tab-item>
                          <v-card flat>
                            <v-card-text>
                              <p>Please Select</p>

                              <v-radio-group v-model="select.paymentWayId" column>
                                <v-radio label="ธนาคารไทยพานิชย์" value="radio-5"></v-radio>
                                <v-radio label="ธนาคารทหารไทย" value="radio-6"></v-radio>
                                <v-radio label="ธนาคารกรุงเทพ" value="radio-7"></v-radio>
                                <v-radio label="ธนาคารกรุงไทย" value="radio-8"></v-radio>
                              </v-radio-group>
                            </v-card-text>
                          </v-card>
                        </v-tab-item>
                      </v-tabs>
                    </v-card>
                  </v-app>
                </div>
              </v-col>
              <!-- *************************************** -->
              <v-col>
                <v-card class="text-center mt-5">
                  <v-toolbar flat color="primary" dark>
                    <v-toolbar-title class="pt-1">
                      <v-row>
                        <v-col>
                          <h4 class="text-left pt-2">Total</h4>
                        </v-col>
                        <v-col>
                          <h4 class="pt-2">3,131.00</h4>
                        </v-col>
                        <v-col>
                          <h4 class="text-right pt-2">THB</h4>
                        </v-col>
                      </v-row>
                    </v-toolbar-title>
                  </v-toolbar>
                  <div class="px-3">
                    <div class="d-flex pt-2">
                      <div class="mr-auto p-2" style="color:grey">Subtotal</div>
                      <div class="p-2">3,131.00</div>
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
                        class="form-control"
                        placeholder="code"
                        aria-describedby="button-addon2"
                      />
                      <div class="input-group-append">
                        <button
                          class="btn btn-outline-secondary"
                          type="button"
                          id="button-addon2"
                        >Search</button>
                      </div>
                    </div>

                    <hr />

                    <div class="text-center p-3">
                      <v-btn large color="error">Purchase</v-btn>
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
        <UserFooter style="padding-bottom: 100px;" />
      </div>
    </div>
  </div>
</template>

<script>
import UserNavbar from "../components/UserNavbar";
import UserFooter from "../components/UserFooter";
import axios from "axios";

let http = axios.create({
  baseURL: 'http://localhost:9000/api',
  timeout: 120000,
  headers: {
    'Access-Control-Allow-Origin': '*',
    "Content-type": "application/json",
  }
})

export default {
  name: "payment",
  data: () => ({
    showVoucher: false,
    flightBookingLoaded:false,
    select: {
      paymentWayId: -1
    },
    data: {
      flightBooking:{}
    }
  }),
  components: {
    UserNavbar,
    UserFooter
  },
  computed: {
    
  },
  methods: {
    getDuration(end,start){
      let arrive = new Date(end)
      let depart = new Date(start)
      const diffTime = Math.abs(arrive - depart);
      const diffMinute = Math.ceil(diffTime / (1000 * 60));
      const diffHour = Math.ceil(diffMinute / 60) - 1;
      const minute = diffMinute % 60;
      const hour = diffHour
      if(diffHour == 0){
        return minute + " นาที"
      }
      return hour + " ชม. " + minute + " นาที"
    },
    getFlightBooking(){
      http
      .get("/flight-booking/1")
      .then(res => {
        this.data.flightBooking = res.data
        console.log(this.data.flightBooking)
        this.flightBookingLoaded = true
      })
      .catch(e=>console.log(e))
    }
  },
  created(){
    this.getFlightBooking()
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