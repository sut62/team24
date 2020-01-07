<template>
  <div>
    <v-card>
      <v-card-title>
        <h2 class="mx-auto">เข้าสู่ระบบ</h2>
      </v-card-title>
      <v-card-text>
        <v-text-field
          v-model="username"
          label="Username"
          required
          outlined
        ></v-text-field>
        <v-text-field
          v-model="password"
          label="Password"
          required
          outlined
        ></v-text-field>
        <dir style="color:red" v-if="message != ''">{{message}}</dir>
        <div style="color:red">{{alert.head}}<br>{{alert.result}}</div>
      </v-card-text>
      <v-card-actions>
        <button class="btn btn-lg btn-primary  btn-block" @click.stop="login">Login</button>
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