<template>
  <div>
    <Alert :open="saveSuccess" topic="แจ้งเตือน" desc="บันทึกสำเร็จ" :callback="closeSaveAlert" />
    <Alert
      :open="dataFail"
      topic="แจ้งเตือน"
      desc="กรุณากรอกข้อมูลให้ครบ"
      :callback="()=>this.dataFail = false"
    />
    <Alert
      :open="codeNotFound"
      topic="แจ้งเตือน"
      desc="Code ไม่ถูกต้อง"
      :callback="()=>this.codeNotFound = false"
    />
    <div class="payment" v-if="checkToLoad() && paymentPage == true">
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

            <div
              v-if="data.flightBooking.flightBookingLinks[0].baggageAddon != null || data.flightBooking.flightBookingLinks[1].baggageAddon != null"
            >
              <v-card class="text-center mt-4">
                <v-toolbar flat color="primary" dark>
                  <v-toolbar-title>Baggage Addon details ( สัมภาระพกพา 7 kg รวมอยู่ในค่าโดยสารแล้ว )</v-toolbar-title>
                </v-toolbar>
                <v-row class="text-left pl-7">
                  <v-col
                    v-if="data.flightBooking.flightBookingLinks[0].baggageAddon != null"
                    cols="6"
                  >
                    <v-row>
                      <v-icon slot="icon" size="36">mdi-bag-checked</v-icon>
                      <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                    </v-row>
                    <p>สัมภาระเช็คอิน : {{data.flightBooking.flightBookingLinks[0].baggageAddon.maxWeight}} kg</p>
                    <p>ราคา : {{data.flightBooking.flightBookingLinks[0].baggageAddon.price}} THB</p>
                  </v-col>
                  <v-col v-if="data.flightBooking.flightBookingLinks[1].baggageAddon != null">
                    <v-row>
                      <i class="airasia-icon icon-return-flight"></i>
                      <v-icon slot="icon" size="36">mdi-bag-checked</v-icon>
                      <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                    </v-row>
                    <p>สัมภาระเช็คอิน : {{data.flightBooking.flightBookingLinks[1].baggageAddon.maxWeight}} kg</p>
                    <p>ราคา : {{data.flightBooking.flightBookingLinks[1].baggageAddon.price}} THB</p>
                  </v-col>
                </v-row>
              </v-card>
            </div>
            <!-- +++++++++++++++++++++++++++++++++++++++ -->
            <div>
              <v-card class="text-center mt-4">
                <v-toolbar flat color="primary" dark>
                  <v-toolbar-title>Contact info</v-toolbar-title>
                </v-toolbar>
                <v-row class="pl-3">
                  <v-col cols="12" md="4">
                    <v-text-field
                      v-model="phone"
                      :rules="phoneRules"
                      :counter="10"
                      label="เบอร์โทรติดต่อ"
                      required
                      outlined
                    ></v-text-field>
                  </v-col>
                  <v-col cols="12" md="4">
                    <v-text-field
                      v-model="email"
                      :rules="emailRules"
                      label="อีเมลล์ที่ใช้ติดต่อ"
                      required
                      outlined
                    ></v-text-field>
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
                      <div
                        class="d-flex pt-2"
                        v-if="data.flightBooking.flightBookingLinks[0].baggageAddon != null 
                        || data.flightBooking.flightBookingLinks[1].baggageAddon != null"
                      >
                        <div class="mr-auto p-2" style="color:grey">Baggage Addon</div>
                        <div class="p-2">{{addonPrice}}</div>
                      </div>
                      <div class="d-flex pt-2" v-if="this.discountPer">
                        <div class="mr-auto p-2" style="color:grey">Discount</div>
                        <div class="p-2">{{getDiscount}}</div>
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

    <!-- BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL BILL -->
    <div v-if="billPage == true">
      <div class="header-bg"></div>
      <div class="content">
        <UserNavbar />
        <div class="container" style="margin-top:100px;">
          <div class="mx-auto">
            <div>
              <div class="text-white">
                <div>
                  <h1 class="mt-5">Your Payment Bill</h1>
                </div>
              </div>
            </div>
            <div v-if="this.paymentSaved == null">
              <v-card class="text-center p-5">
                <br><br>
                <h1>ไม่พบข้อมูล</h1>
                <br><br><br><br><br><br><br>
              </v-card>
            </div>

            <div v-else>
              <v-card class="card text-center pageA4">
                <br />
                <v-avatar class="logo mr-4" size="100">
                  <img src="../assets/logo.png" alt="John" />
                  <br />
                </v-avatar>
                <h3>Booking ID : {{this.paymentSaved.flightBooking.bookId}}</h3>
                <!-- ------------------------------ -->
                <div class="myborder">
                  <v-row class="text-left pl-7">
                    <v-col cols="6">
                      <v-row>
                        <v-icon slot="icon" size="36">mdi-airplane-takeoff</v-icon>
                        <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                      </v-row>
                      <p>{{this.paymentSaved.flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY")}}</p>
                      <p>
                        {{this.paymentSaved.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                        <v-icon color="error">mdi-arrow-right</v-icon>
                        {{this.paymentSaved.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                      </p>
                      <p>
                        {{this.paymentSaved.flightBooking.flightBookingLinks[0].flight.depart | moment("HH:mm")}} - {{this.paymentSaved.flightBooking.flightBookingLinks[0].flight.arrive | moment("HH:mm")}}
                        | {{getDuration(this.paymentSaved.flightBooking.flightBookingLinks[0].flight.arrive,data.flightBooking.flightBookingLinks[0].flight.depart)}}
                      </p>
                    </v-col>
                    <v-col>
                      <v-row>
                        <i class="airasia-icon icon-return-flight"></i>
                        <v-icon slot="icon" size="36" class="flip">mdi-airplane-takeoff</v-icon>
                        <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                      </v-row>
                      <p>{{this.paymentSaved.flightBooking.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY")}}</p>
                      <p>
                        {{this.paymentSaved.flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.city.name}}
                        <v-icon color="error">mdi-arrow-right</v-icon>
                        {{this.paymentSaved.flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.city.name}}
                      </p>
                      <p>
                        {{this.paymentSaved.flightBooking.flightBookingLinks[1].flight.depart | moment("HH:mm")}} - {{this.paymentSaved.flightBooking.flightBookingLinks[1].flight.arrive | moment("HH:mm")}}
                        | {{getDuration(this.paymentSaved.flightBooking.flightBookingLinks[1].flight.arrive,data.flightBooking.flightBookingLinks[1].flight.depart)}}
                      </p>
                    </v-col>
                  </v-row>
                </div>
                <!-- ------------------------------ -->
                <br />
                <div
                  class="myborder"
                  v-if="this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon != null 
                || this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon != null"
                >
                  <v-row class="text-left pl-7">
                    <v-col
                      v-if="this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon != null"
                      cols="6"
                    >
                      <v-row>
                        <v-icon slot="icon" size="36">mdi-bag-checked</v-icon>
                        <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                      </v-row>
                      <p>สัมภาระเช็คอิน : {{this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon.maxWeight}} kg</p>
                      <p>ราคา : {{this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon.price}} THB</p>
                    </v-col>
                    <v-col
                      v-if="this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon != null"
                    >
                      <v-row>
                        <i class="airasia-icon icon-return-flight"></i>
                        <v-icon slot="icon" size="36">mdi-bag-checked</v-icon>
                        <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                      </v-row>
                      <p>สัมภาระเช็คอิน : {{this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon.maxWeight}} kg</p>
                      <p>ราคา : {{this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon.price}} THB</p>
                    </v-col>
                  </v-row>
                </div>
                <!-- ---------------------- -->
                <br />
                <div class="myborder">
                  <v-row class="pl-7">
                    <i class="airasia-icon icon-return-flight"></i>
                    <v-icon slot="icon" size="36" class="flip">mdi-cash-multiple</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Total Price</p>
                  </v-row>
                  <div>Subtotal : {{subtotalSaved}} THB</div>
                  <div
                    v-if="this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon != null 
                  && this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon != null"
                  >Add ons : {{totalAddonSaved}} THB</div>
                  <div v-if="this.paymentSaved.promotionCode != null">
                    <p>Discount [ {{this.paymentSaved.promotionCode.discount}} % ] : {{discountSaved}}</p>
                  </div>
                  <hr />
                  <p>Total : {{this.paymentSaved.amount}} THB</p>
                  <p>Payment Way : {{this.paymentSaved.paymentWay.name}}</p>
                </div>
                <!-- ---------------------- -->
                <br />
                <div>
                  <img class="barcode" src="../assets/barcode2D.png" />
                </div>
              </v-card>

              <!-- +++++++++++++++++++++++++++++++++++++++++++ -->
            </div>
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
import axios from "axios";
import Alert from "../components/Alert";
import router from "../router";
import { mapState } from "vuex";
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
  props: {
    bookId: {
      type: String
    }
  },
  data: () => ({
    paymentPage: true,
    billPage: false,
    paymentSaved: null,
    paymentBill: null,
    saveSuccess: false,
    dataFail: false,
    codeNotFound: false,
    showVoucher: false,
    flightBookingLoaded: false,
    paymentWaysLoaded: false,
    paymentTypesLoaded: false,
    phone: "",
    email: "",
    phoneRules: [
      v => !!v || "ท่านจำเป็นต้องกรอกเบอร์โทร",
      v => v.length == 10 || "กรุณากรอกให้ครบ 10 หลัก"
    ],
    emailRules: [
      v => !!v || "จำเป็นต้องกรอก E-mail",
      v => /.+@.+/.test(v) || "กรุณากรอก E-mail ให้ถูกต้อง"
    ],
    select: {
      paymentWayId: -1
    },
    data: {
      flightBooking: {},
      paymentWays: [], // no used
      paymentTypes: [],
      promotionCode: ""
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
    ...mapState({
      user: state => state.Auth.user,
      logedIn: state => state.Auth.logedIn
    }),

    getDiscount() {
      let sum =
        this.data.flightBooking.flightBookingLinks[0].flight.price +
        this.data.flightBooking.flightBookingLinks[1].flight.price;
      if (this.data.flightBooking.flightBookingLinks[0].baggageAddon != null) {
        sum += this.data.flightBooking.flightBookingLinks[0].baggageAddon.price;
      }
      if (this.data.flightBooking.flightBookingLinks[1].baggageAddon != null) {
        sum += this.data.flightBooking.flightBookingLinks[1].baggageAddon.price;
      }
      let discount = (sum * this.discountPer) / 100;
      return numeral(discount).format("0,0.00");
    },
    getDiff() {
      let sum =
        this.data.flightBooking.flightBookingLinks[0].flight.price +
        this.data.flightBooking.flightBookingLinks[1].flight.price;
      if (this.data.flightBooking.flightBookingLinks[0].baggageAddon != null) {
        sum += this.data.flightBooking.flightBookingLinks[0].baggageAddon.price;
      }
      if (this.data.flightBooking.flightBookingLinks[1].baggageAddon != null) {
        sum += this.data.flightBooking.flightBookingLinks[1].baggageAddon.price;
      }
      let discount = (sum * this.discountPer) / 100;
      let diff = sum - discount;
      return numeral(diff).format("0,0.00");
    },
    totalPrice() {
      let sum =
        this.data.flightBooking.flightBookingLinks[0].flight.price +
        this.data.flightBooking.flightBookingLinks[1].flight.price;
      return numeral(sum).format("0,0.00");
    },
    addonPrice() {
      let sum = 0;
      if (this.data.flightBooking.flightBookingLinks[0].baggageAddon != null) {
        sum += this.data.flightBooking.flightBookingLinks[0].baggageAddon.price;
      }
      if (this.data.flightBooking.flightBookingLinks[1].baggageAddon != null) {
        sum += this.data.flightBooking.flightBookingLinks[1].baggageAddon.price;
      }
      return numeral(sum).format("0,0.00");
    },
    subtotalSaved() {
      let sum =
        this.paymentSaved.flightBooking.flightBookingLinks[0].flight.price +
        this.paymentSaved.flightBooking.flightBookingLinks[1].flight.price;
      return numeral(sum).format("0,0.00");
    },
    discountSaved() {
      let sum =
        this.paymentSaved.flightBooking.flightBookingLinks[0].flight.price +
        this.paymentSaved.flightBooking.flightBookingLinks[1].flight.price;
      if (
        this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon !=
        null
      ) {
        sum += this.paymentSaved.flightBooking.flightBookingLinks[0]
          .baggageAddon.price;
      }
      if (
        this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon !=
        null
      ) {
        sum += this.paymentSaved.flightBooking.flightBookingLinks[1]
          .baggageAddon.price;
      }
      let discount = (sum * this.paymentSaved.promotionCode.discount) / 100;
      return numeral(discount).format("0,0.00");
    },
    totalAddonSaved() {
      let sum = 0;
      if (
        this.paymentSaved.flightBooking.flightBookingLinks[0].baggageAddon !=
        null
      ) {
        sum += this.paymentSaved.flightBooking.flightBookingLinks[0]
          .baggageAddon.price;
      }
      if (
        this.paymentSaved.flightBooking.flightBookingLinks[1].baggageAddon !=
        null
      ) {
        sum += this.paymentSaved.flightBooking.flightBookingLinks[1]
          .baggageAddon.price;
      }
      return numeral(sum).format("0,0.00");
    }
  },

  methods: {
    closeSaveAlert() {
      this.saveSuccess = false;
      this.onShowBill();
    },
    onShowBill() {
      console.log("asdsadasdsa");
      this.paymentPage = false;
      this.billPage = true;
    },

    save() {
      if (this.discountPer > 0) {
        this.savePaymentWithPromotionCode();
      } else this.savePayment();
    },
    goToPaymentBill() {
      router.push({ name: "paymentBill" });
    },
    checkPromotion() {
      this.data.promotionCode; 
      http
        .get("/promotion-code/" + this.data.promotionCode)
        .then(res => {
          console.log(res.data);
          if (this.isRealValue(res.data)) this.discountPer = res.data.discount;
          else this.codeNotFound = true;
        })
        .catch(e => {
          console.log(e);
        });
    },

    checkToLoad() {
      if (this.flightBookingLoaded && this.paymentTypesLoaded) {
        if (!this.isRealValue(this.data.flightBooking)) {
          // alert("Book ID " + this.bookId + " not founded");
          router.push({ name: "bookFlight" });
        }
        return true;
      }
      return false;
    },
    isRealValue(obj) {
      return obj && obj !== "null" && obj !== "undefined";
    },
    savePayment() {
      if (this.select.paymentWayId == "-1") {
        this.dataFail = true;
      } else {
        http
          .post(
            "/payment/" +
              this.data.flightBooking.id +
              "/" +
              this.select.paymentWayId +
              "/" +
              this.phone +
              "/" +
              this.email
          )
          .then(res => {
            11;
            console.log("Success");
            console.log(res.data);
            this.saveSuccess = true;
            this.paymentSaved = res.data;
            this.findLastSave();
            //alert("บันทึกสำเร็จ ขอบคุณครับ/ค่ะ");
          })
          .catch(e => {
            console.log(e);
            //alert("เกิดข้อผิดพลาด " + e);
          });
      }
    },
    savePaymentWithPromotionCode() {
      if (this.select.paymentWayId == "-1") {
        alert("กรุณากรอกข้อมูลให้ครบ");
      } else {
        http
          .post(
            "/payment/" +
              this.data.flightBooking.id +
              "/" +
              this.select.paymentWayId +
              "/" +
              this.data.promotionCode +
              "/" +
              this.phone +
              "/" +
              this.email
          )
          .then(res => {
            console.log("Success");
            console.log(res.data);
            //alert("บันทึกสำเร็จ ขอบคุณครับ/ค่ะ");
            this.saveSuccess = true;
            this.paymentSaved = res.data;
            this.findLastSave();
          })
          .catch(e => {
            console.log(e);
            //alert("เกิดข้อผิดพลาด " + e);
          });
      }
    },
    findLastSave() {
      http
        .post("/" + this.paymentSaved.id)
        .then(res => {
          console.log(res.data);
          this.paymentBill = res.data;
        })
        .catch(e => {
          console.log(e);
        });
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
        .get("/flight-booking/" + this.bookId)
        .then(res => {
          this.data.flightBooking = res.data;
          console.log(this.data.flightBooking);
          this.flightBookingLoaded = true;
        })
        .catch(e => console.log(e));
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
    if (this.bookId == "TESTFAIL00") {
      this.onShowBill();
    } else {
      this.getFlightBooking();
      this.getPaymentTypes();
    }
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
.pageA4 {
  width: 210mm;
  min-height: 297mm;
  padding: 10mm;
  margin: 10mm auto;
  border: 1px #d3d3d3 solid;
  border-radius: 5px;
  background: white;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}
.barcode {
  margin-top: 10px;
  width: 70%;
  height: 50px;
}
.logo {
  position: unset;
  top: 30px;
  left: 10%;
}
.myborder {
  border-style: solid;
  border-width: 3px;
  padding: 20px;
}
</style>