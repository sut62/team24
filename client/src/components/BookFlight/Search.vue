<template>
  <div >
    <h3 class="text-white ">ค้นหาเที่ยวบิน</h3>
    <div class="card shadow text-center">
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
          <li class="nav-item">
            <a class="nav-link active" href="#">ค้นหาเที่ยวบิน</a>
          </li>
        </ul>
      </div>
      <div class="card-body px-4">
        <v-form
          ref="form"
          lazy-validation
        >
          <v-row>
            <v-col>
              <v-autocomplete
                v-model="airportDepart"
                rounded
                :rules="[rules.requiredAirportDepart]"
                :items="airportName"
                item-text="text"
                item-value="value"
                outlined
                required
                label="จาก"
              ></v-autocomplete>
            </v-col>
            <v-col>
              <v-autocomplete
                v-model="airportArrive"
                rounded
                :rules="[rules.requiredAirportReturn]"
                :items="airportNameFilter"
                item-text="text"
                item-value="value"
                outlined
                required
                :disabled="airportNameFilter.length != airportName.length -1"
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
        </v-form>
      </div>
    </div>
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
    rules: {
      required: v => !!v || 'Required.',
      requiredAirportDepart: v => v.id != undefined || 'กรุณาเลือกสนามบินต้นทาง',
      requiredAirportReturn: v => v.id != undefined || 'กรุณาเลือกสนามบินปลายทาง',
      min: v => v.length >= 8 || 'Min 8 characters',
      emailMatch: () => ('The email and password you entered don\'t match'),
    },
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
      this.validate()
      if(this.isValid){
        this.getFlightDepart(this.dates[0])
        this.getFlightReturn(this.dates[1])
        this.selectDepartDate(this.dates[0])
        this.selectReturnDate(this.dates[1])
      }
    },
    validate () {
      if (this.$refs.form.validate()) {
        this.snackbar = true
      }
    },
    reset () {
      this.$refs.form.reset()
    },
    resetValidation () {
      this.$refs.form.resetValidation()
    },
  },
  computed: {
    airportNameFilter(){
      if(this.airportDepart != {}){
        return this.airportName.filter(obj => obj.value.id != this.airportDepart.id)
      }else if(this.airportArrive != {}){
        return this.airportName.filter(obj => obj.value.id != this.airportArrive.id)
      }
      return this.airportName
    },
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
      if(new Date(this.dates[0]).getDate() < new Date(moment(new Date()).format("YYYY-MM-DD")).getDate() ){
        this.dates = [moment(new Date()).format("YYYY-MM-DD")]
      }else
      if(new Date(this.dates[1]).getDate() < new Date(this.dates[0]).getDate()){
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
  h3 {
    color: white;
    text-shadow: 2px 2px 4px #000000;
  }
  .shadow{
    box-shadow: 5px 5px 15px rgb(0, 0, 0);
  }
  .date-picker{
    position: absolute;
    left: 20px;
    top: 70px;
    width: 500px;
  }
</style>