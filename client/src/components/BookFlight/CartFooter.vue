<template>
  <div class="footer" >
    <v-row>
      <v-col>
      </v-col>
      <v-col>
        <div class="mt-3">
          <i class="fas fa-shopping-cart fa-2x" ></i> <span class="h4 ml-3">ราคารวม </span><span class="h1"> {{getTotalPrice}} THB</span>
        </div>
      </v-col>
      <v-col cols="2">
        <div @click="bookFlight" class="btn btn-danger btn-block btn-lg pt-4" style="height:100%;">
          ดำเนินการต่อ
        </div>
      </v-col>
      <v-col>
      </v-col>
    </v-row>
    
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
export default {
  name: "cartFooter",
  computed: mapGetters({
    getTotalPrice: 'BookFlight/getTotalPrice'
  }),
  methods: mapActions({
    bookFlight: 'BookFlight/bookFlight'
  })
}
</script>

<style scoped>
.footer {
  position: fixed;
  height:100px;
  left: 0;
  bottom: 0;
  width: 100%;
  background-color: rgb(66, 65, 65);
  color: white;
  text-align: center;
}
</style>