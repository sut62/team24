import { BookFlightService } from '../api'

const bookFlight = {
  namespaced: true,
  state: {
    bookLoading: false,
    flightDepartLoading: false,
    flightReturnLoading: false,
    pageLocation: 1, // 1 for Search, 2 for SelectFlight, 3 for Adds-on
    result:{
      bookResult: null,
      flightDepart: null,
      flightReturn: null
    },
    data:{
      flightDepart: null,
      flightReturn: null,
    }
  },
  mutations: {
    // Booking Methods
    BOOK_SUCCESS (state, result){
      state.bookLoading = false
      state.result.bookResult = result
    },
    BOOK_PENDING (state){
      state.bookLoading = true
      state.bookResult = null
    },
    // Flight Methods
    FLIGHT_DEPART_LOADING(state){
      state.flightDepartLoading = true
    },
    FLIGHT_DEPART_LOADING_SUCCESS(state,result){
      state.flightDepartLoading = false
      state.result.flightDepart = result
      state.pageLocation = 2
    },
    FLIGHT_RETURN_LOADING(state){
      state.flightReturnLoading = true
    },
    FLIGHT_RETURN_LOADING_SUCCESS(state,result){
      state.flightReturnLoading = false
      state.result.flightReturn = result
      state.pageLocation = 2
    },
    // Selection method
    selectDepartFlight(state,data){
      state.data.flightDepart = data
    },
    selectReturnFlight(state,data){
      state.data.flightReturn = data
    }
  },
  actions: {
    // For book flight
    async bookFlight ({commit},data){
      await commit('BOOK_PENDING')
      let result = await BookFlightService.bookFlight(data)
      await commit('BOOK_SUCCESS',result.data)
    },
    // Get flight departure by date
    async getFlightDepart({commit},date){
      await commit('FLIGHT_DEPART_LOADING')
      let result = await BookFlightService.getFlight(date);
      await commit('FLIGHT_DEPART_LOADING_SUCCESS',result.data)
    },
    // Get flight return by date
    async getFlightReturn({commit},date){
      await commit('FLIGHT_RETURN_LOADING')
      let result = await BookFlightService.getFlight(date);
      await commit('FLIGHT_RETURN_LOADING_SUCCESS',result.data)
    }
  },
  getters: {
    getTotalPrice: state => {
      let sum = 0;
      if(state.data.flightDepart){
        sum += state.data.flightDepart.price;
      }
      if(state.data.flightReturn){
        sum += state.data.flightDepart.price;
      }
      return sum;
    }
  }
}

export default bookFlight
