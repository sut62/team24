<template>
<div>
    <NavBar />
    <div class=" mt-10 mx-auto container">
        <div class="row">
            <div class="col-6">
                <h1>ตรวจสอบการชำระเงิน</h1>
            </div>
        </div>
    <Check :mode="mode"></Check>   
    </div>

    

</div>
</template>

<script>
//   import axios from "../../src/api/http_request";
import NavBar from '../components/AdminNavBar'
import Check from '../components/CheckPayment/Checkpay.vue'
export default {
    name: 'AddsOn',
     props: {
    mode: {
      type: String
    }
  },
    components: {
        NavBar,
        Check
    },
    data() {
        return {
            openDialog: false,
            modal: false,

        }
    },

    mounted() {}

}
</script>

<style scoped>
.my-content {
    background-color: rgba(300, 300, 300, 0.900);
    border-radius: 30px;
    padding: 30px;
}
</style>
