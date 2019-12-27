<template>
  <div class="" id="book">
    <div class="step-style mt-12">
      <v-stepper :alt-labels="true">
        <v-stepper-header>
          <v-stepper-step :complete="pageLocation>=1" step="1">ค้นหาเที่ยวบิน</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=2" step="2">เลือกเที่ยวบิน</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=3" step="3">ข้อมูลผู้โดยสาร</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=4" step="4">ชำระเงิน</v-stepper-step>
        </v-stepper-header>
      </v-stepper>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'

export default {
  name: "bookNav",
  computed: mapState({
    pageLocation: state => state.BookFlight.pageLocation
  })
}
</script>

<style>

</style>