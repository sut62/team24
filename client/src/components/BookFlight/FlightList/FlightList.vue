<template>
  <div class="mt-12 mx-auto" id="flight-list">
    <h2 class="mb-5" >ขาไป ขอนแก่น ถึง กรุงเทพฯ - ดอนเมือง</h2>
    <FlightCard/>
    <FlightCard/>
    <FlightCard/>
    <FlightCard/>
  </div>
</template>

<script>
import FlightCard from './FlightCard'
export default {
  name: "flightList",
  components: {
    FlightCard
  }
}
</script>

<style>

</style>