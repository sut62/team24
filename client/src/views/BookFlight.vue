<template>
  <div class="bookFlight">
    <nav class="navbar navbar-dark bg-white">
      <!-- Navbar content -->
      <button class="btn-warning btn">จองเที่ยวบิน</button>
    </nav>

    <div class="container">
      <div class="mx-auto" style="width: 400px;">
        <div class="topic">
          <h1 class="mt-5">เริ่มทริปของคุณที่นี่</h1>
        </div>
      </div>
      <Search/>
    </div>
    
  </div>
</template>

<script>
// @ is an alias to /src
import Search from '../components/BookFlight/Search'

export default {
  name: 'boookFlight',
  components: {
    Search
  }
}
</script>
