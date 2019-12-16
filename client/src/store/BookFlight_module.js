import { BookFlightService } from '../resource'

const bookFlight = {
  namespaced: true,
  state: {
    loading: false,
    bookResult: null
  },
  mutations: {
    BOOK_SUCCESS (state, result){
      state.loading = false
      state.bookResult = result
    },
    BOOK_PENDING (state){
      state.loading = true
      state.bookResult = null
    }
  },
  actions: {
    async bookFlight ({ commit } ,data){
      await commit('BOOK_PENDING');
      let result = await BookFlightService.bookFlight(data);
      await commit('BOOK_SUCCESS',result);
    }
  },
  getters: {
    
  }
}

export default bookFlight
