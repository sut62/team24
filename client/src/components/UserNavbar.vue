<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
        <div class="my-2 my-lg-0">
          <a href="/" class="a btn">เริ่มจองเที่ยวบิน</a>
          <a href="/promotion" class="btn">โปรโมชั่น</a>
          <a href="/checkin" class="btn">เช็คอิน</a>
          <a href="/payment" class="btn mr-5">ชำระค่าเที่ยวบิน</a>
        </div>
        <v-dialog v-model="showAuthForm" max-width="400">
          <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">เข้าสู่ระบบ</v-btn>
          </template>
          <LoginForm :closeDialog = "closeDialog"/>
        </v-dialog>
      </div>
    </nav>
    <v-avatar class="logo" size="100"> 
      <img
        src="../assets/logo.png"
        alt="John"
      >
    </v-avatar>
  </div>
</template>

<script>
import LoginForm from '../components/Auth/UserLoginForm'

export default {
  name: "userNavbar",
  components:{
    LoginForm
  },
  data:()=>({
    showAuthForm:false,
  }),
  methods:{
    closeDialog(){
      this.showAuthForm = false
    },
    openAuthForm(){
      this.showAuthFrom = true;
    }
  }
}
</script>

<style scoped>
  .logo{
    position: absolute;
    top: 30px;
    left: 10%;
  }
</style>