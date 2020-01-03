<template>
  <div>
    <h3 class="text-white">ค้นหาเที่ยวบิน</h3>
    <div class="card text-center">
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
          <li class="nav-item">
            <a class="nav-link active" href="#">ค้นหาเที่ยวบิน</a>
          </li>
        </ul>
      </div>
      <div class="card-body px-4">
        <v-row>
          <v-col>
            <v-autocomplete
              v-model="airportDepart"
              rounded
              :items="airportName"
              item-text="text"
              item-value="value"
              outlined
              label="จาก"
              
            ></v-autocomplete>
          </v-col>
          <v-col>
            <v-autocomplete
              v-model="airportArrive"
              rounded
              :items="airportName"
              item-text="text"
              item-value="value"
              outlined
              label="ถึง"
            ></v-autocomplete>
          </v-col>
          <v-col >
            <v-menu
            :close-on-content-click="false"
            transition="scale-transition"
            offset-y
            min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field rounded v-on="on" outlined v-model="dateRangeText" label="เลือกวันเวลาไป-กลับ" readonly></v-text-field>
              </template>
              <v-date-picker v-model="dates" range no-title scrollable>
              </v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="2">
              <div :class="{'btn btn-danger text-white btn-lg':isValid,'btn btn-secondary text-white btn-lg':!isValid}" style="width:100%;" @click="getFlight">ค้นหา</div>
          </v-col>
        </v-row>
      </div>
    </div>
    <hr/>
    
  </div>
</template>

<script>
import {mapActions,mapState,mapMutations} from 'vuex'
import moment from 'moment'

export default {

  name: 'search',

  data: () => ({
    showDatePick: false,
    dates: ['2020-01-03', '2020-01-03'],
    airportName: [],
    airportDepart: {},
    airportArrive: {},
  }),
  methods:{
    ...mapActions({
      getFlightDepart: 'BookFlight/getFlightDepart',
      getFlightReturn: 'BookFlight/getFlightReturn',
      getAirport: 'BookFlight/getAirport',
    }),
    ...mapMutations({
      'selectDepartDate':'BookFlight/selectDepartDate',
      'selectReturnDate':'BookFlight/selectReturnDate',
      'selectDepartAirport': 'BookFlight/selectDepartAirport',
      'selectArriveAirport': 'BookFlight/selectArriveAirport'
    }),
    getFlight(){
      if(this.isValid){
        this.getFlightDepart(this.dates[0])
        this.getFlightReturn(this.dates[1])
        this.selectDepartDate(this.dates[0])
        this.selectReturnDate(this.dates[1])
      }
    },
  },
  computed: {
    ...mapState({
      'airport': state => state.BookFlight.result.airport,
    }),
    dateRangeText () {
      return 'ไป: ' + this.dates.join(' กลับ: ')
    },
    isValid(){
      return this.airportDepart.name != undefined && this.airportArrive.name != undefined && this.dates != [] && new Date(this.dates[1]).getTime() >= new Date(this.dates[0]).getTime()
    }
  },
  watch:{
    dates(){
      if(new Date(this.dates[1]).getTime() < new Date(this.dates[0]).getTime()){
        this.dates = [this.dates[1]]
      }
    },
    airportDepart(){
      this.selectDepartAirport(this.airportDepart)
    },
    airportArrive(){
      this.selectArriveAirport(this.airportArrive)
    }
  },
  created(){
    this.getAirport().then((result)=>{
      // console.log(result)
      result.map(airport => {
        this.airportName.push({
          text: airport.city.name + " [" + airport.name + "]" ,
          value: airport
        })
      })
    });
    let currentDate = new Date()
    let nextDate = new Date()
    nextDate.setDate(nextDate.getDate() + 1)
    let currentDateString = moment(currentDate).format("YYYY-MM-DD").toString()
    let nextDateString = moment(nextDate).format("YYYY-MM-DD").toString()
    this.dates = [currentDateString,nextDateString]
  }
};
</script>

<style scoped>
  .mycard{
    border-style: solid;
    border-color: rgb(240, 240, 240);
    border-width: 3px;
    padding:20px;
    border-radius: 20px;
  }
  .date-picker{
    position: absolute;
    left: 20px;
    top: 70px;
    width: 500px;
  }
</style>