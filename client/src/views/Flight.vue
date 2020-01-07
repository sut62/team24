<template>
  <div class="checkIn">
    <UserNavbar/>

    <div class="container" style="margin-top:auto;">
      <div class="mx-auto">
        <div class="my-content">
          <div class="text-black" style="width: auto;">
            <div class="topic">
              <h1 class="mt-5 topic">ระบบเพิ่มลดเที่ยวบิน</h1>
            </div>
          </div>
          <Search/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Search from '../components/Flight/Flight'
import UserNavbar from '../components/AdminNavBar'

export default {
  name: 'checkIn',
  data: ()=>({
    
  }),
  components: {
    Search,
    UserNavbar
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

</style>