import Vue from 'vue'
import Vuex from 'vuex'
import BookFlightModule from './BookFlight_module'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    BookFlight: BookFlightModule
  }
})
