<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
        <div class="my-2 my-lg-0">
          <a href="/book-flight" class="a btn">เริ่มจองเที่ยวบิน</a>
          <a href="/check-ticket" class="a btn">เช็คการจองตั๋ว</a>
          <a href="/checkin" class="btn">เช็คอิน</a>
          <a href="/payment" class="btn">ชำระค่าเที่ยวบิน</a>
          <a href="/about" class="btn mr-5">เกี่ยวกับเรา</a>

        </div>
        <v-dialog persistent v-if="!this.isLoginAsCustomer()" v-model="showAuthForm" max-width="400">
          <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">เข้าสู่ระบบ</v-btn>
          </template>
          <LoginForm :message="this.formMessage" :closeDialog = "closeDialog"/>
        </v-dialog>
        <span v-if="this.isLoginAsCustomer()">
          <div class="dropdown ">
            <button style="width:300px" class="px-10 btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class="" id="user_info_bar"><v-icon>mdi-account</v-icon> ชื่อผู้ใช้ {{user.username}} </span>
            </button>
            <div class="dropdown-menu px-3" style="width:300px" aria-labelledby="dropdown" justify-center>
              <span class="dropdawn-item" style="height:50px; color:gray"><span style="color:black">e-mail:</span> {{user.email}}</span>
              <br>
              <span class="dropdawn-item" style="height:50px; color:gray"><span style="color:black">name:</span> {{user.firstName}} {{user.lastName}}</span>

              <div id="logout_button" class="dropdown-item px-0 btn logout-btn" @click="logout">ออกจากระบบ</div>
            </div>
          </div>
        </span>
      </div>
    </nav>
    <v-avatar class="logo" size="100"> 
      <img
        src="../assets/logo.png"
        alt="John"
        class="shadow"
      >
    </v-avatar>
  </div>
</template>

<script>
import LoginForm from '../components/Auth/UserLoginForm'
import {mapActions,mapState} from 'vuex'

export default {
  name: "userNavbar",
  components:{
    LoginForm,
  },
  data:()=>({
    showAuthForm:false,
    
  }),
  methods:{
    ...mapActions({
      'logout': 'Auth/logout'
    }),
    closeDialog(){
      this.showAuthForm = false
    },
    openAuthForm(){
      this.showAuthFrom = true;
    },
    isLoginAsCustomer(){
      if(!this.logedIn){
        return false
      }
      for(let role of this.user.roles){
        if(role == "ROLE_MEMBER"){
          return true;
        }
      }
      this.formMessage = "กรุณาใช้ Account Customer ในการเข้าสู่ระบบ"
      return false;
    }
  },
  watch:{
    logedIn(){
      this.formMessage = ""
      if(!this.isLoginAsCustomer()){
        this.showAuthForm = true
      } 
    }
  },
  computed:{
    ...mapState({
      'logedIn': state => state.Auth.logedIn,
      'user': state => state.Auth.user
    })
  },
  mounted(){
    if(!this.isLoginAsCustomer()){
      this.showAuthForm = true
    } 
    this.showAuthForm = true
  }
}
</script>

<style scoped>
  .logout-btn{
    color:white;
    background-color: rgb(221, 42, 42);
    text-align: center;
  }
  .logout-btn:hover {
    color:white;
    background-color: rgb(167, 34, 34);
    text-align: center;
  }
  .my-lable{
    border-radius: 10px;
    border-style: solid;
    background-color: rgb(110, 106, 106);
    color:white;
  }
  .logo{
    position: absolute;
    top: 30px;
    left: 10%;
    box-shadow: 0 8px 6px -6px black;
  }
  .shadow {
    -webkit-box-shadow: 0 8px 6px -16px black;
    -moz-box-shadow: 0 8px 6px -6px black;
    box-shadow: 0 8px 6px -6px black;
  }
</style>