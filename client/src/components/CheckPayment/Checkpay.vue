<template>
<div>
<div class="container">
  <div class="row">
    <div class="col">
        <p class="pl-3 pt-3" style="color:black">ยังไม่ชำระเงิน</p>
     <div v-for="(flightBookings,index) in flightBookings" :key="index">
                  <v-card>
                    <div class="my-img2 justify-content-between p-3 mb-2">
                      <div>
                        <div>{{flightBookings.user.firstName}}  {{flightBookings.user.lastName}}</div>
                        <div></div>
                      </div>
                      <div></div>
                    </div>
                  </v-card>
                </div>
    </div>
    <div class="col">
      <p class="pl-3 pt-3" style="color:black">ชำระเงินแล้ว</p>
    </div>
  </div>
</div>

</div>
</template>

<script>
import axios from "axios";
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
    name: "Checkpay",
    data: () => ({
        flightBookings: [],
        payment: [],
        price: ""

    }),
    props: {
        openDialog: {
            type: Boolean
        },
        closeDialog: {
            type: Function
        }
    },

    methods: {
        getFBooking() {
            http
                .get(
                    "http://localhost:9000/api/flight-booking"
                )
                .then(res => {
                    this.flightBookings = res.data;
                })
                .catch(e => console.log(e));
        },
        getPay() {
            http
                .get(
                    "http://localhost:9000/api/payment"
                )
                .then(res => {
                    this.payment = res.data;
                })
                .catch(e => console.log(e));
        }
    },
    mounted() {
        this.getFBooking();
        this.getPay();
    },
    watch: {
        images() {
            // console.log("this.form");
        }
    },
    computed: {
        totalPrice() {
      let sum =
        this.payment.flightBooking.flightBookingLinks[0].flight.price +
        this.payment.flightBooking.flightBookingLinks[1].flight.price;
        console.log(sum);
      return numeral(sum).format("0,0.00");
    }
    }

};
</script>

<style scoped>
.my-img {
    border-style: solid;
    padding: 10px;
    width: 18%;
    margin-left: 5px;
    border-radius: 10px;
}

.my-img2 {
    border-style: solid;
    border-color: red;
    padding: 10px;
    width: 100%;

}

.my-img3 {
    border-style: solid;
    border-color: green;
    padding: 10px;
    width: 100%;
}

.my-img:hover {
    border-color: red;
    border-style: solid;
    padding: 10px;
    width: 18%;
    margin-left: 5px;
    border-radius: 20px;
}
</style>
