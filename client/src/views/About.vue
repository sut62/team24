<template>
  <v-app class="">
    <userHeader/>
     <v-content>
      <!-- Provides the application the proper gutter -->
      <v-container fluid>
        <div class= "mx-auto" style="top:100px;position:relative" >
          <div class="text-center h1">About us</div>
          <hr>
          <div class="text-center h1">สมาชิก</div><hr>
          <div class="text-center h4">B6000035 นางสาวอริสา รังกลาง</div>
          <div class="text-center h4">B6000530 นายกันตพงศ์ พูนเกษม</div>
          <div class="text-center h4">B6005818 นายชยากร พิลึกนา</div>
          <div class="text-center h4">B6012540 นายเปี่ยมพูล พูลเปี่ยม</div>
          <div class="text-center h4">B6016357 นายไตรรงค์ บำรุงเกตุอุดม</div>
          <div class="text-center h4">B6026066 นายคณิศร ศรีแสงทรัพย์</div>
          <hr>
        </div>
      </v-container>
    </v-content>
    <userFooter/>
  </v-app>
</template>

<script>
import userHeader from '../components/UserNavbar'
import userFooter from '../components/UserFooter'

export default {
  components:{
    userHeader,
    userFooter
  }
  
}
</script>

<style scope>
  .content{
    height: 1000px;
  }
</style>