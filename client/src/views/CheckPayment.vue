<template>
<div>
    <NavBar />
    <div class=" mt-10 mx-auto container">
        <div class="row">
            <div class="col-6">
                <h1>ตรวจสอบการชำระเงิน</h1>
            </div>
            <div class="col-3">
            </div>
            <div class="col-3">
                <div @click="openDialog = true" class="  btn-primary btn">
                    <v-icon>mdi-magnify</v-icon>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                1 of 3
            </div>
            <div class="col">
            </div>
            <div class="col">
                3 of 3
            </div>
        </div>
    </div>

    <Check :openDialog="openDialog" :closeDialog="closeDialog" />

</div>
</template>

<script>
//   import axios from "../../src/api/http_request";
import NavBar from '../components/AdminNavBar'
import Check from '../components/CheckPayment/Checkpay.vue'
export default {
    name: 'AddsOn',
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

    methods: {
        closeDialog() {
            this.openDialog = false
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
