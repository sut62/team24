<template>
<div>
    <div class="header-bg"></div>
    <div class="content">
        <UserNavbar />
        <div class="container" style="margin-top:100px; margin-bottom: 300px">
            <div class="mx-auto">
                <div class="text-white">
                    <h1 class="mt-5">ตรวจสอบการจองตั๋วเครื่องบิน</h1><br><br>
                </div>
                <Search :mode="mode"/>
            </div>
        </div>
        <UserFooter />
    </div>
</div>
</template>

<script>
// @ is an alias to /src
import Search from '../components/CheckTicket/CheckTicket..vue'
import UserNavbar from '../components/UserNavbar'
import UserFooter from '../components/UserFooter'
export default {
    name: 'CheckTicket',
    props: {
        mode: {
            type: String
        }
    },
    data: () => ({

    }),
    components: {
        Search,
        UserNavbar,
        UserFooter
    },
}
</script>

<style scoped>
.header-bg {
    height: 300px;
    width: 100%;
    position: absolute;
    top: 1px;
    background-size: cover;
    /* background-color: rgb(8, 2, 43); */
    background-image: url("../assets/head-bg.jpg");
}

.content {
    z-index: 1;
    position: relative;
}
</style>
