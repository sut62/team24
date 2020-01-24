<template>
<div>
  <div class="mb-2 mt-4 alert alert-primary" style="color:blue"> {{useCurentUserData?'* ใช้ข้อมูลของผู้ใช้งาน Username : ' + this.user.username + ', Email : ' + this.user.email :''}}</div>
  <v-form v-model="valid" class="mx-auto mt-10" style="width:90%">
    <v-card class="pa-10 mt-3" >
      <h2>ข้อมูลผู้โดยสาร </h2>
      <v-divider></v-divider>
      <v-row>
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="firstname"
            :disabled="useCurentUserData"
            :rules="nameRules"
            label="ชื่อจริง"
            required
            outlined
          ></v-text-field>
        </v-col>
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="lastname"
            :rules="nameRules"
            :disabled="useCurentUserData"
            label="นามสกุล"
            required
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
      <!-- <v-row>
        <v-col cols="12" md="4">
          <v-menu
          ref="menu"
          v-model="menu"
          :close-on-content-click="true"
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field v-on="on" :rules="dateRules" required outlined v-model="dateText" label="เลือกวันเกิด" readonly></v-text-field>
            </template>
            <v-date-picker v-model="date" no-title scrollable>
            </v-date-picker>
          </v-menu>
        </v-col>
        <v-col cols="12" md="4">
          <v-radio-group required v-model="sex" class="mt-0 pt-0">
            <v-radio
              label="เพศชาย"
              :value="male"
            ></v-radio>
            <v-radio
              label="เพศหญิง"
              :value="female"
            ></v-radio>
          </v-radio-group>
        </v-col>
      </v-row> -->
    </v-card>
    <v-card class="pa-10 mt-3">
      <h2>ข้อมูลติดต่อ</h2>
      <v-divider></v-divider>
      <v-row>
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="phone"
            :rules="phoneRules"
            :disabled="useCurentUserData"
            :counter="10"
            label="เบอร์โทรติดต่อ"
            required
            outlined
          ></v-text-field>
        </v-col>
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="email"
            :rules="emailRules"
            :disabled="useCurentUserData"
            label="อีเมลล์ที่ใช้ติดต่อ"
            required
            outlined
          ></v-text-field>
        </v-col>
      </v-row>
    </v-card>
  </v-form>
</div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  name: 'GuestDetail',
  data: () => ({
    useCurentUserData: false,
    valid: false,
    firstname: '',
    lastname: '',
    phone: '',
    sex:'',
    date: '',
    dateRules:[
      v => v != 'เลือกวันเกิด' || 'ท่านจำเป็นต้องกรอกวันเกิด',
    ],
    phoneRules: [
      v => !!v || 'ท่านจำเป็นต้องกรอกเบอร์โทร',
      v => v.length == 10 || 'กรุณากรอกให้ครบ 10 หลัก',
    ],
    nameRules: [
      v => !!v || 'ท่านจำเป็นต้องกรอกชื่อ',
    ],
    email: '',
    emailRules: [
      v => !!v || 'จำเป็นต้องกรอก E-mail',
      v => /.+@.+/.test(v) || 'กรุณากรอก E-mail ให้ถูกต้อง',
    ],
  }),
  computed: {
    ...mapState({
      'user' : state => state.Auth.user,
      'logedIn' : state => state.Auth.logedIn
    }),
    dateText () {
      return this.date != '' ? 'วันที่ ' + this.date : 'เลือกวันเกิด';
    },
  },
  mounted(){
    if(this.logedIn){
      this.useCurentUserData = true
      this.email = this.user.email
      this.phone = this.user.phone
      this.firstname = this.user.firstName
      this.lastname = this.user.lastName
    }else{
      this.useCurentUserData = false
    }
  }
}
</script>

<style scoped>

</style>