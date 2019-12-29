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
        <v-dialog v-if="!logedIn" v-model="showAuthForm" max-width="400">
          <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">เข้าสู่ระบบ</v-btn>
          </template>
          <LoginForm :closeDialog = "closeDialog"/>
        </v-dialog>
        <span v-if="logedIn">
          <div class="dropdown ">
            <button class="px-10 btn btn-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span class=""><v-icon>mdi-account</v-icon> ชื่อผู้ใช้ {{user.username}} </span>
            </button>
            <div class="dropdown-menu px-3" style="width:100%" aria-labelledby="dropdown" justify-center>
              <span class="dropdawn-item" style="height:50px"><span style="color:black">E-Mail:</span> {{user.email}}</span>
              <a class="dropdown-item px-0" style="color:red;" @click="logout">ออกจากระบบ</a>
            </div>
          </div>
          
          
        </span>
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
    isLogin(){
      let user =localStorage.getItem('user')
      if(user != null){
        return true;
      }
      else 
        return false
    }
  },
  watch:{
    // logedIn(logedIn){
    //   console.log(logedIn)
    // }
  },
  computed:{
    ...mapState({
      'logedIn': state => state.Auth.logedIn,
      'user': state => state.Auth.user
    })
  }
}
</script>

<style scoped>
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
  }
</style>