<template>
  <v-card
      class="mx-auto"
      style="width:100%"
    >
      <v-card-text>
        <v-row>
          <v-col cols="2">
            <div class="center" style="width:90px;position: absolute;right:1px;">
              <h3>07:11</h3>
              <div>
                1 ชม. 15 นาที
              </div>
            </div>
          </v-col>
          <v-col cols="3">
            <v-divider></v-divider>
          </v-col>
          <v-col cols="2">
            <div class="center" style="width:90px;">
              <h3>07:11</h3>
              <div>
                บินตรง
              </div>
            </div>
          </v-col>
          <v-col cols="4">
            <div class="btn btn-warning btn-block btn-lg pt-4" style="height:100%;">
              1,900 THB
            </div>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
</template>

<script>
export default {
  name: 'flightCard'
}
</script>

<style>

</style>