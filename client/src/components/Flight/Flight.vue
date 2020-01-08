<template>
<div>
    <v-container fluid>
        <v-row>
            <v-col cols="12" sm="20" md="12">
                <v-card>
                    <v-toolbar>
                        <h1>เที่ยวบิน</h1>
                    </v-toolbar>
                    <v-dialog v-model="dialog" max-width="800px">
                        <v-card>
                            <v-card-text>
                                <v-autocomplete label="เครื่องบิน" v-model="flights.airplaneId" :items="airplanes" item-text="name" item-value="id"></v-autocomplete>
                                <v-row>
                                    <v-col cols="12" sm="6" md="6">
                                        <v-autocomplete label="เมืองต้นทาง" v-model="flights.departAirportId" :items="airportName" item-text="text" item-value="value.id"></v-autocomplete>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="6">
                                        <v-autocomplete label="เมืองปลายทาง" v-model="flights.arriveAirportId" :items="airportName" item-text="text" item-value="value.id"></v-autocomplete>
                                    </v-col>
                                </v-row>

                                <v-text-field label="ราคา(บาท)" v-model="flights.price"></v-text-field>

                                <v-row>
                                    <v-col cols="12" lg="6">
                                        <v-menu ref="menu1" v-model="menu1" :close-on-content-click="false" transition="scale-transition" offset-y max-width="290px" min-width="290px">
                                            <template v-slot:activator="{ on }">
                                                <v-text-field v-model="flights.departDate" label="Depart Date" hint="YYYY/MM/DD format" persistent-hint prepend-icon="mdi-calendar-clock" v-on="on"></v-text-field>
                                            </template>
                                            <v-date-picker v-model="flights.departDate" no-title @input="menu1 = false"></v-date-picker>
                                        </v-menu>
                                        <p>Date in ISO format: <strong>{{ flights.departDate }}</strong></p>
                                    </v-col>

                                    <v-col cols="12" lg="6">
                                        <v-menu ref="menu2" v-model="menu2" :close-on-content-click="false" transition="scale-transition" offset-y max-width="290px" min-width="290px">
                                            <template v-slot:activator="{ on }">
                                                <v-text-field v-model="flights.arriveDate" label="Arrive Date" hint="YYYY/MM/DD format" persistent-hint prepend-icon="mdi-calendar-clock" v-on="on"></v-text-field>
                                            </template>
                                            <v-date-picker v-model="flights.arriveDate" no-title @input="menu2 = false"></v-date-picker>
                                        </v-menu>
                                        <p>Date in ISO format: <strong>{{ flights.arriveDate }}</strong></p>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="12" sm="6">
                                        <v-menu ref="menu3" v-model="menu3" :close-on-content-click="false" :nudge-right="40" :return-value.sync="flights.departTime" transition="scale-transition" offset-y max-width="290px" min-width="290px">
                                            <template v-slot:activator="{ on }">
                                                <v-text-field v-model="flights.departTime" label="Depart Time" prepend-icon="mdi-timer" readonly v-on="on"></v-text-field>
                                            </template>
                                            <v-time-picker format="24hr" v-if="menu3" v-model="flights.departTime" @click:minute="$refs.menu3.save(flights.departTime)"></v-time-picker>
                                        </v-menu>
                                    </v-col>
                                    <v-col cols="12" sm="6">
                                        <v-menu ref="menu4" v-model="menu4" :close-on-content-click="false" :nudge-right="40" :return-value.sync="flights.arriveTime" transition="scale-transition" offset-y max-width="290px" min-width="290px">
                                            <template v-slot:activator="{ on }">
                                                <v-text-field v-model="flights.arriveTime" label="Depart Time" prepend-icon="mdi-timer" readonly v-on="on"></v-text-field>
                                            </template>
                                            <v-time-picker format="24hr" v-if="menu4" v-model="flights.arriveTime" @click:minute="$refs.menu4.save(flights.arriveTime)"></v-time-picker>
                                        </v-menu>
                                    </v-col>
                                </v-row>
                                <small class="grey--text">* This doesn't actually save.</small>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn text color="primary" @click="dialog = false">ปิด</v-btn>
                                <v-btn text color="primary" @click="saveFlight">ยืนยันการเพิ่มเที่ยวบิน</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>

                    <v-card-text style="height: 100%; width: 100%" class="background">
                            <div v-for="(flights, index) in flight" :key="index">
                                <v-card>
                                    <v-list-item>
                                    <v-list-item-content>
                                        <v-row class="font-weight-medium">
                                            <v-list-item>
                                                <v-row class="ml-6 mt -3">
                                                    <v-list-item-title class="headline mb-3">{{flights.airplane.name}}</v-list-item-title>
                                                    <v-list-item-title class="headline mb-0">{{flights.price}} บาท</v-list-item-title>
                                                </v-row>
                                                <v-icon large color="orange darken-2">mdi-airplane-takeoff</v-icon>
                                                <v-row class="ml-6 mt-.5">
                                                    <v-list-item-content>
                                                        <v-list-item-title class="font-weight-medium">{{flights.depart | moment("DD-MM-YYYY | HH:mm")}}</v-list-item-title>
                                                        <v-list-item-title class="font-weight-medium">[{{flights.flightAirports[0].airport.name}}] {{flights.flightAirports[0].airport.city.name}}</v-list-item-title>
                                                    </v-list-item-content>
                                                </v-row>
                                                <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                                                <v-row class="ml-6 mt-.5">
                                                    <v-list-item-content>
                                                        <v-list-item-title class="font-weight-medium">{{flights.arrive | moment("DD-MM-YYYY | HH:mm")}}</v-list-item-title>
                                                        <v-list-item-title class="font-weight-medium">[{{flights.flightAirports[1].airport.name}}] {{flights.flightAirports[1].airport.city.name}}</v-list-item-title>
                                                    </v-list-item-content>
                                                </v-row>
                                            </v-list-item>
                                        </v-row>
                                        <hr />
                                    </v-list-item-content>
                                </v-list-item>
                                </v-card>
                            </div>
                                
                    </v-card-text>

                    <v-card-text style="height: 100px; position: relative">
                        <v-btn absolute dark fab top right color="pink" @click="dialog = !dialog">
                            <v-icon>mdi-plus</v-icon>
                        </v-btn>
                    </v-card-text>

                </v-card>

            </v-col>
        </v-row>
    </v-container>
</div>
</template>

<script>
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
    name: "flight",
    data: () => ({
        airportName: [],
        dialog: false,
        menu1: false,
        menu2: false,
        menu3: false,
        menu4: false,
        flight: [],
        airplanes: [],
        airports: [],
        flights: {
            airplaneId: "",
            departAirportId: "",
            arriveAirportId: "",
            price: "",
            departDate: "",
            departTime: "",
            arriveDate: "",
            arriveTime: ""
        }
    }),
    watch: {
        flights() {
            console.log("flighs")
            console.log(this.flights)
        }
    },
    methods: {
        getFlight() {
            http
                .get('http://localhost:9000/api/flight')
                .then(response => {
                    this.flight = response.data
                })
                .catch(e => {
                    console.log(e)
                })
        },
        getAirport() {
            http
                .get("http://localhost:9000/api/airport")
                .then(response => {
                    this.airports = response.data;
                    console.log(this.airports)
                    response.data.map(airport => {
                        this.airportName.push({
                            text: airport.city.name + " [" + airport.name + "]",
                            value: airport
                        })
                    })
                })
                .catch(e => {
                    console.log(e);
                })
        },
        getAirplane() {
            http
                .get("http://localhost:9000/api/airplane")
                .then(response => {
                    this.airplanes = response.data;
                    console.log(this.airplanes);
                })
                .catch(e => {
                    console.log(e);
                })
        },
        saveFlight() {
            http({
                    method: 'post',
                    url: "http://localhost:9000/api/flight/create",
                    data: {
                        "price": this.flights.price,
                        "departDate": this.flights.departDate + " " + this.flights.departTime,
                        "arriveDate": this.flights.arriveDate + " " + this.flights.arriveTime,
                        "airplaneId": this.flights.airplaneId,
                        "departAirportId": this.flights.departAirportId,
                        "arriveAirportId": this.flights.arriveAirportId
                    }
                })
                .then(response => {
                    alert("บันทึกสำเร็จ", response);
                    console.log(response.data)
                    window.location.reload()
                })
                .catch(e => {
                    console.log(e);
                    console.log({
                        "price": this.flights.price,
                        "departDate": this.flights.departDate + " " + this.flights.departTime,
                        "arriveDate": this.flights.arriveDate + " " + this.flights.arriveTime,
                        "airplaneId": this.flights.airplaneId,
                        "departAirportId": this.flights.departAirportId,
                        "arriveAirportId": this.flights.arriveAirportId
                    })
                });
        },
    },
    mounted() {
        this.getFlight();
        this.getAirport();
        this.getAirplane();
        // this.saveFlight();
    }
}
</script>

<style scoped>
.background {
    background: url("https://www.bloggang.com/data/or-oa/picture/1220243567.jpg") no-repeat;
    -webkit-background-size: cover;
    background-size: cover;
    width: 100vw;
    height: 100vh;

}
</style>
