<template>
<div>
    <Alert :open="success" topic="แจ้งเตือน" desc="บันทึกสำเร็จ" :callback="()=>this.success = false" />
    <Alert :open="fall" topic="แจ้งเตือน" desc="บันทึกไม่สำเร็จ" :callback="()=>this.fall = false" />
    <Alert :open="deleteSuccess" topic="แจ้งเตือน" desc="ลบ Flight สำเร็จ" :callback="()=>this.deleteSuccess = false" />
    <Alert :open="deleteFail" topic="แจ้งเตือน" desc="ลบ Flight ไม่สำเร็จ" :callback="()=>this.deleteFail = false" />
    <v-container fluid>
        <v-row>
            <v-col cols="12" sm="20" md="10">
                <v-card raised>
                    <v-toolbar height="100px">
                        <h1>เที่ยวบิน</h1>
                        <v-btn id="add_fligh_btn" class="mx-12" absolute small dark fab right color="primary" @click="dialog = !dialog">
                            <v-icon>mdi-plus</v-icon>
                        </v-btn>
                        <v-btn id="delete_fligh_btn" class="mx-0" absolute small dark fab right color="pink" @click="dialog1 = !dialog1">
                            <v-icon>mdi-minus</v-icon>
                        </v-btn>
                    </v-toolbar>
                    <v-dialog v-model="dialog" max-width="800px">
                        <v-card>
                            <v-card-text>
                                <v-autocomplete id="AirplaneCombobox" label="เครื่องบิน" :rules="[rules.requiredAirplane]" required v-model="flights.airplaneId" :items="airplanes" item-text="name" item-value="id"></v-autocomplete>
                                <v-row>
                                    <v-col cols="12" sm="6" md="6">
                                        <v-autocomplete id="departAirportCombobox" label="เมืองต้นทาง" :rules="[rules.requiredAirportDepart]" required v-model="flights.departAirportId" key="value.id" :items="airportName" item-text="text" item-value="value.id"></v-autocomplete>
                                    </v-col>
                                    <v-col cols="12" sm="6" md="6">
                                        <v-autocomplete id="arriveAirportCombobox" label="เมืองปลายทาง" :rules="[rules.requiredAirportArrive]" required v-model="flights.arriveAirportId" :items="airportName" item-text="text" item-value="value.id"></v-autocomplete>
                                    </v-col>
                                </v-row>

                                <v-text-field id="PriceInput" label="ราคา(บาท)" :rules="[rules.requiredPrice]" required v-model="flights.price"></v-text-field>

                                <v-row>
                                    <v-col cols="12" lg="6">
                                        <v-flex>
                                            <v-datetime-picker label="Depart Date" v-model="flights.departDate" :text-field-props="textFieldProps" :date-picker-props="dateProps" :time-picker-props="timeProps"></v-datetime-picker>
                                        </v-flex>
                                        <p>Datetime value: <span>{{flights.departDate | moment("YYYY-MM-DD HH:mm")}}</span></p>
                                    </v-col>

                                    <v-col cols="12" lg="6">
                                        <v-flex>
                                            <v-datetime-picker label="Arrive Date" v-model="flights.arriveDate" :text-field-props="textFieldProps" :date-picker-props="dateProps" :time-picker-props="timeProps"></v-datetime-picker>
                                        </v-flex>
                                        <p>Datetime value: <span>{{flights.arriveDate | moment("YYYY-MM-DD HH:mm")}}</span></p>
                                    </v-col>
                                </v-row>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn text color="primary" @click="dialog = false">ปิด</v-btn>
                                <v-btn text color="primary" @click="saveFlight">ยืนยันการเพิ่มเที่ยวบิน</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>

                    <v-dialog v-model="dialog1" max-width="800px">
                        <v-card>
                            <v-card-text>
                                <v-autocomplete id="FlightCombobox" label="เลือก Flight ที่ต้องการลบ" v-model="flights.flightId" :rules="[rules.requiredDeleteFlight]" required :items="flight" item-text="id" item-value="value.id"></v-autocomplete>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn text color="primary" @click="dialog1 = false">ปิด</v-btn>
                                <v-btn text color="primary" @click="deleteFlight(flights.flightId)">ยืนยันการลดเที่ยวบิน</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>

                    <v-card-text style="height: 100%; width: 100%" class="background">
                        <div v-for="(flight1, index) in flight" :key="index">
                            <v-card tile>
                                <div class="p-2 mb-2">
                                    <v-list-item>
                                        <v-list-item-content>
                                            <v-row class="font-weight-medium">
                                                <v-list-item>
                                                    <v-row class="ml-6 mt -3">
                                                        <v-list-item-title class="headline mb-2">
                                                            <font color="red">Flight ID: {{flight1.id}}</font>
                                                        </v-list-item-title>
                                                        <v-list-item-title class="headline mb-2"><b>เครื่องบิน:</b> {{flight1.airplane.name}}</v-list-item-title>
                                                        <v-list-item-title class="headline mb-0">
                                                            <v-icon color="green darken-2">mdi-currency-usd </v-icon>ราคา {{flight1.price}} บาท
                                                        </v-list-item-title>
                                                    </v-row>
                                                    <v-icon large color="orange darken-2">mdi-airplane-takeoff</v-icon>
                                                    <v-row class="ml-6 mt-.5">
                                                        <v-list-item-content>
                                                            <v-list-item-title class="font-weight-small">YYYY-MM-DD | HH:mm</v-list-item-title>
                                                            <v-list-item-title class="font-weight-medium">{{flight1.depart | moment("YYYY-MM-DD | HH:mm")}}</v-list-item-title>
                                                            <v-list-item-title class="font-weight-medium">[{{flight1.flightAirports[0].airport.name}}] {{flight1.flightAirports[0].airport.city.name}}</v-list-item-title>
                                                        </v-list-item-content>
                                                    </v-row>
                                                    <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                                                    <v-row class="ml-6 mt-.5">
                                                        <v-list-item-content>
                                                            <v-list-item-title class="font-weight-small">YYYY-MM-DD | HH:mm</v-list-item-title>
                                                            <v-list-item-title class="font-weight-medium">{{flight1.arrive | moment("YYYY-MM-DD | HH:mm")}}</v-list-item-title>
                                                            <v-list-item-title class="font-weight-medium">[{{flight1.flightAirports[1].airport.name}}] {{flight1.flightAirports[1].airport.city.name}}</v-list-item-title>
                                                        </v-list-item-content>
                                                    </v-row>
                                                </v-list-item>
                                            </v-row>
                                        </v-list-item-content>
                                    </v-list-item>
                                </div>
                            </v-card>
                        </div>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</div>
</template>

<script>
import axios from "axios";
import Alert from '../Alert';
import moment from 'moment';

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
        rules: {
            required: v => !!v || 'Required.',
            requiredAirplane: v => !!v || 'กรุณาเลือกเครื่องบิน',
            requiredAirportDepart: v => !!v || 'กรุณาเลือกเมืองต้นทาง',
            requiredAirportArrive: v => !!v || 'กรุณาเลือกเมืองปลายทาง',
            requiredPrice: v => !!v || 'กรุณากรอกราคา',
            requiredDeleteFlight: v => !!v || 'กรุณาเลือก FLIGHT_ID',
        },
        textFieldProps: {
            appendIcon: 'mdi-calendar'
        },
        dateProps: {
            min: ""
        },
        timeProps: {
            format: "24hr",
            scrollable: true,
            min: ""
        },
        date: "",
        minute: "",
        airportName: [],
        dialog: false,
        dialog1: false,
        menu1: false,
        menu2: false,
        menu3: false,
        menu4: false,
        success: false,
        deleteSuccess: false,
        deleteFail: false,
        fall: false,
        flight: [],
        airplanes: [],
        airports: [],
        flights: {
            flightId: "",
            airplaneId: "",
            departAirportId: "",
            arriveAirportId: "",
            price: "",
            departDate: "",
            arriveDate: "",
        }
    }),
    components: {
        Alert
    },
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
        deleteFlight(id) {
            http
                .delete("http://localhost:9000/api/flight/id/" + id).then(res => {
                    console.log(res.data);
                    this.getFlight();
                    this.deleteSuccess = true;
                })
                .catch(e => {
                    console.log(e);
                    this.deleteFail = true;
                })
        },
        saveFlight() {
            http({
                    method: 'post',
                    url: "http://localhost:9000/api/flight/create",
                    data: {
                        "price": this.flights.price,
                        "departDate": moment(this.flights.departDate).format("YYYY-MM-DD HH:mm").toString(),
                        "arriveDate": moment(this.flights.departDate).format("YYYY-MM-DD HH:mm").toString(),
                        "airplaneId": this.flights.airplaneId,
                        "departAirportId": this.flights.departAirportId,
                        "arriveAirportId": this.flights.arriveAirportId
                    }
                })
                .then(response => {
                    // alert("บันทึกสำเร็จ", response);
                    this.getFlight()
                    this.success = true
                    console.log(response.data)
                    // window.location.reload()
                })
                .catch(e => {
                    this.fall = true;
                    console.log(e);
                    console.log({
                        "price": this.flights.price,
                        "departDate": moment(this.flights.departDate).format("YYYY-MM-DD HH:mm").toString(),
                        "arriveDate": moment(this.flights.departDate).format("YYYY-MM-DD HH:mm").toString(),
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
    },
    created() {
        let departDate = new Date()
        let arriveDate = new Date()
        arriveDate.setDate(arriveDate.getDate() + 1)
        let departDateString = moment(departDate).format("YYYY-MM-DD HH:mm").toString()
        let arriveDateString = moment(arriveDate).format("YYYY-MM-DD HH:mm").toString()
        this.flights.departDate = departDateString;
        this.flights.arriveDate = arriveDateString;

        let date = new Date()
        date.setDate(date.getDate())
        let dateString = moment(date).format("YYYY-MM-DD").toString()
        this.dateProps.min = dateString;

        // let minute = new Date()
        // minute.setDate(minute.getDate())
        // let minuteString = moment(minute).format("HH:mm").toString()
        // this.timeProps.min = minuteString;
    },

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
