<template>
  <v-row justify="center">
    <v-dialog v-model="openDialog" persistent max-width="600px">
      <template v-slot:activator="{ on }">
      
      </template>
      <v-card>
        <v-card-title>
          <span class="headline">จัดการสัมภาระ</span>
        </v-card-title>
        <div class="container">
          <div class="row">
            <div class="col-sm">
              <label for="formGroupExampleInput">สนามบิน</label>
              <select class="browser-default custom-select">
                  
              <option
                v-for="a in name"
                :key="a.id"
                :value="a.id"
              >{{a.name}}</option>
                </select>
              <br><br>
              <label >ประเภทสัมภาระ</label>
            <select class="browser-default custom-select">
                  
              <option
                v-for="t in btypename"
                :key="t.id"
                :value="t.id"
              >{{t.btypename}}</option>
                </select><br><br>

              <label for="formGroupExampleInput">น้ำหนักสูงสุด</label>
                <input type="text" class="form-control" placeholder="" v-model = "form.maxweight">
              <br>
              <label for="formGroupExampleInput">ราคา</label>
                <input type="text" class="form-control" placeholder="">
              <br>


              <label >รูปภาพ</label>
            <select class="browser-default custom-select">
                  
              <option
                v-for="i in imagename"
                :key="i.id"
                :value="i.id"
              >{{i.imagename}}</option>
                </select><br>
            </div>
            <div class="col-sm">
              One of three columns
            </div>
            
          </div>
        </div>
        
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDialog">Close</v-btn>
          <v-btn color="blue darken-1" text @click="saveDialog">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import axios from "axios";

let http = axios.create({
  baseURL: 'http://localhost:9000/api',
  timeout: 120000,
  headers: {
    'Access-Control-Allow-Origin': '*',
    "Content-type": "application/json",
  }
})


  export default {
    data: () => ({
  
      btypename: [],
      imagename: [],
      name: [],
      form:{
        maxweight: "",
        price: "",
        responsibility: "",
        experience: "",
        worktype: "",
      }
    }),
    props:{
      openDialog:{
        type: Boolean
      },
      closeDialog:{
        type: Function
      }
    },
    
    methods:{
      saveDialog(){
        console.log("Save")
      },
      getBaggagetype() {
      axios
        .get("http://localhost:9000/baggagetype")
        .then(bag => {
          this.btypename = bag.data;
        })
        .catch(e => {
          console.log(e);
        });
    },

    getImage() {
      http
        .get("http://localhost:9000/bagimage")
        .then(bimage => {
          this.imagename = bimage.data;
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
    
  }
  }
</script>

<style>

</style>