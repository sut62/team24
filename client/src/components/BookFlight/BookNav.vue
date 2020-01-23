<template>
  <div class="" id="book">
    <div class="step-style mt-12">
      <v-stepper :alt-labels="true">
        <v-stepper-header>
          <v-stepper-step :complete="pageLocation>=1" step="1" ><span><v-icon>mdi-magnify</v-icon> ค้นหา</span></v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=2" step="2"><span><v-icon>mdi-airplane</v-icon> เที่ยวบิน</span></v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=3" step="3"><span><v-icon>mdi-account</v-icon> ผู้โดยสาร</span></v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=4" step="4"><span><v-icon>mdi-cart</v-icon>บริการเสริม</span></v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step :complete="pageLocation>=5" step="5"><span><v-icon>mdi-credit-card-outline</v-icon> ชำระเงิน</span></v-stepper-step>
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