import { BookFlightService } from '../api'

const bookFlight = {
  namespaced: true,
  state: {
    showSearchBar: true,
    bookLoading: false,
    flightDepartLoading: false,
    flightReturnLoading: false,
    pageLocation: 1, // 1 for Search, 2 for SelectFlight, 3 for guestDetail, 4 redirect to payment
    result:{ //for response
      bookResult: null,
      airport: null,
      flightDepart: null,
      flightReturn: null
    },
    data:{ //for request
      departDate: '',
      returnDate: '',
      flightDepart: null,
      flightReturn: null,
      airportDepart: null,
      airportArrive: null
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
      state.data.flightDepart = null
    },
    FLIGHT_DEPART_LOADING_SUCCESS(state,result){
      state.flightDepartLoading = false
      state.result.flightDepart = result
      state.pageLocation = 2
      state.showSearchBar = false
    },
    FLIGHT_RETURN_LOADING(state){
      state.flightReturnLoading = true
      state.data.flightReturn = null
    },
    FLIGHT_RETURN_LOADING_SUCCESS(state,result){
      state.flightReturnLoading = false
      state.result.flightReturn = result
      state.pageLocation = 2
      state.showSearchBar = false
    },
    GET_AIRPORT_SUCCESS(state,airport){
      state.result.airport = airport;
    },
    // Selection method
    goToMainPage(state){
      state.pageLocation = 1
    },
    showSearchBar(state){
      state.showSearchBar = true
      state.result.flightDepart = null
      state.result.flightReturn = null
    },
    selectDepartDate(state,date){
      state.data.departDate = date;
      let departDate = new Date(state.data.departDate)
      let returnDate = new Date(state.data.returnDate)
      if(returnDate - departDate < 0){
        state.data.returnDate = state.data.departDate
      }
    },
    selectReturnDate(state,date){
      state.data.returnDate = date;
    },
    selectDepartFlight(state,data){
      state.data.flightDepart = data
    },
    selectReturnFlight(state,data){
      state.data.flightReturn = data
    },
    selectDepartAirport(state,data){
      state.data.airportDepart = data
    },
    selectArriveAirport(state,data){
      state.data.airportArrive = data
    },
    NEXT_PAGE(state){
      state.pageLocation += 1;
      state.showSearchBar = false;
    }
  },
  actions: {
    // For book flight
    async bookFlight ({commit,state}){
      await commit('BOOK_PENDING')
      let data = {
        "departFlightId": state.data.flightDepart.id,
        "returnFlightId": state.data.flightReturn.id,
      }
      let result = await BookFlightService.bookFlight(data)
      await commit('BOOK_SUCCESS',result.data)
    },
    // Get flight departure by date
    async getFlightDepart({commit,state},date){
      await commit('selectDepartDate',date)
      await commit('FLIGHT_DEPART_LOADING')
      let result = await BookFlightService.getFlightByAirport(date,state.data.airportDepart.id,state.data.airportArrive.id);
      await commit('FLIGHT_DEPART_LOADING_SUCCESS',result.data)
    },
    // Get flight return by date
    async getFlightReturn({commit,state},date){
      await commit('selectReturnDate',date)
      await commit('FLIGHT_RETURN_LOADING')
      let result = await BookFlightService.getFlightByAirport(date,state.data.airportArrive.id,state.data.airportDepart.id);
      await commit('FLIGHT_RETURN_LOADING_SUCCESS',result.data)
    },
    // next Page
    async nextPage({state,commit,dispatch}){
      //use last page number
      if(state.pageLocation >= 3){ 
        await dispatch('bookFlight');
        await alert("จองสำเสร็จ BookingId ของท่านคือ " + state.result.bookResult.bookId)
      }else{
        await commit('NEXT_PAGE')
      }
    },
    async getAirport({commit}){
      let result = await BookFlightService.getAirport();
      await commit('GET_AIRPORT_SUCCESS',result.data)
      return result.data
    },
  },
  getters: {
    getTotalPrice: state => {
      let sum = 0;
      if(state.data.flightDepart != null){
        sum += state.data.flightDepart.price;
      }
      if(state.data.flightReturn != null){
        sum += state.data.flightReturn.price;
      }
      return sum;
    }
  }
}

export default bookFlight
