<template>
  <div>
    <h3 class="text-white">ค้นหาเที่ยวบิน</h3>
    <div class="card text-center">
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
          <li class="nav-item">
            <a class="nav-link active" href="#">เที่ยวบิน</a>
          </li>
        </ul>
      </div>
      <div class="card-body">
        <h5 class="card-title">เริ่มต้นการเดินทาง</h5>
        <v-row>
          <v-col cols="2">
            <v-autocomplete
              v-model="select"
              :items="items"
              outlined
              label="จาก"
              filled
            ></v-autocomplete>
          </v-col>
          <v-col cols="2">
            <v-autocomplete
              v-model="select"
              :items="items"
              outlined
              label="ถึง"
              filled
            ></v-autocomplete>
          </v-col>
          <v-col cols="4">
            <v-text-field @click="showDatePick =! showDatePick" outlined v-model="dateRangeText" label="เลือกวันเวลาไป-กลับ" readonly></v-text-field>
            <v-date-picker v-if="showDatePick" style="z-index:1;" class="date-picker" v-model="dates" range></v-date-picker>
          </v-col>
          <v-col cols="4">
            <div class="btn btn-danger text-white btn-lg" style="width:100%;" @click="getFlight">ค้นหา</div>
          </v-col>
        </v-row>
      </div>
    </div>
    <hr/>
    
  </div>
</template>

<script>
import {mapActions} from 'vuex'

export default {

  name: 'search',

  data: () => ({
    showDatePick: false,
    dates: ['2019-12-27', '2019-12-27'],
    select: 'Programming',
    items: [
      'กรุงเทพ',
      'ขอนแก่น',
      'ภูเก็ต',
      'เลย',
    ]
  }),
  methods:{
    ...mapActions({
      getFlightDepart: 'BookFlight/getFlightDepart',
      getFlightReturn: 'BookFlight/getFlightReturn'
    }),
    getFlight(){
      this.getFlightDepart(this.dates[0])
      this.getFlightReturn(this.dates[1])
    }
  },
  computed: {
    dateRangeText () {
      return 'Depart: ' + this.dates.join(' Return: ')
    },
  }
};
</script>

<style scoped>
  .date-picker{
    position: absolute;
    left: 20px;
    top: 70px;
    width: 500px;
  }
</style>