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
              <v-divider></v-divider>
              <v-stepper-step step="5">Print</v-stepper-step>
            </v-stepper-header>
          </v-stepper>
        </div>
      </div>
      <div>
        <div class="mt-3 font-weight-medium topic-size">Flight</div>
        <div class="mt-0 font-weight-medium text-size">กรุณาเลือกผู้โดยสารเพื่อทำการเช็คอิน</div>
        <div class="card text-center card-description">
          <div class="card-header text-left">รายละเอียด</div>
          <div class="card card-flight card-header text-center">
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
              <v-divider></v-divider>
              <v-stepper-step step="5">Print</v-stepper-step>
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
                <v-icon large color="orange darken-2">mdi-dot-single</v-icon>กำหนดอัตโนมัติ
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
          @click="()=>onShowConfirmationPage(this)"
          class="btn btn-danger text-white btn-lg"
          style="width:20% margin-left: 130px;"
        >Next</a>
      </div>
    </div>

    <div v-if="confirmation_page == true">
      <div class="step-style">
        <v-stepper :alt-labels="true">
          <v-stepper-header value="1">
            <v-stepper-step complete step="1">Flight</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete step="2">Adds-on</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete editable step="3">Confimation</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step step="4">Safty notice</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step step="5">Print</v-stepper-step>
          </v-stepper-header>
        </v-stepper>
      </div>

      <div>
        <div class="mt-3 font-weight-medium topic-size">Confimation</div>
        <div class="text-black">กรุณาเลือก 'continue' เพื่อทำการเช็คอิน</div>
      </div>
      <div class="card text-leftfont-weight-medium card-confimation">
        <div class="card-header text-left">Gusest(s) are now ready to check-in</div>
        <v-list-item three-line>
          <v-list-item-content>
            <v-list-item-title class="mb-1 mr-1">
              <v-icon large color="black">mdi-check-bold</v-icon>นายเปี่ยมพูล พูลเปี่ยม
            </v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </div>
      <div class="card text-leftfont-weight-medium card-confimation">
        <div class="card-header text-left">พิมพ์บอร์ดดิ้งพาสเอง</div>
        <div class="text-left ml-4">คุณสามารถบันทึกเพื่อสั่งพิมพ์ภายหลังได้</div>
        <v-list-item three-line>
          <v-list-item-content>
            <v-list-item-title class="mb-1">
              <v-icon large color="black">mdi-email-newsletter</v-icon>รับบัตรขึ้นเครื่องบอร์ดดิ้งพาสทาง E-mail
            </v-list-item-title>
            <v-text-field outlined label="E-mail" filled style="width: 50%;"></v-text-field>
          </v-list-item-content>
        </v-list-item>
      </div>
      <div class="card text-center card-button">
        <a
          href="#"
          @click="()=>onShowSaftyPage(this)"
          class="btn btn-danger text-white btn-lg"
          style="width:20% margin-left: 130px;"
        >Next</a>
      </div>
    </div>

    <div v-if="safty_page == true">
      <div class="step-style">
        <v-stepper :alt-labels="true">
          <v-stepper-header value="1">
            <v-stepper-step complete step="1">Flight</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete step="2">Adds-on</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete step="3">Confimation</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete editable step="4">Safty notice</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step step="5">Print</v-stepper-step>
          </v-stepper-header>
        </v-stepper>
      </div>

      <div>
        <v-card class="mx-auto mt-5" max-width="100%" height="600" outlined color="#ECEFF1">
          <v-list-item three-line>
            <v-list-item-content class="text-black">
              <v-list-item-title class="headline mb-1">สินค้าอันตราย</v-list-item-title>
              <v-list-item-subtitle>สินค้าอันตรายเป็นรายการหรือสารที่อาจเป็นอันตรายต่อความปลอดภัยของเครื่องบินหรือแขกบนเครื่องบิน โปรดตรวจสอบให้แน่ใจว่าคุณไม่ได้บรรจุสิ่งของที่ระบุไว้ในรายการนี้ในสัมภาระในห้องโดยสารหรือสัมภาระที่ลงทะเบียน</v-list-item-subtitle>
              <v-list-item-title class="headline mb-1">รายการที่ต้องห้าม</v-list-item-title>
              <v-container class="text-center break-word">
                <v-row>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-corrosive_7.png" />
                    <div>กัดกร่อน</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-gas_7.png" />
                    <div>ก๊าซ</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-flammable-liquid-_131.png" />
                    <div>ของเหลวไวไฟ</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-oxdysing-material_7.png" />
                    <div>วัสดุออกซิไดซ์</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-organic-paroxides_7.png" />
                    <div>สารอินทรีย์</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-toxic_7.png" />
                    <div>สารพิษ</div>
                  </v-img>
                </v-row>
                <v-row class="mt-10">
                  <v-img>
                    <img src="../../assets/safty/dg-icon-radioactive_7.png" />
                    <div>กัมมันตรังสี</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-infection-subtances_7.png" />
                    <div>สารติดเชื้อ</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-explosive_7.png" />
                    <div>วัตถุระเบิด</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-dry-ice_7.png" />
                    <div>น้ำมันเบนซิน</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-flammable-solids_7.png" />
                    <div>กัดกร่อน</div>
                  </v-img>
                  <v-img>
                    <img src="../../assets/safty/dg-icon-magnetic-material_7.png" />
                    <div>วัสดุแม่เหล็ก</div>
                  </v-img>
                </v-row>
              </v-container>
              <br />
              <p>สิ่งของต่อไปนี้เป็นสิ่งต้องห้ามในสัมภาระของคุณไม่ว่าจะมีการเช็คอินหรือไม่:</p>
              <pre>
ต้องห้าม	อาวุธปืนและกระสุน
ต้องห้าม	วัตถุระเบิด, ก๊าซไวไฟหรือไม่ติดไฟ (เช่นสีสเปรย์, ก๊าซบิวเทน, เติมเบา)
ต้องห้าม	ก๊าซแช่เย็น (เช่นถังบรรจุ aqualung, ไนโตรเจนเหลว)
ต้องห้าม	ของเหลวไวไฟ (เช่นสี, ทินเนอร์, ตัวทำละลาย)
ต้องห้าม	ของแข็งไวไฟ (เช่นไม้ขีดไฟ, ไฟแช็ค)
ต้องห้าม	อินทรีย์เปอร์ออกไซด์ (เช่นเรซิน)
ต้องห้าม	สารพิษ
ต้องห้าม	สารติดเชื้อ (เช่นไวรัสแบคทีเรีย)
ต้องห้าม	วัสดุกัมมันตรังสี (เช่นเรเดียม)
ต้องห้าม	วัสดุที่มีฤทธิ์กัดกร่อน (เช่นกรด, ด่าง, ปรอท, เครื่องวัดอุณหภูมิ)
ต้องห้าม	สารแม่เหล็ก, วัสดุออกซิไดซ์ (เช่นสารฟอกขาว)
ต้องห้าม	อาวุธเช่นอาวุธปืนโบราณดาบมีดและสิ่งของที่คล้ายกันโดยมีเงื่อนไขว่าสิ่งของดังกล่าวอาจได้รับอนุญาตให้นำติดตัวขึ้นเครื่องได้ตามดุลยพินิจของเราด้วยเหตุผลพิเศษ
            </pre>
            </v-list-item-content>
          </v-list-item>
        </v-card>
        <hr />
        <v-checkbox
          v-model="agree"
          label="ข้าพเจ้าได้ศึกษาและยอมรับในข้อกำหนดเกี่ยวกับวัตถุต้องห้าม และขอยืนยันว่าไม่มีวัตถุอัตรายใด ๆ บรรจุในสัมภาระของข้าพเจ้า"
          color="green"
        ></v-checkbox>
      </div>
      <div class="card text-center card-button" >
        <a
          href="#"
          @click="()=>onShowSaftyPage(this)"
          class="btn btn-danger text-white btn-lg"
          style="width:10px margin-left: 10px;"
        >ยืนยัน</a>
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
      agree: "Yes",

      checkin_page: true,
      fligth_page: false,
      addon_page: false,
      confirmation_page: false,
      safty_page: false,

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
    },
    onShowConfirmationPage(main) {
      main.addon_page = !main.addon_page;
      main.confirmation_page = !main.confirmation_page;
    },
    onShowSaftyPage(main) {
      main.confirmation_page = !main.confirmation_page;
      main.safty_page = !main.safty_page;
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
.card-confimation {
  height: 200px;
  width: 100%;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>