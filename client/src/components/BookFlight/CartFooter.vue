<template>
  <div class="footer" >
    <v-row>
      <v-col cols="6">
        <div class="mt-3 float-right">
          <i class="fas fa-shopping-cart fa-2x" ></i> <span class="h4 ml-3">ราคารวม </span><span class="h1"> {{getTotalPrice}} THB</span>
        </div>
      </v-col>
      <v-col cols="6">
        <div class="mx-12">
          <button @click="nextPage" :disabled="getTotalPrice == 0" class="btn btn-danger btn-block btn-lg pt-4" style="height:100%;">
          ดำเนินการต่อ
          </button>
        </div>
      </v-col>
    </v-row>
    
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  name: "cartFooter",
  computed: {
    ...mapGetters({
      getTotalPrice: 'BookFlight/getTotalPrice'
    }),
  },
  methods: mapActions({
    'nextPage' : 'BookFlight/nextPage'
  }),
}
</script>

<style scoped>
.btn{
  max-width: 300px
}
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