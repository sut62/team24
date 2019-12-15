<template>
  <div class="" id="book">
    <div class="step-style mt-12">
      <v-stepper :alt-labels="true">
        <v-stepper-header value="1">
          <v-stepper-step complete step="1">ค้นหาเที่ยวบิน</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step step="2">เลือกเที่ยวบิน</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step step="3">บริการเสริมพิเศษ</v-stepper-step>
          <v-divider></v-divider>
          <v-stepper-step step="4">ชำระเงิน</v-stepper-step>
        </v-stepper-header>
      </v-stepper>
    </div>
  </div>
</template>

<script>
export default {
  name: "bookNav"
}
</script>

<style>

</style>