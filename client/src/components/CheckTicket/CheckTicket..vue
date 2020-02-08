<template>
<div>
    <div class="container" v-if="data == true">
        <center>
            <font size="20">Data not found</font>
        </center>
    </div>

    <div class="container" v-if="data == false">
        <v-row>
            <v-col>
                <div v-for="(flightBookings,index) in flightBookings" :key="index">
                    <v-card tile max-width="1200" class="mx-auto">
                        <div class="p-2 mb-2">
                            <v-list-item two-line>
                                <v-list-item-avatar size="48">
                                    <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
                                </v-list-item-avatar>
                                <v-list-item-action>
                                    <v-list-item-title><b>{{flightBookings.user.firstName}} {{flightBookings.user.lastName}}</b></v-list-item-title>
                                    <v-list-item-subtitle><b>Booking NO. </b>{{flightBookings.bookId}}</v-list-item-subtitle>
                                    <v-list-item-subtitle><b>วันที่ทำการจอง </b>{{flightBookings.date | moment("DD MMM YYYY | HH:mm น.")}} </v-list-item-subtitle>
                                    <v-list-item-subtitle><b>สถานะการชำระเงิน: </b>
                                        <font style="color:red">ยังไม่ชำระ</font>
                                    </v-list-item-subtitle>
                                </v-list-item-action>
                                <v-divider class="mx-5" inset vertical></v-divider>
                                <div align="center">
                                    <v-list-item-content>
                                        <v-col>
                                            <h4>DEPART</h4>
                                            <v-icon color="blue darken-2">mdi-calendar-range </v-icon> {{flightBookings.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY | เครื่องออกเวลา HH:mm น.")}}<br><br>
                                            <v-icon color="orange draken-2">mdi-airplane-takeoff</v-icon> {{flightBookings.flightBookingLinks[0].flight.flightAirports[0].airport.name}}
                                            <v-icon color="error">mdi-arrow-right-bold</v-icon>
                                            {{flightBookings.flightBookingLinks[0].flight.flightAirports[1].airport.name}}
                                        </v-col>
                                        <v-col>
                                            <h4>ARRIVE</h4>
                                            <v-icon color="green darken-2">mdi-calendar-range </v-icon> {{flightBookings.flightBookingLinks[1].flight.arrive | moment("DD MMM YYYY | เครื่องถึงเวลา HH:mm น.")}}<br><br>
                                            <v-icon color="orange draken-2" class="flip">mdi-airplane-landing</v-icon> {{flightBookings.flightBookingLinks[1].flight.flightAirports[1].airport.name}}
                                            <v-icon color="error">mdi-arrow-right-bold</v-icon>
                                            {{flightBookings.flightBookingLinks[1].flight.flightAirports[0].airport.name}}
                                        </v-col>
                                        <div align="center">
                                            <h4>
                                                ค้างชำระ
                                                <v-icon color="green darken-2">mdi-currency-usd </v-icon>
                                                <font color="red">{{totalPrice(flightBookings)}}</font>
                                            </h4>
                                        </div>
                                    </v-list-item-content>
                                </div>
                            </v-list-item>
                        </div>
                    </v-card>
                </div>
            </v-col>
        </v-row>

        <v-col>
            <div v-for="(payment,index) in payment" :key="index">
                <v-card tile max-width="1200" center class="mx-auto">
                    <div class="p-2 mb-2">
                        <v-list-item two-line>
                            <v-list-item-avatar size="48">
                                <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
                            </v-list-item-avatar>
                            <v-list-item-action>
                                <v-list-item-title> <b>{{payment.flightBooking.user.firstName}} {{payment.flightBooking.user.lastName}}</b></v-list-item-title>
                                <v-list-item-subtitle> <b>Booking NO. </b>{{payment.flightBooking.bookId}}</v-list-item-subtitle>
                                <v-list-item-subtitle><b>วันที่ทำการจอง: </b>{{payment.flightBooking.date | moment("DD MMM YYYY | HH:mm น.")}} </v-list-item-subtitle>
                                <v-list-item-subtitle><b>สถานะการชำระเงิน: </b>
                                    <font style="color:green">ชำระแล้ว</font>
                                </v-list-item-subtitle>
                                <v-list-item-subtitle> <b>วันและเวลาที่ชำระเงิน </b>{{payment.payDate | moment("DD MMM YYYY | HH:mm น.")}}</v-list-item-subtitle>
                            </v-list-item-action>
                            <v-divider class="mx-5" inset vertical></v-divider>
                            <v-list-item-content>
                                <v-col>
                                    <h4>DEPART</h4>
                                    <v-icon color="blue darken-2">mdi-calendar-range </v-icon> {{payment.flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY | เครื่องออกเวลา HH:mm น.")}}<br><br>
                                    <v-icon color="orange">mdi-airplane-takeoff</v-icon> {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.name}}
                                    <v-icon color="error">mdi-arrow-right-bold</v-icon>
                                    {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.name}}
                                </v-col>
                                <v-col>
                                    <h4>ARRIVE</h4>
                                    <v-icon color="green darken-2">mdi-calendar-range </v-icon> {{payment.flightBooking.flightBookingLinks[1].flight.arrive | moment("DD MMM YYYY | เครื่องถึงเวลา HH:mm น.")}}<br><br>
                                    <v-icon color="orange" class="flip">mdi-airplane-landing</v-icon> {{payment.flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.name}}
                                    <v-icon color="error">mdi-arrow-right-bold</v-icon>
                                    {{payment.flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.name}}
                                </v-col>
                            </v-list-item-content>
                        </v-list-item>
                    </div>
                </v-card>
            </div>
        </v-col>
    </div>
</div>
</template>

<script>
import axios from "axios";
var numeral = require("numeral");

import authHeader from '../../api/Auth_header'

let auth = authHeader()
let http = axios.create({
    baseURL: 'http://localhost:9000/api',
    timeout: 120000,
    headers: {
        'Access-Control-Allow-Origin': '*',
        "Content-type": "application/json",
        ...auth
    }
})

export default {
    name: "CheckTicket",
    props: {
        mode: {
            type: String
        }
    },
    data: () => ({
        justify: [
            'start',
            'end',
            'center',
            'space-between',
            'space-around',
        ],
        flightBookings: [],
        payment: [],
        price: "",
        data: true

    }),

    methods: {
        totalPrice(flightBooking) {
            let sum =
                flightBooking.flightBookingLinks[0].flight.price +
                flightBooking.flightBookingLinks[1].flight.price;
            if (flightBooking.flightBookingLinks[0].baggageAddon != null) {
                sum += flightBooking.flightBookingLinks[0].baggageAddon.price;

            }
            if (flightBooking.flightBookingLinks[1].baggageAddon != null) {
                sum += flightBooking.flightBookingLinks[1].baggageAddon.price;

            }
            console.log(sum);
            return numeral(sum).format("0,0");
        },
        getFlightBooking() {
            http
                .get(
                    "http://localhost:9000/api/flight-booking/pending/user"
                )
                .then(res => {
                    this.flightBookings = res.data;
                    this.data = false;
                })
                .catch(e => console.log(e));

        },
        getPayment() {
            http
                .get(
                    "http://localhost:9000/api/payment"
                )
                .then(res => {
                    this.payment = res.data;
                    this.data = false;
                })
                .catch(e => console.log(e));
        }
    },
    mounted() {
        if (this.mode != "testfail") {
            this.getFlightBooking();
            this.getPayment();
        }
    },

};
</script>

<style scoped>

</style>
