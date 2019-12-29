<template>
  <div>
    <v-card>
      <v-card-title>
        <h2>เข้าสู่ระบบ</h2>
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
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn @click.stop="login">Login</v-btn>
        <v-btn @click="closeDialog">Close</v-btn>
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
    }
  },
  data: ()=>({
    username: '',
    password: ''
  }),
  methods: {
    login(){
      this.requestLogin({
        username: this.username,
        password: this.password
      })
    },
    ...mapActions({
      'requestLogin' : 'Auth/requestLogin'
    })
  }
}
</script>

<style>

</style>