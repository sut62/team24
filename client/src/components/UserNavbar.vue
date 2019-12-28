<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
        <div class="my-2 my-lg-0">
          <a href="/" class="a btn">เริ่มจองเที่ยวบิน</a>
          <a href="/promotion" class="btn">โปรโมชั่น</a>
          <a href="/checkin" class="btn">เช็คอิน</a>
          <a href="/payment" class="btn mr-5">ชำระค่าเที่ยวบิน</a>
          <a class="btn my-2 my-sm-0" type="submit">เข้าสู่ระบบ | สมัครสมาชิก</a>
        </div>
      </div>
    </nav>
    <v-avatar class="logo" size="100"> 
      <img
        src="../assets/logo.png"
        alt="John"
      >
    </v-avatar>
  </div>
</template>

<script>
export default {
  name: "userNavbar",
}
</script>

<style scoped>
  .logo{
    position: absolute;
    top: 30px;
    left: 10%;
  }
</style>