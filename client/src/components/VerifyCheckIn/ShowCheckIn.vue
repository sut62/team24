<template>
  <div>
    <v-card class="my-card container">
    <v-row>
      <v-navigation-drawer absolute permanent left>
        <template v-slot:prepend>
          <v-list-item two-line>
            <v-list-item-avatar>
               <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Jane Smith</v-list-item-title>
              <v-list-item-subtitle>สภานะเช็คอิน : ยืนยัน</v-list-item-subtitle>
               <v-list-item-subtitle>เช็คอินผ่านทาง : Webside</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
        <v-divider></v-divider>
        <v-list dense>
          <v-list-item v-for="item in items" :key="item.title">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
       </v-row>
       <div class="text-card">
       <v-col><div><v-icon color="blue darken-2">mdi-calendar-range </v-icon>วันที่ทำการเช็คอิน : 15/15/2501</div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> สนามบินต้นทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องออก : 12/12/12 </div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-landing</v-icon> สนามบินปลายทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องถึง : 12/12/12</div></v-col>
       </div>
    </v-card>
       <v-card class="my-card container">
    <v-row>
      <v-navigation-drawer absolute permanent left>
        <template v-slot:prepend>
          <v-list-item two-line>
            <v-list-item-avatar>
               <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Jane Smith</v-list-item-title>
              <v-list-item-subtitle>สภานะเช็คอิน : ยืนยัน</v-list-item-subtitle>
               <v-list-item-subtitle>เช็คอินผ่านทาง : Webside</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
        <v-divider></v-divider>
        <v-list dense>
          <v-list-item v-for="item in items" :key="item.title">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
       </v-row>
       <div class="text-card">
       <v-col><div><v-icon color="blue darken-2">mdi-calendar-range </v-icon>วันที่ทำการเช็คอิน : 15/15/2501</div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> สนามบินต้นทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องออก : 12/12/12 </div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-landing</v-icon> สนามบินปลายทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องถึง : 12/12/12</div></v-col>
       </div>
    </v-card>
    <v-card class="my-card container">
    <v-row>
      <v-navigation-drawer absolute permanent left>
        <template v-slot:prepend>
          <v-list-item two-line>
            <v-list-item-avatar>
               <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Jane Smith</v-list-item-title>
              <v-list-item-subtitle>สภานะเช็คอิน : ยืนยัน</v-list-item-subtitle>
               <v-list-item-subtitle>เช็คอินผ่านทาง : Webside</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
        <v-divider></v-divider>
        <v-list dense>
          <v-list-item v-for="item in items" :key="item.title">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
       </v-row>
       <div class="text-card">
       <v-col><div><v-icon color="blue darken-2">mdi-calendar-range </v-icon>วันที่ทำการเช็คอิน : 15/15/2501</div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> สนามบินต้นทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องออก : 12/12/12 </div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-landing</v-icon> สนามบินปลายทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องถึง : 12/12/12</div></v-col>
       </div>
    </v-card>
    <v-card class="my-card container">
    <v-row>
      <v-navigation-drawer absolute permanent left>
        <template v-slot:prepend>
          <v-list-item two-line>
            <v-list-item-avatar>
               <img src="../../assets/iconfinder_profle_1055000.png" alt="profilePicture" />
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>Jane Smith</v-list-item-title>
              <v-list-item-subtitle>สภานะเช็คอิน : ยืนยัน</v-list-item-subtitle>
               <v-list-item-subtitle>เช็คอินผ่านทาง : Webside</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </template>
        <v-divider></v-divider>
        <v-list dense>
          <v-list-item v-for="item in items" :key="item.title">
            <v-list-item-icon>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-navigation-drawer>
       </v-row>
       <div class="text-card">
       <v-col><div><v-icon color="blue darken-2">mdi-calendar-range </v-icon>วันที่ทำการเช็คอิน : 15/15/2501</div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-takeoff</v-icon> สนามบินต้นทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องออก : 12/12/12 </div></v-col>
       <v-col><div><v-icon color="blue darken-2">mdi-airplane-landing</v-icon> สนามบินปลายทาง: สุวรรณภูมิ<v-icon color="blue darken-2">mdi-slash-forward </v-icon> เครื่องถึง : 12/12/12</div></v-col>
       </div>
    </v-card>
  </div>
</template>

<script>

export default {
  name: "showCheckIn",
  data: () => ({
    items: [
      { title: "BoradingPass: DSDADS", icon: "mdi-barcode" },
      { title: "Email : dsadas@sddsa.com", icon: "mdi-at" },
      { title: "Phone: 0897417464", icon: "mdi-phone" },
    ]
  }),

  components: {},

  methods: {
    mounted() {}
  }
};
</script>

<style scoped>
.my-card {
  width: 95%;
  height: 250px;
  text-align: center;
  border: 10px;
  margin-bottom: 10px;
}
.center {
  position: absolute;
  left: 0;
  top: 50%;
  width: 100%;
  text-align: center;
  font-size: 18px;
}
.text-card{
    text-align:left;
    margin-left: 40%;
    margin-top: 2%;
    margin-bottom: 2%;

}
</style>