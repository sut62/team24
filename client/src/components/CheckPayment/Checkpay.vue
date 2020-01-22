<template>
<div>
    <v-row justify="center">
        <v-dialog v-model="openDialog" persistent max-width="1000px">

            <v-card>
                <v-card-title>
                    <span class="headline">ตรวจสอบการชำระเงิน</span>
                </v-card-title>
                <div class="container">
                    <div class="row">
                        <div class="col">

                            <label>ยังไม่ชำระ</label>
                            <div v-for="(flightBookings,index) in flightBookings" :key="index">
                                <v-card>
                                    <div class="my-img2 justify-content-between p-3 mb-2">

                                        <div>
                                            <div class="row">
                                                    <div class="col-sm">
                                                        {{flightBookings.user.firstName}}
                                                    </div>
                                                    <div class="col-sm">
                                                        
                                                    </div>
                                                    <div class="col-sm">
                                                        {{flightBookings.bookId}}
                                                    </div>
                                                </div>
                                            <p>
                                                <v-icon slot="icon" size="24">mdi-airplane-takeoff</v-icon>
                                                {{flightBookings.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{flightBookings.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                                            </p>
                                            <p>
                                                <v-icon slot="icon" size="24" class="flip">mdi-airplane-landing</v-icon>
                                                {{flightBookings.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{flightBookings.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                                            </p>

                                        </div>

                                    </div>
                                </v-card>
                            </div>
                        </div>

                        <div class="col">
                            <label>ชำระเงินแล้ว</label>
                            <div v-for="(payment,index) in payment" :key="index">
                                <v-card>
                                    <div class="my-img3 justify-content-between p-3 mb-2">

                                        <div>
                                            
                                                <div class="row">
                                                    <div class="col-sm">
                                                        {{payment.flightBooking.user.firstName}}
                                                    </div>
                                                    <div class="col-sm">
                                                        {{payment.flightBooking.flightBookingLinks[0].flight.price}}
                                                    </div>
                                                    <div class="col-sm">
                                                        {{payment.flightBooking.bookId}}
                                                    </div>
                                                </div>
                                            
                                            <p>
                                                {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                                            </p>
                                            <p>
                                                {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                                            </p>
                                        </div>

                                    </div>
                                </v-card>
                            </div>
                        </div>
                    </div>
                </div>

                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>

                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
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
                    this.flightBookings = res.data.reverse();
                })
                .catch(e => console.log(e));
        },
        getPay() {
            http
                .get(
                    "http://localhost:9000/api/payment"
                )
                .then(res => {
                    this.payment = res.data.reverse();
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
