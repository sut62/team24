import { BookFlightService } from '../api'
// import router from '../router'

const bookFlight = {
  namespaced: true,
  state: {
    showSearchBar: true,//true,
    bookLoading: false,
    flightDepartLoading: false,
    flightReturnLoading: false,
    pageLocation: 1, // 1 for Search, 2 for SelectFlight, 3 for guestDetail, 4 adds on , 5 redirect to payment
    result:{ //for response
      bookResult: null,
      airport: null,
      flightDepart: null,
      flightReturn: null
    },
    data:{ //for request
      baggageDepart: null,
      baggageReturn: null,
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
    SELECT_BAGGAGE_DEPART(state,baggage){
      state.data.baggageDepart = baggage
    },
    SELECT_BAGGAGE_RETURN(state,baggage){
      state.data.baggageReturn = baggage
    },
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
        "departBaggageAddsOnId": state.data.baggageDepart == null? 0 : state.data.baggageDepart.id,
        "returnBaggageAddsOnId":  state.data.baggageReturn == null? 0 : state.data.baggageReturn.id,
      }
      let result = await BookFlightService.bookFlight(data)
      await commit('BOOK_SUCCESS',result.data)
      // await router.push({ name: 'payment', params: {bookId: result.data.bookId }})
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
      // 1 for Search, 2 for SelectFlight, 3 for guestDetail, 4 adds on , 5 redirect to payment
      if(state.pageLocation+1 >= 5){ 
        await dispatch('bookFlight');
        // await alert("จองสำเสร็จ BookingId ของท่านคือ " + state.result.bookResult.bookId)
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
    getIsNextBtnAllow: state => {
      if(state.data.flightDepart == null || state.data.flightReturn == null){
        return false;
      }
      return true;
    },
    getTotalPrice: state => {
      let sum = 0;
      if(state.data.flightDepart != null){
        sum += state.data.flightDepart.price;
      }
      if(state.data.flightReturn != null){
        sum += state.data.flightReturn.price;
      }
      if(state.data.baggageDepart != null){
        sum += state.data.baggageDepart.price;
      }
      if(state.data.baggageReturn != null){
        sum += state.data.baggageReturn.price;
      }
      return sum;
    },
    getBaggagesPrice: state => {
      let sum = 0;
      if(state.data.baggageDepart != null){
        sum += state.data.baggageDepart.price;
      }
      if(state.data.baggageReturn != null){
        sum += state.data.baggageReturn.price;
      }
      return sum
    }
  }
}

export default bookFlight
