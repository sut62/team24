<template>
  <div>
    <div v-if="checkin_page == true">
      <div class="text-black" style="width: auto;">
        <div class="topic">
          <h1 class="mt-5 topic">เช็คอิน</h1>
        </div>
      </div>
      <div class="text-black">ค้นหาบุ๊คกิ้งของท่านเพื่อเช็คอินหรือพิมพ์บอร์ดดิ้งพาสของท่านใหม่</div>
      <div class="card text-center card-style">
        <div class="card-body">
          <v-row>
            <v-col cols="3">
              <v-autocomplete v-model="select1" :items="items" outlined label="เมืองต้นทาง" filled></v-autocomplete>
            </v-col>
            <v-col cols="3">
              <v-autocomplete v-model="select2" :items="items" outlined label="เมืองปลายทาง" filled></v-autocomplete>
            </v-col>
            <v-col cols="3">
              <v-text-field v-model="lastname" :lastname="lastname" outlined label="นามสกุล" filled></v-text-field>
            </v-col>
            <v-col cols="3">
              <a
                href="#"
                @click="()=> onShowFligthPage(this)"
                class="btn btn-danger text-white btn-lg"
                style="width:85%"
              >ค้นหา</a>
            </v-col>
          </v-row>
        </div>
      </div>
      <hr />
      <div>
        <p class="text-size font-weight-medium">หมายเหตุ:</p>
        <ul class="text-size font-weight-regular" style="list-style-type:none">
          <li>1.มีข้อจำกัดในการเคลื่อนไหวร่างกายหรือต้องการความช่วยเหลือพิเศษ</li>
          <li>2.เดินทางพร้อมกับทารกอายุไม่เกิน 8 วัน</li>
          <li>3.มีอายุระหว่าง 12 - 16 ปีและเดินทางเพียงคนเดียว</li>
        </ul>
        <p class="text-size font-weight-regular">
          ผู้โดยสารเด็กที่มีอายุระหว่าง 12 ถึง 16 ปี ณ วันที่เดินทาง
          ต้องดำเนินการเช็คอินที่เคาน์เตอร์เช็คอินในสนามบิน
          โดยต้องมาพร้อมกับพ่อแม่หรือผู้ปกครอง
          พร้อมกับแสดงบัตรที่ออกโดยหน่วยงานราชการที่มีรูปถ่ายแนบ
          การอนุญาตเป็นไปตาม
          ข้อกำหนดผู้โดยสารเยาวชนที่เดินทางโดยลำพัง (YPTA)
        </p>
        <p
          class="text-size font-weight-regular"
        >สายการบินฯ ขอแนะนำให้ท่านยืนยันตัวตนกับเจ้าหน้าที่เช็คอินหรือเจ้าหน้าที่บริเวณประตูขึ้นเครื่อง หากท่านมีลักษณะดังต่อไปนี้:</p>
        <ul class="text-size font-weight-regular" style="list-style-type:none;">
          <li>1.ตั้งครรภ์</li>
          <li>2.มีอาการเจ็บป่วยทางร่างกาย</li>
        </ul>

        <p
          class="text-size font-weight-regular"
        >โดยทั่วไปแล้ว เว็บเช็คอินเปิดให้บริการ 14 วันล่วงหน้าก่อนออกเดินทาง และปิดให้บริการ 1 ชั่วโมงก่อนออกเดินทางสำหรับเที่ยวบินแอร์เอเชีย และปิดให้บริการ 4 ชั่วโมงก่อนออกเดินทางสำหรับเที่ยวบินแอร์เอเชียเอ็กซ์ตรวจสอบเวลาเช็คอิน</p>
      </div>
    </div>

    <div v-if="fligth_page == true">
      <div>
        <div class="step-style">
          <v-stepper :alt-labels="true">
            <v-stepper-header value="1">
              <v-stepper-step complete editable step="1">Flight</v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="2">adds-on</v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="3">Confirmation</v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="4">Safty notice</v-stepper-step>
            </v-stepper-header>
          </v-stepper>
        </div>
      </div>
      <div>
        <div class="mt-3 font-weight-medium topic-size">Flight</div>
        <div class="mt-0 font-weight-medium text-size">กรุณาเลือกผู้โดยสารเพื่อทำการเช็คอิน</div>
        <div class="card text-center card-description">
          <div class="card-header text-left">รายละเอียด</div>
          <div class="card-flight card-header text-center">
            <v-row>
              <v-col cols="5">กรุงเทพ</v-col>
              <v-col cols="7">เชียงใหม่</v-col>
            </v-row>
            <v-row class="mt-0">
              <v-col cols="5">14 กย 62</v-col>
              <v-icon x-large color="orange darken-2">mdi-airplane</v-icon>
              <v-col cols="6">14 กย 62</v-col>
            </v-row>
            <v-row class="mt-0">
              <v-col cols="5">23:00 น</v-col>
              <v-col cols="7">24:00 น</v-col>
            </v-row>
          </div>
          <div class="card-body text-left">นาย เปี่ยมพูล พูลเปี่ยม</div>
        </div>
      </div>
      <div class="card text-center card-button">
        <a
          href="#"
          @click="()=>onShowAddOnPage(this)"
          class="btn btn-danger text-white btn-lg"
          style="width:20% margin-left: 130px;"
        >Next</a>
      </div>
    </div>

    <div v-if="addon_page == true">
      <div>
        <div class="step-style">
          <v-stepper :alt-labels="true">
            <v-stepper-header value="1">
              <v-stepper-step complete step="1">Flight</v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step complete editable step="2">adds-on</v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="3">Confirmation</v-stepper-step>

              <v-divider></v-divider>

              <v-stepper-step step="4">Safty notice</v-stepper-step>
            </v-stepper-header>
          </v-stepper>
        </div>

        <div class="mt-3 font-weight-medium topic-size">Adds-On</div>
      </div>
      <div>
        <v-card class="mx-auto card-addson" width="100%" height="210px" outlined>
          <v-list-item three>
            <v-list-item-content>
              <v-list-item-title class="headline mb-4">สัมภาระเช็คอิน</v-list-item-title>
              <div class="mt-0">สัมภาระพกพา 7 กก รวมอยู่ในค่าโดยสารแล้ว</div>
              <hr />
              <v-row class="font-weight-medium">
                <v-col>
                  <v-list-item>
                    <v-icon large color="orange darken-2">mdi-airplane-takeoff</v-icon>
                    <div class="ml-5">DMK-URT</div>
                  </v-list-item>
                </v-col>
                <v-col>
                  <v-list-item>
                    <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                    <div class="ml-5">URT-DMK</div>
                  </v-list-item>
                </v-col>
              </v-row>
            </v-list-item-content>

            <v-list-item-avatar tile size="200" color="#e0f2f1">
              <img src="../../assets/baggage.png" alt="baggage" />
            </v-list-item-avatar>
          </v-list-item>
        </v-card>
        <v-card class="mx-auto card-addson" width="100%" height="250px" outlined>
          <v-list-item three>
            <v-list-item-content>
              <v-list-item-title class="headline mb-4">Seat</v-list-item-title>
              <div class="mt-0"> 
                <v-icon large color="orange darken-2">mdi-dot-single</v-icon>
                กำหนดอัตโนมัติ
                </div>
              <hr />
              <v-row class="font-weight-medium">
                <v-list-item>
                  <v-icon large color="orange darken-2">mdi-airplane-takeoff</v-icon>
                  <v-row class="ml-6 mt-.5">
                    <v-list-item-content>
                      <v-list-item-title class="font-weight-medium">DMK-URT</v-list-item-title>
                      <v-list-item-subtitle>23D,23E</v-list-item-subtitle>
                    </v-list-item-content>
                  </v-row>
                </v-list-item>
              </v-row>
              <hr />
              <v-row class="font-weight-medium">
                <v-list-item>
                  <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                  <v-row class="ml-6 mt-.5">
                    <v-list-item-content>
                      <v-list-item-title class="font-weight-medium">DMK-URT</v-list-item-title>
                      <v-list-item-subtitle>23D,23E</v-list-item-subtitle>
                    </v-list-item-content>
                  </v-row>
                </v-list-item>
              </v-row>
            </v-list-item-content>

            <v-list-item-avatar tile size="200" color="#e0f2f1">
              <img src="../../assets/seat.png" alt="seat" />
            </v-list-item-avatar>
          </v-list-item>
        </v-card>
      </div>
      <div class="card text-center card-button">
        <a
          href="#"
          @click="()=>onShowAddOnPage(this)"
          class="btn btn-danger text-white btn-lg"
          style="width:20% margin-left: 130px;"
        >Next</a>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "search",
      lastname: "",
      select1: "",
      select2: "",
      items: ["กรุงเทพ", "ขอนแก่น", "ภูเก็ต", "เลย"],

      checkin_page: true,
      fligth_page: false,
      addon_page: false,

      extensionHeight: 100,
      reverse: true
    };
  },

  updated() {},

  methods: {
    onShowCheckInPage(main) {
      main.checkin_page = !main.checkin_page;
    },

    onShowFligthPage(main) {
      main.checkin_page = !main.checkin_page;
      main.fligth_page = !main.fligth_page;
    },
    onShowAddOnPage(main) {
      main.addon_page = !main.addon_page;
      main.fligth_page = !main.fligth_page;
    }
  }
};
</script>

<style scoped>
.text-size {
  font-size: 13px;
}
.topic-size {
  font-size: 20px;
}
.card-style {
  border-radius: 8px;
  height: 130px;
  margin-top: 20px;
  margin-bottom: 20px;
}
.step-style {
  margin-top: 0;
  border: none;
  box-shadow: none;
}
.card-description {
  height: 300px;
  width: 100%;
  margin-top: 10px;
  margin-bottom: 10px;
}
.card-flight {
  height: 150px;
  width: 70%;
  margin-top: 20px;
  margin-left: 130px;
  position: static;
}
.card-button {
  width: 100%;
  height: 50px;
  margin-top: 10px;
  margin-bottom: 10px;
}
.card-addson {
  margin-top: 10px;
  margin-bottom: 10px;
  background-color: #e0f2f1;
}
</style>