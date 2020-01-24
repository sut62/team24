<template>
  <div v-if="show = true">
    <div class="paymentBill">
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

            <div >
              <v-card class="card text-center pageA4">
                <br />
                <v-avatar class="logo mr-4" size="100">
                  <img src="../assets/logo.png" alt="John" />
                  <br />
                </v-avatar>
                <h3>Booking ID : {{[0].flightBooking.bookId}}</h3>
                <div class="myborder">
                  <v-row>
                    <v-icon slot="icon" size="36">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                  </v-row>
                  <p>dfvdf</p>
                  <p>
                    dvdfv
                    <v-icon color="error">mdi-arrow-right</v-icon>fdvd
                  </p>
                  <p>
                    fdvd - vdfvd
                    | dfvdf
                  </p>
                </div>
                <!-- ---------------------- -->
                <br />
                <div class="myborder">
                  <v-row>
                    <i class="airasia-icon icon-return-flight"></i>
                    <v-icon slot="icon" size="36" class="flip">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                  </v-row>
                  <p>dvdfv</p>
                  <p>
                    fdv
                    <v-icon color="error">mdi-arrow-right</v-icon>fdv
                  </p>
                  <p>
                    fvd - fdv
                    | fdv
                  </p>
                </div>
                <!-- ---------------------- -->
                <br />
                <div class="myborder">
                  <v-row>
                    <i class="airasia-icon icon-return-flight"></i>
                    <v-icon slot="icon" size="36" class="flip">mdi-cash-multiple</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Total Price</p>
                  </v-row>
                  <p>Subtotal : </p>
                  <p>Add ons :</p>
                  <p>Discount [ "NEWYEAR2020" ] :</p>
                  <p>Total :</p>
                  <p>Payment Way :</p>
                </div>
                <!-- ---------------------- -->
                <br>
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
var JsBarcode = require("jsbarcode");
import axios from "axios";
let axiosInstance = axios.create({
  baseURL: "http://localhost:9000/api",
  timeout: 120000,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-type": "application/json"
  }
});
export default {
  name: "paymentBill",
  components: {
    UserNavbar,
    UserFooter
  },
  data: () => ({
    // return {};
     payment: [],
       }),
    methods: {
      genBarcode() {
        JsBarcode("#barcode", "ASDSWA");
      },
      getPayment() {
      axiosInstance
        .get("/payment")
        .then(response => {
          this.payment = response.data;
          console.log(response.data);
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },
  },
   mounted() {
      this.getPayment();
    }
  
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
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
.myborder {
  border-style: solid;
  border-width: 3px;
  padding: 20px;
}
.logo {
  position: unset;
  top: 30px;
  left: 10%;
}
.barcode {
  margin-top: 10px;
  width: 70%;
  height: 50px;
}
</style>
