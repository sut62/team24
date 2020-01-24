<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
        <div class="my-2 my-lg-0">
          <a href="/adds-on" class="a btn text-light">เพิ่มบริการเสริมพิเศษ</a>
          <a href="/promotion" class="btn text-light">เพิ่มโปรโมชั่น</a>
          <a href="/flight" class="btn text-light">จัดการเที่ยวบิน</a>
          <a href="/verify-checkIn" class="btn text-light">ตรวจสอบการเช็คอิน</a>    
          <a href="/Check-Payment" class="btn text-light">ตรวจสอบการชำระเงิน</a>    
        </div>
        <v-dialog persistent v-if="!this.isLoginAsAdmin()" v-model="showAuthForm" max-width="400">
          <template v-slot:activator="{ on }">
            <v-btn color="primary" dark v-on="on">เข้าสู่ระบบแอดมิน</v-btn>
          </template>
          <LoginForm :message="this.formMessage" :closeDialog = "closeDialog"/>
        </v-dialog>
        <span v-if="this.isLoginAsAdmin()">
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
    formMessage:''
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
    sendFormMessage(msg){
      this.formMessage = msg;
    },
    isLoginAsAdmin(){
      if(!this.logedIn){
        return false
      }
      for(let role of this.user.roles){
        if(role == "ROLE_ADMIN"){
          return true;
        }
      }
      this.formMessage = "กรุณาใช้ Account Admin ในการเข้าสู่ระบบ"
      return false;
    }
  },
  watch:{
    logedIn(){
      this.formMessage = ""
      if(!this.isLoginAsAdmin()){
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
    if(!this.isLoginAsAdmin()){
      this.showAuthForm = true
    } 
    this.showAuthForm = true
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