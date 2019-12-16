<template>
  <div>
    <h3 class="text-white">ค้นหาเที่ยวบิน</h3>
    <div class="card text-center">
      <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs">
          <li class="nav-item">
            <a class="nav-link active" href="#">เที่ยวบิน</a>
          </li>
        </ul>
      </div>
      <div class="card-body">
        <h5 class="card-title">เริ่มต้นการเดินทาง</h5>
        <v-row>
          <v-col cols="2">
            <v-autocomplete
              v-model="select"
              :items="items"
              outlined
              label="จาก"
              filled
            ></v-autocomplete>
          </v-col>
          <v-col cols="2">
            <v-autocomplete
              v-model="select"
              :items="items"
              outlined
              label="ถึง"
              filled
            ></v-autocomplete>
          </v-col>
          <v-col cols="4">
            <v-text-field @click="showDatePick =! showDatePick" outlined v-model="dateRangeText" label="เลือกวันเวลาไป-กลับ" readonly></v-text-field>
            <v-date-picker v-if="showDatePick" style="z-index:1;" class="date-picker" v-model="dates" range></v-date-picker>
          </v-col>
          <v-col cols="4">
            <a href="#" class="btn btn-danger text-white btn-lg" style="width:100%;">ค้นหา</a>
          </v-col>
        </v-row>
      </div>
    </div>
    <hr/>
    
  </div>
</template>

<script>
export default {

  name: 'search',

  data: () => ({
    showDatePick: false,
    dates: ['2019-09-10', '2019-09-20'],
    select: 'Programming',
    items: [
      'กรุงเทพ',
      'ขอนแก่น',
      'ภูเก็ต',
      'เลย',
    ],
  }),
  computed: {
    dateRangeText () {
      return this.dates.join(' ~ ')
    },
  },
};
</script>

<style scoped>
  .date-picker{
    position: absolute;
    left: 20px;
    top: 70px;
    width: 500px;
  }
</style>