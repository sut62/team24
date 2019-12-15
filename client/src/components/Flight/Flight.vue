<template>
<div>
    <v-container fluid>
        <v-row>
            <v-col cols="12" sm="20" md="10">
                <v-card>
                    <v-toolbar>
                        <v-app-bar-nav-icon></v-app-bar-nav-icon>
                    </v-toolbar>
                    <v-card-text style="height: 300px;" class="grey lighten-5"></v-card-text>
                    <v-card-text style="height: 100px; position: relative">
                        <v-btn absolute dark fab top right color="pink" @click="dialog = !dialog">
                            <v-icon>mdi-plus</v-icon>
                        </v-btn>
                    </v-card-text>
                </v-card>
                <v-dialog v-model="dialog" max-width="800px">
                    <v-card>
                        <v-card-text>
                            <v-row>
                                <v-col cols="12" sm="6" md="6">
                                    <v-autocomplete label="เมืองต้นทาง" v-model="select1" :items="depart"></v-autocomplete>
                                </v-col>
                                <v-col cols="12" sm="6" md="6">
                                    <v-autocomplete label="เมืองปลายทาง" v-model="select2" :items="depart"></v-autocomplete>
                                </v-col>
                            </v-row>

                            <v-text-field label="ราคา(บาท)"></v-text-field>
                            <v-row>
                                <v-col cols="12" sm="6" md="6">
                                    <v-menu ref="menu" v-model="menu" :close-on-content-click="false" :return-value.sync="date" transition="scale-transition" offset-y min-width="290px">
                                        <template v-slot:activator="{ on }">
                                            <v-text-field v-model="date" label="วันออกเดินทาง" prepend-icon="event" readonly v-on="on" hint="YYYY/MM/DD format"></v-text-field>
                                        </template>
                                        <v-date-picker v-model="date" no-title scrollable>
                                            <v-spacer></v-spacer>
                                            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                                            <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                                        </v-date-picker>
                                    </v-menu>
                                </v-col>

                                <v-col cols="12" sm="6" md="6">
                                    <v-menu ref="menu" v-model="menu1" :close-on-content-click="false" :return-value.sync="date1" transition="scale-transition" offset-y min-width="290px">
                                        <template v-slot:activator="{ on }">
                                            <v-text-field v-model="date1" label="วันที่คาดว่าจะถึง" prepend-icon="event" readonly v-on="on" hint="YYYY/MM/DD format"></v-text-field>
                                        </template>
                                        <v-date-picker v-model="date1" no-title scrollable>
                                            <v-spacer></v-spacer>
                                            <v-btn text color="primary" @click="menu1 = false">Cancel</v-btn>
                                            <v-btn text color="primary" @click="$refs.menu.save(date1)">OK</v-btn>
                                        </v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>

                            <small class="grey--text">* This doesn't actually save.</small>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>

                            <v-btn text color="primary" @click="dialog = false">ยืนยันการเพิ่มเที่ยวบิน</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-col>
        </v-row>
    </v-container>
</div>
</template>

<script>
export default {
    name: "flight",
    data: () => ({
        modal: false,
        dialog: false,
        date: new Date().toISOString().substr(0, 10),
        date1: new Date().toISOString().substr(0, 10),
        menu: false,
        menu1: false,
        modal1: false,
        menu2: false,
        select1: "",
        select2: "",
        depart: ['กรุงเทพมหานคร', 'ขอนแก่น'],
    }),

};
</script>

<style scoped>

</style>
