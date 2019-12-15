<template>
  <div class="bookFlight">
    <UserNavbar/>
    <div class="header-bg"></div>
    <div class=" container" style="margin-top:100px;">
      <div class="mx-auto">
        <div class="my-content">
          <div class="text-white" style="width: 400px;">
            <div class="topic">
              <h1 class="mt-5 topic">เริ่มทริปของคุณที่นี่</h1>
            </div>
          </div>
          <Search/>
        </div>
        <BookNav/>
        <FlightList/>
        <FlightList/>
      </div>
    </div>
    <br>
    <UserFooter style="padding-bottom: 100px;"/>
    <CartFooter/>
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
}
</script>

<style scoped>
  .header-bg{
    height: 300px;
    width: 100%;
    background-size: cover;
    /* background-color: rgb(8, 2, 43); */
    background-image: url("../assets/head-bg.jpg");
  }
  .container{
    padding-left: 100px;
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