<template>
  <div :key="update">
    <v-row justify="space-around">
      <div>
        <v-icon color="rgb(216, 216, 216)">mdi-chevron-left-box</v-icon>
      <button @click="getFlight(-3)"  :disabled="!dateCheck(-3)" :class="{'shadow mybtn btn btn-primary text-center':dateCheck(-3),'shadow mybtn-disable btn btn-secondary text-center':!dateCheck(-3)}">{{ getDate(-3) | moment("dd")  }}<br> {{ getDate(-3) | moment("DD MMMM")  }}</button>
      </div>
      <button @click="getFlight(-2)"  :disabled="!dateCheck(-2)" :class="{'shadow mybtn btn btn-primary text-center':dateCheck(-2),'shadow mybtn-disable btn btn-secondary text-center':!dateCheck(-2)}">{{ getDate(-2) | moment("dd")  }}<br> {{ getDate(-2) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(-1)"  :disabled="!dateCheck(-1)" :class="{'shadow mybtn btn btn-primary text-center':dateCheck(-1),'shadow mybtn-disable btn btn-secondary text-center':!dateCheck(-1)}">{{ getDate(-1) | moment("dd")  }}<br> {{ getDate(-1) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(0)" :disabled="!dateCheck(0)"  :class="{'selected shadow btn btn-primary text-center':dateCheck(0),'selected shadow btn btn-primary text-center':dateCheck(0)}">{{ getDate(0) | moment("dd") }} <br> {{ getDate(0) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(1)"  :disabled="!dateCheck(1)" :class="{'shadow mybtn btn btn-primary text-center':dateCheck(1),'shadow mybtn-disable btn btn-secondary text-center':!dateCheck(1)}">{{ getDate(1) | moment("dd")  }}<br> {{ getDate(1) | moment("DD MMMM")  }}</button>
      <button @click="getFlight(2)"  :disabled="!dateCheck(2)" :class="{'shadow mybtn btn btn-primary text-center':dateCheck(2),'shadow mybtn-disable btn btn-secondary text-center':!dateCheck(2)}">{{ getDate(2) | moment("dd")  }}<br> {{ getDate(2) | moment("DD MMMM")  }}</button>
      <div>
      <button @click="getFlight(3)"  :disabled="!dateCheck(3)" :class="{'shadow mybtn btn btn-primary text-center':dateCheck(3),'shadow mybtn-disable btn btn-secondary text-center':!dateCheck(3)}">{{ getDate(3) | moment("dd")  }}<br> {{ getDate(3) | moment("DD MMMM")  }}</button>
      <v-icon color="rgb(216, 216, 216)">mdi-chevron-right-box</v-icon>
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
    }),
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
    },
    dateCheck(count){
      if(this.isDepart){
        let date = new Date(this.departDate)
        let todayString = Moment(new Date()).format("YYYY-MM-DD")
        if(date.setDate(date.getDate() + count ) - new Date(todayString) < 0){
          return false
        }else{
          return true;
        }
      }
      let returnDate = new Date(this.returnDate)
      returnDate.setDate(returnDate.getDate() + count)
      let diffTime = (new Date(returnDate) - new Date(this.departDate))

      if(diffTime < 0){
        return false
      }else{
        return true
      }
    }
  },
  watch:{
    departDate(){
      this.update += 1 % 2
    },
    returnDate(){
      this.getFlightReturn(this.returnDate)
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
    background-color: rgb(57, 127, 206);
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
    border-color: rgb(5, 52, 139);
    border-width: 3px;
  }
  .selected:hover{
    position: relative;
    top: -5px;
    width: 150px;
    height: 90px;
    border-radius: 10px;
    border-style: solid;
    border-color: rgb(0, 34, 97);
    border-width: 3px;
  }
  .mybtn-disable{
    height: 80px;
    width: 150px;
    border-radius: 10px;
    background-color: rgb(100, 100, 100);
    border-style: solid;
    border-color: white;
    border-width: 1px;
    padding: 10px;
  }
  .shadow {
    box-shadow: 10px 10px 5px grey;
  }
</style>