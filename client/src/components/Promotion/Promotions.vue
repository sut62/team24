<template>
<div>
  <Alert :open="success" topic="แจ้งเตือน" desc="บันทึกสำเร็จ" :callback="onAlertSumbit"/>
  <v-row justify="center">
    <v-dialog v-model="openDialog" persistent max-width="1000px">
      <template v-slot:activator="{ on }"></template>
      <v-card>
        <v-card-title>
          <span class="headline">เพิ่มโปรโมชั่นส่วนลด</span>
        </v-card-title>
        <div class="container">
          <div class="row">
            <div class="col-sm">
              <label for="formGroupExampleInput"></label>
              <input
                type="text"
                class="form-control"
                placeholder="ชื่อโปรโมชั่น"
                v-model="form.promotionName"
              />
              <br>
              <input type="text" class="form-control" placeholder="รหัส" v-model="form.promotionCode" />
              <br>
              <input
                type="text"
                class="form-control"
                placeholder="ส่วนลด (%)"
                v-model="form.discount"
              />
              <br>
              <input
                type="text"
                class="form-control"
                placeholder="หมดอายุ (ปี-เดือน-วัน เช่น 2020-12-30)"
                v-model="form.promotionExp"
              />
              <br>
              <v-textarea label="รายละเอียด" v-model="form.promotionDesc" auto-grow outlined rows="5" row-height="20"></v-textarea>
              
              <select class="browser-default custom-select" v-model = "form.getPromotionStatusId">
                <option disabled value="-1">สถานะ</option>
                <option
                  v-for="stat in promotionStatus"
                  :key="stat.id"
                  :value="stat.id"
                >{{stat.promotionStatus}}</option>
                </select>

              <br />
              <label >รูปภาพ</label>
                    <div>
                      <div class="d-flex flex-wrap">
                  <img @click="setImageId(image.id)" :class="{'my-img2':form.promotionImageId == image.id ,'my-img':form.promotionImageId != image.id ,}" v-for="(image,index) in promotionImages" :key="index" :src="image.url" alt="">
                    </div>
                    
                  </div>
               
              </div>
            </div>
          </div>
        

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>
          <v-btn color="blue darken-1" text @click="savePromotion">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</div>
</template>

<script>
import axios from "axios";
import Alert from '../Alert'
let http = axios.create({
  baseURL: "http://localhost:9000/api",
  timeout: 120000,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-type": "application/json"
  }
});

export default {
  name: "Baggage",
  data: () => ({
    success: false,
    promotionImages: [],
    promotionStatus: [],
    promotions: [],
    form: {
     promotionName: '',
      promotionDesc: '',
      discount: '',
      promotionCode: '',
      promotionImageId: 1,
      promotionStatusId: 1,
      promotionExp: ''
    }
  }),
  components:{
    Alert
  },
  props: {
    openDialog: {
      type: Boolean
    },
    closeDialog: {
      type: Function
    }
  },

  methods: {
    onAlertSumbit(){
      this.success = false;
    },
    setImageId(id) {
      this.form.promotionImageId = id;
      console.log(this.form);
    },
    setStatusId(id) {
      this.form.promotionStatusId = id;
      console.log(this.form);
    },
    savePromotion() {
      http({
          method:'post',
          url:'/promotion',
          data:{
            proName:this.form.promotionName,
            desc:this.form.promotionDesc,
            discount:this.form.discount,
            code:this.form.promotionCode,
            exp:this.form.promotionExp,
            img_id:this.form.promotionImageId,
            proStat:this.form.promotionStatusId
          }
        })
        .then(res => {
          console.log(res.data);
          this.success = true;
          // alert("เพิ่มโปรโมชั่นสำเร็จ ID="+res.data.id)
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPromotion() {
      http
        .get("http://localhost:9000/api/promotion/")
        .then(res => {
          this.promotions = res.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPromotionImage() {
      http
        .get("http://localhost:9000/api/promotion-image")
        .then(res => {
          this.promotionImages = res.data;
        });
    },
    getPromotionStatus() {
      http
        .get("http://localhost:9000/api/promotion-status")
        .then(res => {
          this.promotionStatus = res.data;
        });
    },
    
  },
  mounted() {
    this.getPromotion()
    this.getPromotionImage()
    this.getPromotionStatus()
  },
  watch: {
    
  }
};
</script>

<style scoped>
.my-img {
  border-style: solid;
  padding: 10px;
  width: 40%;
  margin-left: 5px;
  border-radius: 10px;
}
.my-img2 {
  border-style: solid;
  border-color: red;
  padding: 10px;
  width: 40%;
  margin-left: 5px;
  border-radius: 10px;
}
.my-img3 {
  border-style: solid;
  border-color: rgb(24, 166, 231);
  padding: 10px;
  width: 40%;
  margin-left: 5px;
  border-radius: 10px;
}
.my-img:hover {
  border-color: red;
  border-style: solid;
  padding: 10px;
  width: 40%;
  margin-left: 5px;
  border-radius: 20px;
}
</style>