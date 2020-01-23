<template>
  <!-- Menu card -->
  <div class="card">
    <div class="card-header">
      <v-icon>mdi-account</v-icon> ผู้โดยสาร 1 ท่าน
    </div>
    <div class="card-body">
      <!-- card seeletec menu -->
      <div class="d-flex flex-wrap">
        <div class="btn card my-img m-1">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class=""/>
          <div class="text-secondary">0 กก.</div>
          <div>ไม่มีสัมภาระเช็คอิน</div>
        </div>
        <div class="btn card my-img m-1">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class=""/>
          <div class="text-secondary">15 กก.</div>
          <div>700.00 THB</div>
        </div>
        <div class="btn card my-img m-1">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class=""/>
          <div class="text-secondary">15 กก.</div>
          <div>700.00 THB</div>
        </div>
        <div class="btn card my-img m-1">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class=""/>
          <div class="text-secondary">15 กก.</div>
          <div>700.00 THB</div>
        </div>
        <div class="btn card my-img m-1">
          <img src="../../../assets/BookFlight-AddsOn/baggage_icon.png" class=""/>
          <div class="text-secondary">15 กก.</div>
          <div>700.00 THB</div>
        </div>
      </div>
      <hr>
      <div class="text-right text-success">เลือกสัมภาระของเที่ยวบินขา{{menu==1?'ไป':'กลับ'}}</div>
    </div>
  </div>
</template>

<script>
export default {
  props:{
    menu:{
      type: Number
    },
  }
}
</script>

<style scoped>
  .my-img {
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
    border-radius: 10px;
  }
  .my-img:hover {
    border-color: green;
    border-style: solid;
    padding: 10px;
    margin-left: 5px;
  }
</style>