<template>
  <v-card
      class="mx-auto mt-3"
      style="width:100%"
    >
      <v-card-text>
        <v-row>
          <v-col cols="2">
            <div class="center" style="width:90px;position: absolute;right:1px;">
              <h3>{{flight.depart | formatTime}}</h3>
              <div>
                {{duration}}
                <!-- 1 ชม. 15 นาที -->
              </div>
            </div>
          </v-col>
          <v-col cols="3">
            <v-divider></v-divider>
          </v-col>
          <v-col cols="2">
            <div class="center" style="width:90px;">
              <h3>{{flight.arrive | formatTime}}</h3>
              <div>
                บินตรง
              </div>
            </div>
          </v-col>
          <v-col cols="4">
            <div 
              :class="{ 'btn btn-danger btn-block btn-lg pt-4':isSelected,'btn btn-warning btn-block btn-lg pt-4':!isSelected}" 
              style="height:100%;" 
              @click="selectFlight(flight)"
            >
              {{flight.price}} THB
            </div>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
</template>

<script>
import moment from 'moment'

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
    duration(){
      let start = moment(this.flight.depart)
      let end = moment(this.flight.arrive)
      return end.to(start, true);
    }
  },
  filters: {
    formatTime: (value) => {
      return moment(String(value)).format('hh:mm')
    }
  }
}
</script>

<style>

</style>