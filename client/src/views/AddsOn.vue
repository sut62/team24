<template>
  <div> 
  <NavBar/>
  <div class=" mt-10 mx-auto container">
      <h1>เพิ่มบริการเสริมพิเศษ</h1>
      <v-card class="p-5">
          <v-row>
              <v-col>
                  <v-icon size="250">mdi-bag-checked</v-icon>
              </v-col>
              <v-col>
              <div class="mt-5"></div>
                  <h1 >สัมภาระเช็คอิน</h1>
                  <div @click="openDialog = true" class=" mt-6 btn btn-block btn-primary btn-lg">Edit</div>
              </v-col>
          </v-row>
      </v-card>
  </div>
  
  <Baggage :openDialog="openDialog" :closeDialog="closeDialog"/>
  
  </div>
</template>


<script>
//   import axios from "../../src/api/http_request";
  import NavBar from '../components/AdminNavBar'
  import Baggage from '../components/AddsOn/Baggage.vue'
  export default {
    name: 'AddsOn',
    components: {
        NavBar,
        Baggage
    },
    data() {
      return {
          openDialog:false,
        modal: false,
        
      }
    },

    methods: {
        closeDialog(){
            this.openDialog = false
        }
        
    // getBaggagetype() {
    //   axios.http
    //     .get("http://localhost:9000/baggagetype")
    //     .then(bag => {
    //       this.btypename = bag.data;
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },

    // getImage() {
    //   axios.http
    //     .get("http://localhost:9000/bagimage")
    //     .then(bimage => {
    //       this.imagename = bimage.data;
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // }
    
  },
  
  mounted() {
    // this.getBaggagetype();
    // this.getImage();
    // this.getWorktype();
  }

    
    
    
    
    }
  

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


.my-bg {
  padding-top: 100px;
  background: url("https://hdqwalls.com/wallpapers/person-walking-on-road-with-suitcase-kv.jpg") center center;
  min-height: 100vh;
  height: 50%;
  margin: 0px;
  background-size: cover;
  background-attachment: fixed;
}
.my-content{
  background-color: rgba(300, 300, 300, 0.900);
  border-radius: 30px;
  padding:30px;
}
</style>
