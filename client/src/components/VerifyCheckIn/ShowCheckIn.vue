<template>
  <div>
    <v-card class="my-card container " v-for="check in checkIn" :key="check.id" :value="check.id">
    <v-row>
      <v-navigation-drawer absolute permanent left>
        <template v-slot:prepend>
          <v-list-item two-line>
            <v-list-item-avatar>
               <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title >{{check.flightBooking.user.firstName}}</v-list-item-title>
              <v-list-item-subtitle>สภานะเช็คอิน : {{check.checkInStatus.name}}</v-list-item-subtitle>
               <v-list-item-subtitle>เช็คอินผ่านทาง : {{check.checkInType.name}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
        <v-divider></v-divider>
        <v-list dense>
          <v-list-item >
            <v-list-item-icon>
              <v-icon>mdi-barcode</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>ฺBoardingPass: {{check.boardingPass}}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item >
            <v-list-item-icon>
              <v-icon>mdi-at</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title color="blue darken-2">{{check.email}}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
          <v-list-item >
            <v-list-item-icon>
              <v-icon color="deep-orange darken-2">mdi-phone</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{check.flightBooking.user.phone}}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
       </v-row>
       <div class="text-card">
       <v-col><div><v-icon color="teal darken-2">mdi-calendar-range </v-icon>วันที่ทำการเช็คอิน : {{check.date| moment("DD MMM YYYY เวลา: HH:mm:ss")}} </div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> สนามบินต้นทาง: {{check.flightBooking.flightBookingLinks[0].flight.flightAirports[0].airport.name}}<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องออกเวลา : {{check.flightBooking.flightBookingLinks[0].flight.depart | moment("HH:mm:ss")}} </div></v-col>
       <v-col><div><v-icon color="green darken-2">mdi-airplane-landing</v-icon> สนามบินปลายทาง: {{check.flightBooking.flightBookingLinks[0].flight.flightAirports[1].airport.name}}<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องถึงเวลา : {{check.flightBooking.flightBookingLinks[0].flight.arrive | moment("HH:mm:ss")}}</div></v-col>
       </div>
    </v-card>
  </div>
</template>

<script>
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
  name: "showCheckIn",
  data: () => ({
    checkIn:[],
    payment:[],
  }),
  components: {},

  methods: {
    getCheckIn() {
      axiosInstance
        .get("/checkin")
        .then(response => {
          this.checkIn = response.data;
          console.log(response.data);
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },
  },
  mounted() {
      this.getCheckIn();
    },
};
</script>

<style scoped>
.my-card {
  width: 95%;
  height: 250px;
  text-align: center;
  border: 10px;
  margin-bottom: 10px;
}
.center {
  position: absolute;
  left: 0;
  top: 50%;
  width: 100%;
  text-align: center;
  font-size: 18px;
}
.text-card{
    text-align:left;
    margin-left: 40%;
    margin-top: 2%;
    margin-bottom: 2%;

}
</style>