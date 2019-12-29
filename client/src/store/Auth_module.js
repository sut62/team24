import {AuthService} from '../api'

const EStatus = {
  LOGIN_PENDING: 'login_pendding',
  LOGIN_SUCCESS: 'login_success',
  LOGIN_FAIL: 'login_fail'
}

const auth = {
  namespaced: true,
  state: {
    status: '',
    jwt:'',
    error:''
  },
  mutations: {
    LOGIN_PENDING(state){
      state.error = ''
      state.jwt = ''
      state.status = EStatus.LOGIN_PENDING
    },
    LOGIN_SUCCESS(state,result){
      state.status = EStatus.LOGIN_SUCCESS
      state.jwt = result
    },
    LOGIN_FAIL(state,error){
      state.error = error
      state.state = EStatus.LOGIN_ERROR
    }
  },
  actions: {
    async requestLogin({commit},data){

      await commit('LOGIN_PENDING')
      try{
        let result =  await AuthService.login({
          'username': data.username,
          'password': data.password
        })
        await commit('LOGIN_SUCCESS',result.data)
      }catch(e){
        await commit('LOGIN_FAIL',e)
      }
    },
  },
}

export default auth
