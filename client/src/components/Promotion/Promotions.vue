<template>
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
                v-model="form.maxWeight"
              />
              <br />
              <label for="formGroupExampleInput"></label>
              <input type="text" class="form-control" placeholder="รหัส" v-model="form.maxWeight" />
              <br />
              <label for="formGroupExampleInput"></label>
              <input
                type="text"
                class="form-control"
                placeholder="ส่วนลด (%)"
                v-model="form.maxWeight"
              />
              <br />
              <label for="formGroupExampleInput"></label>
              <v-textarea label="รายละเอียด" auto-grow outlined rows="5" row-height="20"></v-textarea>
              <br />
            </div>
            <div class="col-sm mt-6">
              <label></label>
              <select
                @change="getBaggage"
                class="browser-default custom-select mb-2"
                v-model="filterAirportId"
                placeholder="ชื่อโปรโมชั่น"
              >
                <option disabled value="-1">เลือก</option>
                <option v-for="a in name" :key="a.id" :value="a.id">{{a.name}}</option>
              </select>
              <div v-for="(baggage,index) in baggages" :key="index">
                <v-card>
                  <div class="d-flex justify-content-between p-3 mb-2">
                    <img class="my-img3" :src="baggage.baggageImage.url" alt />
                    <div>
                      <div>{{baggage.baggageType.btypename}}</div>
                      <div>น้อยกว่า {{baggage.maxWeight}}</div>
                    </div>
                    <div>ราคา {{baggage.price}} บาท</div>
                  </div>
                </v-card>
              </div>
            </div>
          </div>
        </div>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>
          <v-btn color="blue darken-1" text @click="saveBaggage">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";

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
    baggages: [],
    btypename: [],
    images: [],
    name: [],
    filterAirportId: 1,
    form: {
      airportId: -1,
      baggageTypeId: -1,
      maxWeight: "",
      price: "",
      imageId: -1
    }
  }),
  props: {
    openDialog: {
      type: Boolean
    },
    closeDialog: {
      type: Function
    }
  },

  methods: {
    setImageId(id) {
      this.form.imageId = id;
      console.log(this.form);
    },
    saveBaggage() {
      http
        .post(
          "http://localhost:9000/api/adds-on/" +
            this.form.maxWeight +
            "/" +
            this.form.price +
            "/" +
            this.form.imageId +
            "/" +
            this.form.baggageTypeId +
            "/" +
            this.form.airportId
        )
        .then(res => {
          console.log(res.data);
          this.getBaggage();
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBaggagetype() {
      http
        .get("http://localhost:9000/api/baggage-type/")
        .then(bag => {
          this.btypename = bag.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBaggage() {
      http
        .get(
          "http://localhost:9000/api/adds-on/airport/" + this.filterAirportId
        )
        .then(res => {
          this.baggages = res.data.reverse();
        })
        .catch(e => console.log(e));
    },
    getImage() {
      http
        .get("http://localhost:9000/api/baggage-image")
        .then(bimage => {
          this.images = bimage.data;
        })
        .catch(e => {
          console.log(e);
        });
    },
    getAirport() {
      http
        .get("http://localhost:9000/api/airport")
        .then(aerodrom => {
          this.name = aerodrom.data;
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.getBaggagetype();
    this.getImage();
    this.getAirport();
    this.getBaggage();
  },
  watch: {
    images() {
      // console.log("this.form");
    }
  }
};
</script>

<style scope>
.my-img {
  border-style: solid;
  padding: 10px;
  width: 18%;
  margin-left: 5px;
  border-radius: 10px;
}
.my-img2 {
  border-style: solid;
  border-color: red;
  padding: 10px;
  width: 18%;
  margin-left: 5px;
  border-radius: 10px;
}
.my-img3 {
  border-style: solid;
  border-color: rgb(24, 166, 231);
  padding: 10px;
  width: 18%;
  margin-left: 5px;
  border-radius: 10px;
}
.my-img:hover {
  border-color: red;
  border-style: solid;
  padding: 10px;
  width: 18%;
  margin-left: 5px;
  border-radius: 20px;
}
</style>