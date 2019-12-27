<template>
  <v-card
      class="mx-auto mt-3"
      style="width:100%"
    >
      <v-card-text>
        <v-row>
          <v-col cols="2">
            <div class="center" style="width:90px;position: absolute;right:1px;">
              <h3>{{flight.arrive | formatTime}}</h3>
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
              <h3>{{flight.depart | formatTime}}</h3>
              <div>
                บินตรง
              </div>
            </div>
          </v-col>
          <v-col cols="4">
            <div class="btn btn-warning btn-block btn-lg pt-4" style="height:100%;">
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
    flight: {
      type: Object
    }
  },
  computed: {
    duration(){
      let start = moment(this.flight.depart)
      let end   = moment(this.flight.arrive)
      return end.to(start, true);
    }
  },
  filters: {
    formatTime: (value) => {
      return moment(String(value)).format('hh:mm')
    },
    formatDuration: (value) => {
      return moment(String(value)).format("from", "now", true)
    }
  }
}
</script>

<style>

</style>