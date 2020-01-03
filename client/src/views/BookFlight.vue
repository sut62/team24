<template>
  <div class="bookFlight">
    <div class="header-bg"></div>
    <div class="content">
      <UserNavbar/>
      <div class="container" style="margin-top:100px;">
        <div class="mx-auto">
          <div>
            <div class="text-white">
              <div >
                <h1 class="mt-5">{{pageLocation == 1 ? 'เริ่มทริปของคุณที่นี่':'เลือกเที่ยวบินของคุณ'}}</h1>
              </div>
            </div>
            <Search v-if="pageLocation <= 1"/>
          </div>
          <BookNav/>
          <!-- หน้าแรก -->
          <div v-if="pageLocation == 1">
            <v-card class="text-center mt-4 py-12">
              <p style="color:grey">
                กรุณาเลือกวันที่ออกเดินทาง
              </p>
            </v-card>
          </div>
          <!-- แสดงรายการบิน -->
          <div v-if="pageLocation == 2">
            <FlightList :isDepart="true" :selectedFlight="selectedDepartFlight" :selectFlight="selectDepartFlight" :topic="topicDepart" :flights = "flightDepart"/>
            <FlightList :isDepart="false" :selectedFlight="selectedReturnFlight" :selectFlight="selectReturnFlight" :topic="topicReturn" :flights = "flightReturn"/>
          </div>
          <!-- ข้อมูลผู้โดยสาร -->
          <div v-if="pageLocation == 3">
            <GuestDetail/>
          </div>
        </div>
      </div>
      <br>
      <UserFooter style="padding-bottom: 100px;"/>
      <CartFooter/>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Search from '../components/BookFlight/Search'
import UserNavbar from '../components/UserNavbar'
import UserFooter from '../components/UserFooter'
import BookNav from '../components/BookFlight/BookNav'
import FlightList from '../components/BookFlight/FlightList/FlightList'
import CartFooter from '../components/BookFlight/CartFooter'
import GuestDetail from '../components/BookFlight/GuestDetail'
import {mapMutations,mapActions,mapState} from 'vuex'

export default {
  name: 'boookFlight',
  components: {
    Search,
    UserNavbar,
    UserFooter,
    BookNav,
    FlightList,
    CartFooter,
    GuestDetail
  },
  computed: {
    ...mapState({
      airportDepart: state => state.BookFlight.data.airportDepart,
      airportArrive: state => state.BookFlight.data.airportArrive,
      pageLocation: state => state.BookFlight.pageLocation,
      flightDepart: state => state.BookFlight.result.flightDepart,
      flightReturn: state => state.BookFlight.result.flightReturn,
      selectedDepartFlight: state => state.BookFlight.data.flightDepart,
      selectedReturnFlight: state => state.BookFlight.data.flightReturn,
    }),
    topicDepart(){
      return 'ไป : จาก ' + this.airportDepart.city.name + " [" + this.airportDepart.name + "]" + ' ถึง ' + this.airportArrive.city.name + " [" + this.airportArrive.name + "]"
    },
    topicReturn(){
      return 'กลับ : จาก ' + this.airportArrive.city.name + " [" + this.airportArrive.name + "]" + ' ถึง ' + this.airportDepart.city.name + " [" + this.airportDepart.name + "]"
    }
  },
  methods: {
    ...mapMutations({
      selectDepartFlight: 'BookFlight/selectDepartFlight',
      selectReturnFlight: 'BookFlight/selectReturnFlight'
    }),
    ...mapActions({
      bookFlight: 'BookFlight/book'
    })
  }
}
</script>

<style scoped>
  .header-bg{
    height: 300px;
    width: 100%;
    position: absolute;
    top: 1px;
    background-size: cover;
    /* background-color: rgb(8, 2, 43); */
    background-image: url("../assets/head-bg.jpg");
  }
  .content{
    z-index: 1;
    position: relative;
  }
  .my-right{
    position: absolute;
    right: 1px;
  }
  .my-content{
    position: absolute;
    top: 150px;
    width: 70%;
  }
</style>