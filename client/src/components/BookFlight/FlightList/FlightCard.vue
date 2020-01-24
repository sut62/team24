<template>
  <v-card
      class="mx-auto mt-3 shadow"
      style="width:90%"
    >
      <v-card-text>
        <v-row>
          <v-col cols="2">
            <div class="center" style="width:90px;position: absolute;right:1px;">
              <h3>{{flight.depart | formatTime}}</h3>
              <div style="color:red">
                {{getDuration }}
                <!-- 1 ชม. 15 นาที -->
              </div>
            </div>
          </v-col>
          <v-col cols="3">
            <v-row>
              <v-icon>mdi-airplane</v-icon>
              <v-divider></v-divider>
              <v-icon>mdi-map-marker</v-icon>
            </v-row>
            
          </v-col>
          <v-col cols="2">
            <div class="center" style="width:90px;">
              <h3>{{flight.arrive | formatTime}}</h3>
              <div style="color:red">
                บินตรง
              </div>
            </div>
          </v-col>
          <v-col cols="4">
            <div 
              :class="{ 'btn btn-warning btn-block myselect btn-lg pt-4':isSelected,'btn btn-secondary btn-block btn-lg pt-4':!isSelected}" 
              style="height:100%;" 
              @click="selectFlight(flight)"
            >
              {{flight.price | price}} THB <v-icon v-if="isSelected" class="mb-1">mdi-checkbox-marked-circle</v-icon>
            </div>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
</template>

<script>
import moment from 'moment'
var numeral = require("numeral");

export default {
  name: 'flightCard',
  props: {
    isSelected:{
      type: Boolean
    },
    flight: {
      type: Object
    },
    selectFlight:{
      type: Function
    }
  },
  computed: {
    getDuration(){
      let arrive = new Date(this.flight.arrive)
      let depart = new Date(this.flight.depart)
      const diffTime = Math.abs(arrive - depart);
      const diffMinute = Math.ceil(diffTime / (1000 * 60));
      const diffHour = Math.ceil(diffMinute / 60) - 1;
      const minute = diffMinute % 60;
      const hour = diffHour
      if(diffHour == 0){
        return minute + " นาที"
      }
      return hour + " ชม. " + minute + " นาที"
    },
    duration(){
      let start = moment(this.flight.depart)
      let end = moment(this.flight.arrive)
      return end.to(start, true);
    }
  },
  filters: {
    formatTime: (value) => {
      return moment(String(value)).format('HH:mm')
    },
    price(price){
      // add this on top
      // var numeral = require("numeral");
      return numeral(price).format("0,0.00");
    }
  }
}
</script>

<style scope>
  .myselect{
    border-style: solid;
    border-width: 6px;
    border-color: rgb(83, 59, 2);
    font-weight: bold;
  }
  .myselect:hover{
    border-style: solid;
    border-width: 6px;
    border-color: rgb(51, 36, 1);
    font-weight: bold;
  }
  .shadow{
    box-shadow: 10px 15px 1px rgb(0, 0, 0);
  }
</style>