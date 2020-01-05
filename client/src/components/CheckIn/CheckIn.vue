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
              <v-autocomplete
                v-model="checkIn.depart"
                :items="flightCity"
                item-text="name"
                item-value="id"
                outlined
                label="เมืองต้นทาง"
                filled
              ></v-autocomplete>
            </v-col>
            <v-col cols="3">
              <v-autocomplete
                v-model="checkIn.arrive"
                :items="flightCity"
                item-text="name"
                item-value="id"
                outlined
                label="เมืองปลายทาง"
                filled
              ></v-autocomplete>
            </v-col>
            <v-col cols="3">
              <v-text-field v-model="checkIn.lastname" outlined label="นามสกุล" filled></v-text-field>
            </v-col>
            <v-col cols="3">
              <a
                href="#"
                @click="()=> findForCheckIn(this) "
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
        <div class="mt-0 font-weight-medium text-size">รายละเอียดเที่ยวบิน</div>
        <div class="card text-center card-description">
          <div class="card-header text-left">รายละเอียด (ขาไป)</div>
          <div class="card card-flight card-header text-center">
            <v-row>
              <v-col cols="5">{{flightAirports_go_depart.name}}</v-col>
              <v-col cols="7">{{flightAirports_go_arrive.name}}</v-col>
            </v-row>
            <v-row class="mt-0">
              <v-col cols="5">{{flightBookingLinks_go.depart}}</v-col>
              <v-icon x-large color="orange darken-2">mdi-airplane</v-icon>
              <v-col cols="5">{{flightBookingLinks_go.arrive}}</v-col>
            </v-row>
          </div>
          <div class="card-body text-left">{{user.firstName}} {{user.lastName}}</div>
        </div>
        <br />
        <div
          class="card text-center card-description"
          v-show="flightBookingLinks_back != '' "
        >
          <div class="card-header text-left">รายละเอียด (ขากลับ)</div>
          <div class="card card-flight card-header text-center">
            <v-row>
              <v-col cols="5">{{flightAirports_back_depart.name}}</v-col>
              <v-col cols="7">{{flightAirports_back_arrive.name}}</v-col>
            </v-row>
            <v-row class="mt-0">
              <v-col cols="5">{{flightBookingLinks_back.depart}}</v-col>
              <v-icon x-large color="orange darken-2">mdi-airplane</v-icon>
              <v-col cols="5">{{flightBookingLinks_back.arrive}}</v-col>
            </v-row>
          </div>
          <div class="card-body text-left">{{user.firstName}} {{user.lastName}}</div>
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
                    <div class="ml-5">{{arrive_airport_go}}-{{depart_airport_go}}</div>
                  </v-list-item>
                </v-col>
                <v-col>
                  <v-list-item>
                    <v-icon large color="orange darken-2">mdi-airplane-landing</v-icon>
                    <div class="ml-5">{{depart_airport_back}}-{{arrive_airport_back}}</div>
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
                      <v-list-item-title
                        class="font-weight-medium"
                      >{{arrive_airport_go}}-{{depart_airport_go}}</v-list-item-title>
                      <v-list-item-subtitle>{{flight.departSeatId}}</v-list-item-subtitle>
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
                      <v-list-item-title
                        class="font-weight-medium"
                      >{{depart_airport_back}}-{{arrive_airport_back}}</v-list-item-title>
                      <v-list-item-subtitle>{{flight.returnSeatId}}</v-list-item-subtitle>
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
              <v-icon large color="black">mdi-check-bold</v-icon>
              {{user.firstName}} {{user.lastName}}
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
            <v-text-field
              v-model="checkIn.email"
              :rules="emailRules"
              outlined
              label="E-mail"
              required
              filled
              style="width: 50%;"
            ></v-text-field>
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
          v-model="checkIn.checkbox"
          label="ข้าพเจ้าได้ศึกษาและยอมรับในข้อกำหนดเกี่ยวกับวัตถุต้องห้าม และขอยืนยันว่าไม่มีวัตถุอัตรายใด ๆ บรรจุในสัมภาระของข้าพเจ้า"
          color="green"
          true-value="yes"
          alse-value="no"
        ></v-checkbox>
      </div>
      <div class="card text-center card-button" v-show="checkIn.checkbox == true">
        <a
          href="#"
          @click="()=>saveCreateCheckIn(this)"
          class="btn btn-danger text-white btn-lg"
          style="width:10px margin-left: 10px;"
        >ยืนยัน</a>
      </div>
    </div>

    <div v-show="boardingPass == true">
      <div class="step-style">
        <v-stepper :alt-labels="true">
          <v-stepper-header value="1">
            <v-stepper-step complete step="1">Flight</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete step="2">Adds-on</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete step="3">Confimation</v-stepper-step>

            <v-divider></v-divider>

            <v-stepper-step complete step="4">Safty notice</v-stepper-step>
            <v-divider></v-divider>
            <v-stepper-step complete editable step="5">Print</v-stepper-step>
          </v-stepper-header>
        </v-stepper>
      </div>
      <br />
      <div class="text-right" >
       <v-btn  @click="()=>backToFirstPage(this)" class="ma-2 " style="text-align:center" color="primary" dark>Print
        <v-icon dark right>mdi-printer</v-icon>
      </v-btn>
      <v-btn @click="()=>backToFirstPage(this)" class="ma-2 " style="text-align:center" color="red" dark="">close
        <v-icon dark right>mdi-close</v-icon>
      </v-btn>
      </div>
      
      <div class="card text-center card-bordingpass-style">
        <div class="card-body">
          <v-row class="pt-5 pr-5">
            <v-col>
              <v-avatar class="logo" size="100">
                <img src="../../assets/logo.png" alt="John" />
                <br />
              </v-avatar>
              <div align="left" class="caption">Depart</div>
              <p align="left" class="title">{{flightAirports_go_depart.name}}</p>
              <div align="left" class="caption">Arrive</div>
              <p align="left" class="title">{{flightAirports_go_arrive.name}}</p>
              <div align="left" class="caption">booking no.</div>
              <p align="left" class="title">{{flight.bookId}}</p>
              <!-- <div align="left" class="caption">Seq no.</div>
              <p align="left" class="title">66</p>-->
            </v-col>
            <v-col>
              <h1>Bording Pass</h1>
              <div class="myborder">
                <p class="caption">
                  Flight
                  <sub class="title">{{flightBookingType_go}}</sub>
                </p>
                <p class="caption">
                  Date Depart
                  <sub class="overline">{{flightBookingLinks_go.depart}}</sub>
                </p>
                <p class="caption">
                  Date Arrive
                  <sub class="overline">{{flightBookingLinks_go.arrive}}</sub>
                </p>
                <p class="caption">
                  Seat no.
                  <sub class="title">{{flight.departSeatId}}</sub>
                </p>
                <p></p>
              </div>
              <div>
                <img id="barcode" />
              </div>
            </v-col>
          </v-row>
          <hr />
          <img src="../../assets/checkInLast.png" alt />
        </div>
      </div>
      <br />
      <div v-show="flightBookingLinks_back != '' "  class="card text-center card-bordingpass-style">
        <div class="card-body">
          <v-row class="pt-5 pr-5">
            <v-col>
              <v-avatar class="logo" size="100">
                <img src="../../assets/logo.png" alt="John" />
                <br />
              </v-avatar>
              <div align="left" class="caption">Depart</div>
              <p align="left" class="title">{{flightAirports_back_depart.name}}</p>
              <div align="left" class="caption">Arrive</div>
              <p align="left" class="title">{{flightAirports_back_arrive.name}}</p>
              <div align="left" class="caption">booking no.</div>
              <p align="left" class="title">{{flight.bookId}}</p>
              <!-- <div align="left" class="caption">Seq no.</div>
              <p align="left" class="title">66</p>-->
            </v-col>
            <v-col>
              <h1>Bording Pass</h1>
              <div class="myborder">
                <p class="caption">
                  Flight
                  <sub class="title">{{flightBookingType_back}}</sub>
                </p>
                <p class="caption">
                  Date Depart
                  <sub class="overline">{{flightBookingLinks_back.depart}}</sub>
                </p>
                <p class="caption">
                  Date Arrive
                  <sub class="overline">{{flightBookingLinks_back.arrive}}</sub>
                </p>
                <p class="caption">
                  Seat no.
                  <sub class="title">{{flight.arriveSeatId}}</sub>
                </p>
                <p></p>
              </div>
              <div>
                <img id="barcode" />
              </div>
            </v-col>
          </v-row>
          <hr />
          <img src="../../assets/checkInLast.png" alt />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
var JsBarcode = require("jsbarcode");

let axiosInstance = axios.create({
  baseURL: "http://localhost:9000/api",
  timeout: 120000,
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Content-type": "application/json"
  }
});

export default {
  data() {
    return {
      isBarcodeVisible: true,
      flightCity: [],
      checkInStatus: [],
      checkInType: [],
      flight: [],
      user: [],
      flightBookingId: [],
      flightBookingLinks_go: [],
      flightBookingLinks_back: [],
      flightBookingType_go: [],
      flightBookingType_back: [],
      flightAirports_go_depart: [],
      flightAirports_go_arrive: [],
      flightAirports_back_depart: [],
      flightAirports_back_arrive: [],
      city_go: [],
      city_back: [],
      arrive_airport_go: [],
      depart_airport_go: [],
      arrive_airport_back: [],
      depart_airport_back: [],
      emailRules: [
        v => !!v || "E-mail is required",
        v => /.+@.+\..+/.test(v) || "E-mail must be valid"
      ],
      checkIn: {
        arrive: "",
        depart: "",
        lastname: "",
        email: "xxxx@mail.com",
        flight: [],
        checkbox: "",
        flightBookingId: this.flightBookingId,
        checkInStatus: this.checkInStatus,
        checkInType: this.checkInType
      },

      checkin_page: true,
      fligth_page: false,
      addon_page: false,
      confirmation_page: false,
      safty_page: false,
      boardingPass: false,

      extensionHeight: 100,
      reverse: true
    };
  },

  // updated(){
  //   console(checkIn);
  // },

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
    },
    onShowBoardingPass(main) {
      main.safty_page = !main.safty_page;
      main.boardingPass = !main.boardingPass;
    },
    backToFirstPage(){
      location.reload()
    },
    getFlightCity() {
      axiosInstance
        .get("/city")
        .then(response => {
          this.flightCity = response.data;
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },

    getCheckInStatus() {
      axiosInstance
        .get("/checkInStatus")
        .then(response => {
          this.checkInStatus = response.data[0].id;
          this.checkIn.checkInStatus = response.data[0].id;
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },

    getCheckInType() {
      axiosInstance
        .get("/checkInType")
        .then(response => {
          this.checkInType = response.data[0].id;
          this.checkIn.checkInType = response.data[0].id;
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },
    getCheckIn() {
      axiosInstance
        .get("/checkin")
        .then(response => {
          console.log(response.data);
          this.$nextTick();
        })
        .catch(e => {
          console.log(e);
        });
    },

    findForCheckIn() {
      axiosInstance
        .get(
          "/flight-booking/checkin/" +
            this.checkIn.lastname +
            "/" +
            this.checkIn.depart +
            "/" +
            this.checkIn.arrive
        )
        .then(response => {
          if (response.data != null) {
            this.flight = response.data;
            this.user = response.data.user;
            this.flightBookingId = response.data.id;
            this.flightBookingLinks_go =
              response.data.flightBookingLinks[0].flight;
            this.flightBookingLinks_back =
              response.data.flightBookingLinks[1].flight;

            this.flightBookingType_go =
              response.data.flightBookingLinks[0].flightBookingType.name;
            this.flightBookingType_back =
              response.data.flightBookingLinks[1].flightBookingType.name;

            this.flightAirports_go_depart =
              response.data.flightBookingLinks[0].flight.flightAirports[0].airport;
            this.flightAirports_go_arrive =
              response.data.flightBookingLinks[0].flight.flightAirports[1].airport;

            this.flightAirports_back_depart =
              response.data.flightBookingLinks[1].flight.flightAirports[0].airport;
            this.flightAirports_back_arrive =
              response.data.flightBookingLinks[1].flight.flightAirports[1].airport;

            this.city_go =
              response.data.flightBookingLinks[0].flight.flightAirports[0].airport.city.name;
            this.city_back =
              response.data.flightBookingLinks[1].flight.flightAirports[1].airport.city.name;

            this.arrive_airport_go =
              response.data.flightBookingLinks[0].flight.flightAirports[0].airport.name;
            this.depart_airport_go =
              response.data.flightBookingLinks[0].flight.flightAirports[1].airport.name;

            this.arrive_airport_back =
              response.data.flightBookingLinks[1].flight.flightAirports[0].airport.name;
            this.depart_airport_back =
              response.data.flightBookingLinks[1].flight.flightAirports[1].airport.name;

            this.checkIn.flightBookingId = response.data.id;
            JsBarcode("#barcode", this.flight.bookId);
            this.onShowFligthPage(this);

            console.log(this.flightBookingType_go);
          } else {
            this.clear();
          }
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },

    saveCreateCheckIn() {
      axiosInstance({
        method: "post",
        url: "/checkin/create",
        data: {
          email: this.checkIn.email,
          checkInStatusId: this.checkIn.checkInStatus,
          checkInTypeId: this.checkIn.checkInType,
          flightBookingId: this.checkIn.flightBookingId
        }
      })
        .then(response => {
          alert("เช็คอินสำเร็จ", response);
          this.onShowBoardingPass(this);
        })
        .catch(e => {
          alert("เกิดข้อผิดพลาด " + e);
          this.forceRefresh();
        });
    }
  },
  mounted() {
    this.getFlightCity();
    this.getCheckInStatus();
    this.getCheckInType();
  }
};
</script>

<style scoped>
.myborder {
  border-style: solid;
  border-width: 3px;
  padding: 20px;
}
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
.card-bordingpass-style {
  border-radius: 1px;
  width: 100%;
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
.barcode-style {
  height: 100px;
  width: 150px;
}
.logo {
  position: unset;
  margin-right: 100px;
  margin-bottom: 30px;
  top: 30px;
  left: 10%;
}
</style>