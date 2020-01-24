<template>
<div>
    <div class="container">
        <div class="row">
            <div class="col">
                <div v-for="(flightBookings,index) in flightBookings" :key="index">
                    <v-card>
                        <div class=" justify-content-between p-3 mb-2">
                            <div>
                                <v-list-item two-line>
                                    <v-list-item-avatar size="48">
                                        <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture"/>
                                    </v-list-item-avatar>
                                    <v-list-item-action>
                                        <v-list-item-title><b>{{flightBookings.user.firstName}} {{flightBookings.user.lastName}}</b></v-list-item-title>
                                        <v-list-item-subtitle><b>Booking NO. </b>{{flightBookings.bookId}}</v-list-item-subtitle>
                                        <v-list-item-subtitle><b>สถานะการชำระเงิน: </b> <font style="color:red">ยังไม่ชำระ</font>  </v-list-item-subtitle>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                            <v-col>
                                                <v-icon color="blue darken-2">mdi-calendar-range </v-icon>{{flightBookings.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY | เครื่องออกเวลา HH:mm น.")}}<br><br>
                                                <v-icon color="orange draken-2">mdi-airplane-takeoff</v-icon>{{flightBookings.flightBookingLinks[0].flight.flightAirports[0].airport.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{flightBookings.flightBookingLinks[0].flight.flightAirports[1].airport.name}}
                                            </v-col>
                                            <v-col>
                                                <v-icon color="green darken-2">mdi-calendar-range </v-icon>{{flightBookings.flightBookingLinks[1].flight.arrive | moment("DD MMM YYYY | เครื่องถึงเวลา HH:mm น.")}}<br><br>
                                                <v-icon color="orange draken-2" class="flip">mdi-airplane-landing</v-icon>{{flightBookings.flightBookingLinks[1].flight.flightAirports[1].airport.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{flightBookings.flightBookingLinks[1].flight.flightAirports[0].airport.name}}
                                            </v-col>
                                    </v-list-item-content>
                                </v-list-item>
                                <p align="center" style="color:red"><b style="color:black">ค้างชำระ</b> <v-icon color="green darken-2">mdi-currency-usd </v-icon>  {{totalPrice(flightBookings)}}</p>
                            </div>
                        </div>
                    </v-card>
                </div>
            </div>
        </div>

        <div class="col">
                <div v-for="(payment,index) in payment" :key="index">
                    <v-card>
                        <div class=" justify-content-between p-3 mb-2">
                            <div>
                                <v-list-item two-line>
                                    <v-list-item-avatar size="48">
                                        <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture"/>
                                    </v-list-item-avatar>
                                    <v-list-item-action>
                                        <v-list-item-title> <b>{{payment.flightBooking.user.firstName}} {{payment.flightBooking.user.lastName}}</b></v-list-item-title>
                                        <v-list-item-subtitle> <b>Booking NO. </b>{{payment.flightBooking.bookId}}</v-list-item-subtitle>
                                        <v-list-item-subtitle><b>สถานะการชำระเงิน: </b> <font style="color:green">ชำระแล้ว</font> </v-list-item-subtitle>
                                    </v-list-item-action>
                                    <v-list-item-content>
                                            <v-col>
                                                <v-icon color="blue darken-2">mdi-calendar-range </v-icon>{{payment.flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY | เครื่องออกเวลา HH:mm น.")}}<br><br>
                                                <v-icon color="orange">mdi-airplane-takeoff</v-icon>{{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{payment.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.name}}
                                            </v-col>
                                            <v-col>
                                                <v-icon color="green darken-2">mdi-calendar-range </v-icon>{{payment.flightBooking.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY | เครื่องถึงเวลา HH:mm น.")}}<br><br>
                                                <v-icon color="orange" class="flip">mdi-airplane-takeoff</v-icon>{{payment.flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.name}}
                                                <v-icon color="error">mdi-arrow-right</v-icon>
                                                {{payment.flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.name}}
                                            </v-col>
                                    </v-list-item-content>
                                </v-list-item>
                                <div></div>
                            </div>

                            <div></div>
                        </div>
                    </v-card>
                </div>
            </div>
        
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
        totalPrice(flightBooking) {
            let sum =
                flightBooking.flightBookingLinks[0].flight.price +
                flightBooking.flightBookingLinks[1].flight.price;
            if(flightBooking.flightBookingLinks[0].baggageAddon != null){
                sum += flightBooking.flightBookingLinks[0].baggageAddon.price;

            }
            if(flightBooking.flightBookingLinks[1].baggageAddon != null){
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
                })
                .catch(e => console.log(e));
        }
    },
    mounted() {
        this.getFlightBooking();
        this.getPayment();
    },
    watch: {
        images() {
            // console.log("this.form");
        }
    },
    computed: {
        
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
    border-color: rgb(236, 60, 16);
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
.font-red{
    color: red;
}
</style>
