<template>
<div>
  <div class="card p-4">
        <v-row>
            <v-icon class="ml-2" slot="icon">mdi-airplane-takeoff</v-icon>
            <span class="ml-3 badge badge-danger badge-lg mt-1">วันที่ออกเดินทาง {{flight.depart | moment("DD MMM YYYY")}}</span>
        </v-row>
        <div class="mt-4">
            <span class="badge badge-info">สนามบินขาขึ้น</span> {{flight.flightAirports[0].airport.name+" จังหวัด"+flight.flightAirports[0].airport.city.name}}
        </div>
        <div>
            <span class="badge badge-info">สนามบินขาลง</span> {{flight.flightAirports[1].airport.name+" จังหวัด"+flight.flightAirports[1].airport.city.name}}
        </div>
        <div class="card p-2 m-2">
            เวลาออกเดินทาง {{flight.depart | moment("HH:mm")}} น. ถึง {{flight.arrive | moment("HH:mm")}} น.
            <br>
            ใช้เวลา {{getDuration(flight.arrive,flight.depart)}}
        </div>
        <div class="badge badge-success">ราคา {{flight.price|price}} บาท</div>
    </div>
</div>
</template>

<script>
var numeral = require("numeral");
export default {
    props:{
        flight:{
            type: Object
        }
    },
    filters:{
        price(price){
        // add this on top
        // var numeral = require("numeral");
        return numeral(price).format("0,0.00");
        }
    },
    methods:{
        getDuration(end, start) {
            let arrive = new Date(end);
            let depart = new Date(start);
            const diffTime = Math.abs(arrive - depart);
            const diffMinute = Math.ceil(diffTime / (1000 * 60));
            const diffHour = Math.ceil(diffMinute / 60) - 1;
            const minute = diffMinute % 60;
            const hour = diffHour;
            if (diffHour == 0) {
                return minute + " นาที";
            }
            return hour + " ชม. " + minute + " นาที";
        },
    }
}
</script>

<style>

</style>