<template>
  <div>
    <div class="text-black">ค้นหาบุ๊คกิ้งของท่านเพื่อเช็คอินหรือพิมพ์บอร์ดดิ้งพาสของท่านใหม่</div>
    <div class="card text-center">
      <div class="card-body">
        <v-row>
          <v-col cols="3">
            <v-autocomplete 
            v-model="select1" 
            :items="items" 
            outlined 
            label="เมืองต้นทาง" 
            filled>
            </v-autocomplete>
          </v-col>
          <v-col cols="3">
            <v-autocomplete 
            v-model="select2" 
             :items="items" 
             outlined 
             label="เมืองปลายทาง" 
             filled>
             </v-autocomplete>
          </v-col>
           <v-col cols="3">
            <v-text-field  
              v-model="lastname"
              :lastname="lastname"
              outlined
              label="นามสกุล"
              filled>
            </v-text-field>
          </v-col>
          <v-col cols="3">
            <a href="#" class="btn btn-danger text-white btn-lg" style="width:85%">ค้นหา</a>
          </v-col>
        </v-row>
      </div>
    </div>
    <hr>
    <div>
      <p class="text-size font-weight-medium">หมายเหตุ:</p>
        <ul class="text-size font-weight-regular" style="list-style-type:none">
          <li>1.มีข้อจำกัดในการเคลื่อนไหวร่างกายหรือต้องการความช่วยเหลือพิเศษ</li>
          <li>2.เดินทางพร้อมกับทารกอายุไม่เกิน 8 วัน</li>
          <li>3.มีอายุระหว่าง 12 - 16 ปีและเดินทางเพียงคนเดียว</li>
      </ul>
      <p class="text-size font-weight-regular" > ผู้โดยสารเด็กที่มีอายุระหว่าง 12 ถึง 16 ปี ณ วันที่เดินทาง 
          ต้องดำเนินการเช็คอินที่เคาน์เตอร์เช็คอินในสนามบิน 
          โดยต้องมาพร้อมกับพ่อแม่หรือผู้ปกครอง 
          พร้อมกับแสดงบัตรที่ออกโดยหน่วยงานราชการที่มีรูปถ่ายแนบ 
          การอนุญาตเป็นไปตาม 
          ข้อกำหนดผู้โดยสารเยาวชนที่เดินทางโดยลำพัง (YPTA) </p>
      <p class="text-size font-weight-regular">สายการบินฯ ขอแนะนำให้ท่านยืนยันตัวตนกับเจ้าหน้าที่เช็คอินหรือเจ้าหน้าที่บริเวณประตูขึ้นเครื่อง หากท่านมีลักษณะดังต่อไปนี้:</p>
        <ul class="text-size font-weight-regular" style="list-style-type:none;">
          <li>1.ตั้งครรภ์</li>
          <li>2.มีอาการเจ็บป่วยทางร่างกาย</li>
        </ul>

      <p class="text-size font-weight-regular">
        โดยทั่วไปแล้ว เว็บเช็คอินเปิดให้บริการ 14 วันล่วงหน้าก่อนออกเดินทาง และปิดให้บริการ 1 ชั่วโมงก่อนออกเดินทางสำหรับเที่ยวบินแอร์เอเชีย และปิดให้บริการ 4 ชั่วโมงก่อนออกเดินทางสำหรับเที่ยวบินแอร์เอเชียเอ็กซ์ตรวจสอบเวลาเช็คอิน
      </p>
    </div>
  </div>
  
</template>

<script>
export default {
  name: "search",

  data: () => ({
    showDatePick: false,
    lastname: "",
    select1: "",
    select2: "",
    items: ["กรุงเทพ", "ขอนแก่น", "ภูเก็ต", "เลย"]
  }),

};
</script>

<style scoped>
.date-picker {
  position: absolute;
  left: 20px;
  top: 70px;
  width: 500px;
}
.text-size{
  font-size: 13px;
}
</style>