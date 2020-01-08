<template>
  <div class="checkIn">
    <UserNavbar/>
    <div class=" container" style="margin-top:auto;">
      <div class="mx-auto">
        <div class="my-content">
          
          <Search/>
        </div>
      </div>
    </div>
    <UserFooter/>
  </div>
</template>

<script>
// @ is an alias to /src
import Search from '../components/CheckIn/CheckIn'
import UserNavbar from '../components/UserNavbar'
import UserFooter from '../components/UserFooter'
export default {
  name: 'checkIn',
  data: ()=>({
    
  }),
  components: {
    Search,
    UserNavbar,
    UserFooter
  },
}
</script>

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
    
  }
</style>