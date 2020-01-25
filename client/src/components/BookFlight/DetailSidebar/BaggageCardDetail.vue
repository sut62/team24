<template>
  <div class="mt-3">
        <h3>สัมภาระเช็คอิน</h3>
        <div class="card p-4 ">
            <v-row>
                <img class="ml-5" :src="baggage.baggageImage.url" width="80px" alt="">
                <v-col>
                    <div><span class="ml-5 badge badge-info">น้ำหนักสูงสุด </span> {{baggage.maxWeight}} กก.</div>
                    <div><span class="ml-5 badge badge-info">ชนิดกระเป๋า </span> {{baggage.baggageType.btypename}}</div>
                </v-col>
            </v-row>
            <div class="mt-2 badge badge-success">ราคา {{baggage.price}} บาท </div> 
        </div>
        
  </div>
</template>

<script>
export default {
    props:{
        baggage:{
            type: Object
        }
    }
}
</script>

<style>

</style>