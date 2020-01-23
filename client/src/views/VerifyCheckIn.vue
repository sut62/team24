<template>
  <div> 
  <NavBar/>
  <div class=" mt-10 mx-auto container">
      <h1>ตรวจสอบการเช็คอิน</h1>
  </div>
   <div class=" container" style="margin-top:auto;">
      <div class="mx-auto">
        <div class="my-content">
          
         <showCheckIn></showCheckIn>
        </div>
      </div>
    </div>
    
  </div>
</template>


<script>
//   import axios from "../../src/api/http_request";
  import NavBar from '../components/AdminNavBar'
  import showCheckIn from '../components/VerifyCheckIn/ShowCheckIn.vue';
  export default {
    name: 'Verify',
    components: {
        NavBar,
        showCheckIn
    },
    data() {
      return {
        
      }
    },

    methods: {
      
        
    
  },
  
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>


  .header-bg{
    height: 300px;
    width: 100%;
    background-size: cover;
    background-color: #ECEFF1;
    
  }
  .container{
    margin: auto;
    padding: 20px;
    

  }

  .my-content{
    width: 80%;
    margin: auto ;
    text-align: center;
  }

</style>
