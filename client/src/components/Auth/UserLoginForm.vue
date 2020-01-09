<template>
  <div>
    <v-card>
      <v-card-title>
        <h2 id="login_title" class="mx-auto">เข้าสู่ระบบ</h2>
      </v-card-title>
      <v-card-text>
        <v-text-field
          id="username"
          v-model="username"
          label="Username"
          required
          outlined
        ></v-text-field>
        <v-text-field
          id="password"
          v-model="password"
          label="Password"
          required
          outlined
        ></v-text-field>
        <dir id="sended_msg" style="color:red" v-if="message != ''">{{message}}</dir>
        <div id="alert_msg" style="color:red">{{alert.head}}</div>
        <div id="alert_msg_detail" style="color:red">{{alert.result}}</div>
      </v-card-text>
      <v-card-actions>
        <button id="login_submit" class="btn btn-lg btn-primary  btn-block" @click.stop="login">Login</button>
        <!-- <v-btn @click="closeDialog">Close</v-btn> -->
      </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import {mapActions} from 'vuex'
export default {
  name: "UserLoginForm",
  props: {
    closeDialog:{
      type: Function
    },
    message:{
      type: String
    }
  },
  data: ()=>({
    username: '',
    password: '',
    alert:''
  }),
  methods: {
    login(){
      this.requestLogin({
        username: this.username,
        password: this.password
      }).then(result => result === true? ()=>{this.closeDialog();this.alert={}}:this.alert = {head:"เกิดข้อผิดพลาด ",result})
    },
    ...mapActions({
      'requestLogin' : 'Auth/requestLogin'
    })
  }
}
</script>

<style>

</style>