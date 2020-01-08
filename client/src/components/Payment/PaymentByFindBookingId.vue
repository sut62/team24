<template>
  <div>
      <div v-for="(flightBooking,index) in flightBookings" :key="index">
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
                    <p>{{flightBooking.flightBookingLinks[0].flight.depart | moment("DD MMM YYYY")}}</p>
                    <p>
                    {{flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.city.name}}
                    <v-icon color="error">mdi-arrow-right</v-icon>
                    {{flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.city.name}}
                    </p>
                    <p>
                    {{flightBooking.flightBookingLinks[0].flight.depart | moment("HH:mm")}} - {{flightBooking.flightBookingLinks[0].flight.arrive | moment("HH:mm")}}
                    | {{getDuration(flightBooking.flightBookingLinks[0].flight.arrive,flightBooking.flightBookingLinks[0].flight.depart)}}
                    </p>
                </v-col>
                <v-col>
                    <v-row>
                    <i class="airasia-icon icon-return-flight"></i>
                    <v-icon slot="icon" size="36" class="flip">mdi-airplane-takeoff</v-icon>
                    <p class="pl-3 pt-3" style="color:grey">Return Date</p>
                    </v-row>
                    <p>{{flightBooking.flightBookingLinks[1].flight.depart | moment("DD MMM YYYY")}}</p>
                    <p>
                    {{flightBooking.flightBookingLinks[1].flight.flightAirports[0].airport.city.name}}
                    <v-icon color="error">mdi-arrow-right</v-icon>
                    {{flightBooking.flightBookingLinks[1].flight.flightAirports[1].airport.city.name}}
                    </p>
                    <p>
                    {{flightBooking.flightBookingLinks[1].flight.depart | moment("HH:mm")}} - {{flightBooking.flightBookingLinks[1].flight.arrive | moment("HH:mm")}}
                    | {{getDuration(flightBooking.flightBookingLinks[1].flight.arrive,flightBooking.flightBookingLinks[1].flight.depart)}}
                    </p>
                </v-col>
                </v-row>
            </v-card>
            </div>
      </div>
  </div>
</template>

<script>
import axios from "axios";

let http = axios.create({
  baseURL: "http://localhost:9000/api",
  timeout: 120000,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-type": "application/json"
  }
});
export default {
    data:()=>({
        flightBookings:[]
    }),
    create(){
        http
        .get("/flight-booking/user")
        .then(res =>{
            console.log(res.data)
            this.flightBookings = res.data
        })
        .catch(e=>{
            console.log(e)
        })
    }
}
</script>

<style>

</style>