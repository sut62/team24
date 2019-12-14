<template>
  <div class="bookFlight">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
        <div class="my-2 my-lg-0">
          <button class="btn">เริ่มจองเที่ยวบิน</button>
          <button class="btn">โปรโมชั่น</button>
          <button class="btn">เช็คอิน</button>
          <button class="btn mr-5">ชำระค่าเที่ยวบิน</button>
          <button class="btn my-2 my-sm-0" type="submit">เข้าสู่ระบบ | สมัครสมาชิก</button>
        </div>
      </div>
    </nav>
    <div class="header-bg"></div>
    
    <div class=" container" style="margin-top:100px;">
      <v-avatar class="logo" size="100"> 
        <img
          src="../assets/logo.png"
          alt="John"
        >
      </v-avatar>
      <div class="mx-auto">
        <div class="my-content">
          <div class="text-white" style="width: 400px;">
            <div class="topic">
              <h1 class="mt-5 topic">เริ่มทริปของคุณที่นี่</h1>
            </div>
          </div>
          <Search/>
        </div>
      </div>
    </div>
    
    <template>
      <v-footer
        absolute
        color="primary lighten-1"
        padless
      >
        <v-row
          justify="center"
          no-gutters
        >
          <v-btn
            v-for="link in links"
            :key="link"
            color="white"
            text
            rounded
            class="my-2"
          >
            {{ link }}
          </v-btn>
          <v-col
            class="primary lighten-2 py-4 text-center white--text"
            cols="12"
          >
            @ {{ new Date().getFullYear() }} — <strong>Airplane</strong>
          </v-col>
        </v-row>
      </v-footer>
    </template>
  </div>
</template>

<script>
// @ is an alias to /src
import Search from '../components/BookFlight/Search'

export default {
  name: 'boookFlight',
  data: ()=>({
    links: [
      'เริ่มจองเที่ยวบิน',
      'เช็คอิน',
      'ชำระค่าเที่ยวบิน',
      'เข้าสู่ระบบ',
      'สมัครสมาชิก',
      'ติดต่อเรา',
    ],
  }),
  components: {
    Search
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
  .logo{
    position: absolute;
    top: 30px;
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