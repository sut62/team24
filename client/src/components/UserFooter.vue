<template>
  <div>
    <v-footer
      absolute
      color="primary lighten-1"
      padless
    >
      <v-row
        justify="center"
        no-gutters
      >
        <v-btn
          v-for="link in links"
          :key="link"
          color="white"
          text
          rounded
          class="my-2"
        >
          {{ link }}
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
      'เริ่มจองเที่ยวบิน',
      'เช็คอิน',
      'ชำระค่าเที่ยวบิน',
      'เข้าสู่ระบบ',
      'สมัครสมาชิก',
      'ติดต่อเรา',
    ],
  })
}
</script>

<style>

</style>