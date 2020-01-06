<template>
<div>
    <v-container fluid>
        <v-row>
            <v-col cols="12" sm="20" md="10">
                <v-card>
                    <v-toolbar>
                        <v-app-bar-nav-icon></v-app-bar-nav-icon>
                    </v-toolbar>

                    <v-card-text style="height: 100%;" class="grey lighten-5">
                        <v-list-item>
                            <v-list-item-content>
                                <v-list-item-title class="headline mb-4"><b>เที่ยวบิน</b></v-list-item-title>
                                <v-row class="font-weight-medium">
                                    <v-list-item>
                                        <v-row class="ml-6 mt -3">
                                            <v-list-item-content>
                                                <v-list-item-title class="headline mb-3"><b>Boeing 777</b></v-list-item-title>
                                            </v-list-item-content>
                                        </v-row>
                                        <v-icon large color="orange darken-2">mdi-airplane-takeoff</v-icon>
                                        <v-row class="ml-6 mt-.5">
                                            <v-list-item-content>
                                                <v-list-item-title class="font-weight-medium">18/12/62 | 07.10</v-list-item-title>
                                                <v-list-item-title class="font-weight-medium">กรุงเทพมหานคร</v-list-item-title>
                                            </v-list-item-content>
                                        </v-row>
                                        <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                                        <v-row class="ml-6 mt-.5">
                                            <v-list-item-content>
                                                <v-list-item-title class="font-weight-medium">18/12/62 | 07.15</v-list-item-title>
                                                <v-list-item-title class="font-weight-medium">ขอนแก่น</v-list-item-title>
                                            </v-list-item-content>
                                        </v-row>
                                    </v-list-item>
                                </v-row>
                                <hr />
                                <v-row class="font-weight-medium">
                                    <v-list-item>
                                        <v-row class="ml-6 mt -3">
                                            <v-list-item-content>
                                                <v-list-item-title class="headline mb-3"><b>Boeing 777</b></v-list-item-title>
                                            </v-list-item-content>
                                        </v-row>
                                        <v-icon large color="orange darken-2">mdi-airplane-takeoff</v-icon>
                                        <v-row class="ml-6 mt-.5">
                                            <v-list-item-content>
                                                <v-list-item-title class="font-weight-medium">18/12/62 | 07.10</v-list-item-title>
                                                <v-list-item-title class="font-weight-medium">กรุงเทพมหานคร</v-list-item-title>
                                            </v-list-item-content>
                                        </v-row>
                                        <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                                        <v-row class="ml-6 mt-.5">
                                            <v-list-item-content>
                                                <v-list-item-title class="font-weight-medium">18/12/62 | 07.15</v-list-item-title>
                                                <v-list-item-title class="font-weight-medium">ขอนแก่น</v-list-item-title>
                                            </v-list-item-content>
                                        </v-row>
                                    </v-list-item>
                                </v-row>
                                <hr />
                                <v-row class="font-weight-medium">
                                    <v-list-item>

                                    </v-list-item>
                                </v-row>
                            </v-list-item-content>
                        </v-list-item>
                    </v-card-text>
                    <v-card-text style="height: 100px; position: relative">

                        <v-btn absolute dark fab top right color="pink" @click="dialog = !dialog">
                            <v-icon>mdi-plus</v-icon>
                        </v-btn>
                    </v-card-text>

                </v-card>
                <v-dialog v-model="dialog" max-width="800px">
                    <v-card>
                        <v-card-text>
                            <v-autocomplete label="เครื่องบิน" v-model="flights.airplaneId" :items="airplanes" item-text="name" item-value="id"></v-autocomplete>
                            <v-row>
                                <v-col cols="12" sm="6" md="6">
                                    <v-autocomplete label="เมืองต้นทาง" v-model="flights.departAirportId" :items="airportName" item-text="text" item-value="value.id"></v-autocomplete>
                                </v-col>
                                <v-col cols="12" sm="6" md="6">
                                    <v-autocomplete label="เมืองปลายทาง" v-model="flights.arriveAirportId" :items="airportName" item-text="text" item-value="id"></v-autocomplete>
                                </v-col>
                            </v-row>

                            <v-text-field label="ราคา(บาท)" v-model="flights.price"></v-text-field>

                            <v-row>
                                <v-col cols="12" lg="6">
                                    <v-menu ref="menu1" v-model="menu1" :close-on-content-click="false" transition="scale-transition" offset-y full-width max-width="290px" min-width="290px">
                                        <template v-slot:activator="{ on }">
                                            <v-text-field v-model="flights.departDate" label="Date" hint="YYYY/MM/DD format" persistent-hint prepend-icon="mdi-calendar-clock" @blur="date = parseDate(dateFormatted)" v-on="on"></v-text-field>
                                        </template>
                                        <v-date-picker v-model="flights.departDate" no-title @input="menu1 = false"></v-date-picker>
                                    </v-menu>
                                    <p>Date in ISO format: <strong>{{ flights.departDate }}</strong></p>
                                </v-col>

                                <v-col cols="12" lg="6">
                                    <v-menu ref="menu2" v-model="menu2" :close-on-content-click="false" transition="scale-transition" offset-y full-width max-width="290px" min-width="290px">
                                        <template v-slot:activator="{ on }">
                                            <v-text-field v-model="flights.arriveDate" label="Date" hint="YYYY/MM/DD format" persistent-hint prepend-icon="mdi-calendar-clock" @blur="date = parseDate(dateFormatted)" v-on="on"></v-text-field>
                                        </template>
                                        <v-date-picker v-model="flights.arriveDate" no-title @input="menu2 = false"></v-date-picker>
                                    </v-menu>
                                    <p>Date in ISO format: <strong>{{ flights.arriveDate }}</strong></p>
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
        menu1: false,
        menu2: false,
        flight: [],
        airplanes: [],
        airports: [],
        flights: {
            airplaneId: "",
            departAirportId: "",
            arriveAirportId: "",
            price: "",
            departDate: "",
            arriveDate: ""
        }
    }),
    watch: {
        flights() {
            console.log("flighs")
            console.log(this.flights)
        }
    },
    methods: {
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
                        "departDate": this.flights.departDate,
                        "arriveDate": this.flights.arriveDate,
                        "airplaneId": this.flights.airplanes,
                        "departAirportId": this.flights.airports,
                        "arriveAirportId": this.flights.airports
                    }
                })
                .then(response => {
                    alert("บันทึกสำเร็จ", response);
                    console.log(response.data)
                    window.location.reload()
                })
                .catch(e => {
                    alert("เกิดข้อผิดพลาด" + e)
                    console.log(e);
                });
        },
    },
    mounted() {
            this.getAirport();
            this.getAirplane();
            // this.saveFlight();
        }
}

    
</script>

<style scoped>

</style>
