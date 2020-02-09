<template>
<div>
     <div class="container" v-if="data == true">
         <center ><font size="20">Data not found</font></center>
      </div>
    
    <div class="container" v-if="data == false">
        <div class="row">
            <div class="col">

                <p class="pl-3 pt-3" style="color:black">ยังไม่ชำระเงิน</p>
                
                <div v-for="(flightBookings,index) in flightBookings" :key="index">
                    <v-card>

                        <div class="my-img2 justify-content-between p-2 mb-2">
                            <v-row>
                                <v-col>
                                    <v-icon color="green">mdi-view-list</v-icon>{{flightBookings.bookId}}
                                </v-col>
                                <v-col lg="7">{{flightBookings.user.firstName}} {{flightBookings.user.lastName}}</v-col>
                                <v-col lg="1">

                                    <v-icon color="blue" size=20 @click.stop="openbookingDialog(flightBookings)">mdi-magnify</v-icon>

                                </v-col>
                            </v-row>

                            <div></div>
                        </div>

                    </v-card>
                </div>
            </div>
            <div class="col">
                <p class="pl-3 pt-3" style="color:black">ชำระเงินแล้ว</p>

                <div v-for="(payment,index) in payment" :key="index">
                    <v-card>

                        <div class="my-img3 justify-content-between p-2 mb-2">
                            <v-row>
                                <v-col>
                                    <v-icon color="green">mdi-view-list</v-icon>{{payment.flightBooking.bookId}}
                                </v-col>
                                <v-col lg="7">{{payment.flightBooking.user.firstName}} {{payment.flightBooking.user.lastName}}</v-col>
                                <v-col lg="1">

                                    <v-icon color="blue" size=20 @click.stop="openpaymentDialog(payment)">mdi-magnify</v-icon>

                                </v-col>
                            </v-row>

                            <div></div>
                        </div>

                    </v-card>
                </div>
            </div>
        </div>
    </div>
    <v-dialog v-if="dialog" v-model="dialog" max-width="">
        <v-card>
            <v-card-title class="headline">{{temppayment.flightBooking.bookId}} ________________________________________________________________________________________________<v-icon color="teal darken-2" size="40">mdi-currency-usd </v-icon>ราคารวม : {{temppayment.amount}}</v-card-title>

            <div>
                <v-list-item two-line>
                    <v-list-item-action>
                        <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" width="" height="100" />
                        <v-list-item>{{temppayment.flightBooking.user.firstName}} <br> {{temppayment.flightBooking.user.lastName}}</v-list-item>
                        <v-list-item>ชำระเงินแล้ว</v-list-item>
                        <v-list-item>{{temppayment.paymentWay.name}}</v-list-item>
                        

                    </v-list-item-action>
                    <v-list-item-content>
                        <div class="text-card">
                            <v-col>
                                <v-icon color="green darken-2">mdi-calendar-range </v-icon> {{temppayment.flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY")}}<br>
                                <v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> {{temppayment.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.name}}
                                <v-icon color="error">mdi-arrow-right</v-icon>
                                {{temppayment.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.name}}
                            </v-col>
                            <v-col>
                                <v-icon color="green darken-2">mdi-calendar-range </v-icon> {{temppayment.flightBooking.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY")}}<br>
                                <v-icon color="blue darken-2" class="flip">mdi-airplane-takeoff</v-icon> {{temppayment.flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.name}}
                                <v-icon color="error">mdi-arrow-right</v-icon>
                                {{temppayment.flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.name}}
                            </v-col>
                            <v-col>
                                <v-icon color="teal darken-2">mdi-currency-usd </v-icon> {{totalPrice1(temppayment)}}<hr>
                                วันที่จำการจองตั๋วเครื่องบิน: {{temppayment.flightBooking.date | moment("DD MMM YYYY LT")}}<br><br>
                                วันที่ทำการชำระเงิน: {{temppayment.payDate| moment("DD MMM YYYY LT")}}
                            </v-col>
                        </div>
                    </v-list-item-content>
                    <v-list-item-content>
                        <div v-if="temppayment.flightBooking.flightBookingLinks[0].baggageAddon != null 
                || temppayment.flightBooking.flightBookingLinks[1].baggageAddon != null">

                            <v-col v-if="temppayment.flightBooking.flightBookingLinks[0].baggageAddon != null" cols="6">
                                <v-row>
                                    <v-icon color="blue darken-2">mdi-bag-checked</v-icon>
                                    <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                                </v-row>
                                <p>สัมภาระเช็คอิน : {{temppayment.flightBooking.flightBookingLinks[0].baggageAddon.maxWeight}} kg</p>
                                <p>ราคา : {{temppayment.flightBooking.flightBookingLinks[0].baggageAddon.price}} THB</p>
                                
                            </v-col>
                            <v-col v-if="temppayment.flightBooking.flightBookingLinks[1].baggageAddon != null">
                                <v-row>
                                    <i class="airasia-icon icon-return-flight"></i>
                                    <v-icon color="blue darken-2">mdi-bag-checked</v-icon>
                                    <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                                </v-row>
                                <p>สัมภาระเช็คอิน : {{temppayment.flightBooking.flightBookingLinks[1].baggageAddon.maxWeight}} kg</p>
                                <p>ราคา : {{temppayment.flightBooking.flightBookingLinks[1].baggageAddon.price}} THB</p>
                            </v-col>

                        </div>
                    </v-list-item-content>
                </v-list-item>
                <div></div>
            </div>

            <v-card-actions>
                <v-spacer></v-spacer>

            </v-card-actions>
        </v-card>
    </v-dialog>

    <v-dialog v-if="dialog1" v-model="dialog1" max-width="">
        <v-card>
            <v-card-title class="headline">{{tempbook.bookId}} ________________________________________________________________________________________________<v-icon color="teal darken-2" size="40">mdi-currency-usd </v-icon>ราคารวม : {{totalPriceAll(tempbook)}}</v-card-title>

            <div>
                <v-list-item three-line>
                    <v-list-item-action>
                        <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" width="" height="100" />
                        <v-list-item>{{tempbook.user.firstName}} <br> {{tempbook.user.lastName}}</v-list-item>
                        <v-list-item>ยังไม่ชำระเงิน</v-list-item>
                        
                    </v-list-item-action>
                    <v-list-item-content>
                        <div class="text-card">
                            <v-col>
                                <v-icon color="green darken-2">mdi-calendar-range </v-icon> {{tempbook.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY")}}<br>
                                <v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> {{tempbook.flightBookingLinks[0].flight.flightAirports[0].airport.name}}
                                <v-icon color="error">mdi-arrow-right</v-icon>
                                {{tempbook.flightBookingLinks[0].flight.flightAirports[1].airport.name}}
                            </v-col>
                            <v-col>
                                <v-icon color="green darken-2">mdi-calendar-range </v-icon> {{tempbook.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY")}}<br>
                                <v-icon color="blue darken-2" class="flip">mdi-airplane-takeoff</v-icon> {{tempbook.flightBookingLinks[1].flight.flightAirports[0].airport.name}}
                                <v-icon color="error">mdi-arrow-right</v-icon>
                                {{tempbook.flightBookingLinks[1].flight.flightAirports[1].airport.name}}
                            </v-col>
                            <v-col>
                                <v-icon color="teal darken-2">mdi-currency-usd </v-icon> {{totalPrice(tempbook)}} THB<hr>
                                วันที่จำการจองตั๋วเครื่องบิน: {{tempbook.date | moment("DD MMM YYYY LT")}}
                            </v-col>
                        </div>
                    </v-list-item-content>
                    <v-list-item-content>
                        <div v-if="tempbook.flightBookingLinks[0].baggageAddon != null 
                || tempbook.flightBookingLinks[1].baggageAddon != null">

                            <v-col v-if="tempbook.flightBookingLinks[0].baggageAddon != null" cols="6">
                                <v-row>
                                    <v-icon color="blue darken-2">mdi-bag-checked</v-icon>
                                    <p class="pl-3 pt-3" style="color:grey">Depart Date</p>
                                </v-row>
                                <p>สัมภาระเช็คอิน : {{tempbook.flightBookingLinks[0].baggageAddon.maxWeight}} kg</p>
                                <p>ราคา : {{tempbook.flightBookingLinks[0].baggageAddon.price}} THB</p>
                            </v-col>
                            <v-col v-if="tempbook.flightBookingLinks[1].baggageAddon != null">
                                <v-row>
                                    <i class="airasia-icon icon-return-flight"></i>
                                    <v-icon color="blue darken-2">mdi-bag-checked</v-icon>
                                    <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                                </v-row>
                                <p>สัมภาระเช็คอิน : {{tempbook.flightBookingLinks[1].baggageAddon.maxWeight}} kg</p>
                                <p>ราคา : {{tempbook.flightBookingLinks[1].baggageAddon.price}} THB</p>
                            </v-col>

                        </div>
                    </v-list-item-content>
                </v-list-item>
                <div></div>
            </div>

            <v-card-actions>
                <v-spacer></v-spacer>

            </v-card-actions>
        </v-card>
    </v-dialog>
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
     props: {
    mode: {
      type: String
    }
  },
    data: () => ({
        tempayment: null,
        tempbook: null,
        dialog: false,
        dialog1: false,
        flightBookings: [],
        payment: [],
        price: "",
        data: true
    }),

    methods: {

        openbookingDialog(bookfight) {
            this.dialog1 = true;
            this.tempbook = bookfight;
        },

        openpaymentDialog(paymentfight) {
            this.dialog = true;
            this.temppayment = paymentfight;
        },
        totalPrice(flightBooking) {
            let sum =
                flightBooking.flightBookingLinks[0].flight.price +
                flightBooking.flightBookingLinks[1].flight.price;

            return numeral(sum).format("0,0");
        },
        totalPrice1(pay) {
            let sum =
                pay.flightBooking.flightBookingLinks[0].flight.price +
                pay.flightBooking.flightBookingLinks[1].flight.price;

            return numeral(sum).format("0,0");
        },
        totalPriceAll(flightBooking) {
            let sum =
                flightBooking.flightBookingLinks[0].flight.price +
                flightBooking.flightBookingLinks[1].flight.price;
            if (flightBooking.flightBookingLinks[0].baggageAddon != null) {
                sum += flightBooking.flightBookingLinks[0].baggageAddon.price;

            }
            if (flightBooking.flightBookingLinks[1].baggageAddon != null) {
                sum += flightBooking.flightBookingLinks[1].baggageAddon.price;

            }

            return numeral(sum).format("0,0");
        },
        getFBooking() {
            http
                .get(
                    "http://localhost:9000/api/flight-booking/pending"
                )
                .then(res => {
                    this.flightBookings = res.data;
                    this.data = false;
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
                    this.data = false;
                })
                .catch(e => console.log(e));
        }
    },
    mounted() {
    if(this.mode !="testfail"){
    
    this.getPay();
    this.getFBooking();
    
    }
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
</style>
