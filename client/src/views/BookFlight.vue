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
                <h1 class="mt-5">เริ่มทริปของคุณที่นี่</h1>
              </div>
            </div>
            <Search/>
          </div>
          <BookNav/>
          <FlightList topic="ขาไป ขอนแก่น ถึง กรุงเทพฯ" :flights = "flightDepart"/>
          <FlightList topic="ขากลับ กรุงเทพฯ ถึง ขอนแก่น" :flights = "flightReturn"/>
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
import {mapActions,mapState} from 'vuex'

export default {
  name: 'boookFlight',
  data: ()=>({
    
  }),
  components: {
    Search,
    UserNavbar,
    UserFooter,
    BookNav,
    FlightList,
    CartFooter
  },
  computed: {
    ...mapState({
      flightDepart: state => state.BookFlight.result.flightDepart,
      flightReturn: state => state.BookFlight.result.flightReturn
    })
  },
  methods: {
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
  .container{
    padding-left: 100px;
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