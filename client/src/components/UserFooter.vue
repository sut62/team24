<template>
  <div>
    <v-footer
      color="primary lighten-1"
      padless
    >
      <v-row
        justify="center"
        no-gutters
      >
        <v-btn
          v-for="(link,index) in links"
          :key="index"
          :href="link.path"
          color="white"
          text
          rounded
          class="my-2"
        >
          {{ link.name }}
        </v-btn>
        <v-col
          class="primary lighten-2 py-4 text-center white--text"
          cols="12"
        >
          @ {{ new Date().getFullYear() }} — <strong>Airplane</strong>
        </v-col>
      </v-row>
    </v-footer>
  </div>
</template>

<script>
export default {
  name: "userFooter",
  data:()=>({
    links: [
      {
        name:'เริ่มจองเที่ยวบิน',
        path: '/book-flight'
      },
      {
        name:'เช็คอิน',
        path: '/checkIn'
      },
      {
        name:'ชำระเงิน',
        path: '/payment'
      },
      {
        name:'เกี่ยวกับเรา',
        path: '/about'
      },
    ],
  })
}
</script>

<style>

</style>