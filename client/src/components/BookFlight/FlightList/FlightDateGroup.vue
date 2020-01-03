<template>
  <div :key="update">
    <v-row justify="space-around">
      <div>
        <v-icon>mdi-chevron-left-box</v-icon>
      <button @click="getFlight(-3)" class="mybtn btn btn-primary  text-center">{{ getDate(-3) | moment("dd")  }}<br> {{ getDate(-3) | moment("DD MMMM")  }}</button>
      </div>
      <button @click="getFlight(-2)" class="mybtn btn btn-primary text-center">{{ getDate(-2) | moment("dd")  }}<br> {{ getDate(-2) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(-1)" class="mybtn btn btn-primary text-center">{{ getDate(-1) | moment("dd")  }}<br> {{ getDate(-1) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(0)" class="selected btn btn-primary text-center">{{ getDate(0) | moment("dd") }} <br> {{ getDate(0) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(1)" class="mybtn btn btn-primary text-center">{{ getDate(1) | moment("dd")  }}<br> {{ getDate(1) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(2)" class="mybtn btn btn-primary text-center">{{ getDate(2) | moment("dd")  }}<br> {{ getDate(2) | moment("DD MMMM")  }}</button>
      <div>
      <button @click="getFlight(3)" class="mybtn btn btn-primary text-center">{{ getDate(3) | moment("dd")  }}<br> {{ getDate(3) | moment("DD MMMM")  }}</button>
      <v-icon>mdi-chevron-right-box</v-icon>
      </div>
    </v-row>
  </div>
</template>

<script>
import {mapState,mapActions} from 'vuex'
import Moment from 'moment'
export default {
  data:()=>({
    update: 1
  }),
  props:{
    isDepart:{
      type: Boolean
    }
  },
  computed:{
    ...mapState({
      'departDate': state => state.BookFlight.data.departDate,
      'returnDate': state => state.BookFlight.data.returnDate,
    })
  },
  methods:{
    ...mapActions({
      getFlightDepart: 'BookFlight/getFlightDepart',
      getFlightReturn: 'BookFlight/getFlightReturn',
    }),
    getDate(count){
      var day
      if(this.isDepart){
        day = new Date(this.departDate);
      }else{
        day = new Date(this.returnDate);
      }
      day.setDate(day.getDate() + count);
      return day
    },
    getFlight(count){
      var day
      if(this.isDepart){
        day = new Date(this.departDate);
      }else{
        day = new Date(this.returnDate);
      }
      day.setDate(day.getDate() + count);
      let date = Moment(day).format("YYYY-MM-DD")
      if(this.isDepart){
        this.getFlightDepart(date)
      }else{
        this.getFlightReturn(date)
      }
    }
  },
  watch:{
    departDate(){
      this.update += 1 % 2
    },
    returnDate(){
      this.update += 1 % 2
    }
  }
}
</script>

<style scope>
  .mybtn{
    height: 80px;
    width: 150px;
    border-radius: 10px;
    background-color: rgb(70, 70, 70);
    border-style: solid;
    border-color: white;
    border-width: 1px;
    padding: 10px;
  }
  .selected{
    position: relative;
    top: -5px;
    width: 150px;
    height: 90px;
    border-radius: 10px;
    border-style: solid;
    border-color: white;
    border-width: 1px;
  }
</style>