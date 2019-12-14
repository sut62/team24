<template>
  <div>
    <h1>ค้นหาเที่ยวบิน</h1>
    <v-text-field @click="showDatePick =! showDatePick" v-model="dateRangeText" label="เลือกวันเวลาไป-กลับ" readonly></v-text-field>
    <v-date-picker v-if="showDatePick" v-model="dates" range></v-date-picker>
  </div>
</template>

<script>
export default {

  name: 'search',

  data: () => ({
    showDatePick: false,
    dates: ['2019-09-10', '2019-09-20'],
  }),
  computed: {
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  },
};
</script>
