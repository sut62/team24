<template>
    <div id="alert" class="my-alert">
        <template>
            <v-row justify="center">
                <v-dialog v-model="open" persistent max-width="290">
                <!-- <template v-slot:activator="{ on }">
                    <v-btn color="primary" dark v-on="on">Open Dialog</v-btn>
                </template> -->
                <v-card>
                    <v-card-title class="headline">{{topic}}</v-card-title>
                    <v-card-text>{{desc}}</v-card-text>
                    <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green darken-1" text @click="callback">ตกลง</v-btn>
                    </v-card-actions>
                </v-card>
                </v-dialog>
            </v-row>
        </template>
    </div>
</template>

<script>
export default {
    props:{
        topic:{
            type: String
        },
        desc:{
            type: String
        },
        open:{
            type: Boolean
        },
        callback:{
            type: Function
        }
    },
    methods:{
        submit(){
            // this.open = false
            this.callback
        }
    }
}
</script>

<style scoped>
.my-alert{
    position: absolute;
    top: 0px;
    z-index: 10;
    width: 100vw;
}
</style>