import {AuthService} from '../api'

const EStatus = {
  LOGIN_PENDING: 'login_pendding',
  LOGIN_SUCCESS: 'login_success',
  LOGIN_FAIL: 'login_fail'
}

const user = JSON.parse(localStorage.getItem('user'));

const initialState = user ? 
  { status: '',
    user: user,
    error:'',
    logedIn: true
  }
  :
  { status: '',
    user: '',
    error:'',
    logedIn: false
  } 
  
const auth = {
  namespaced: true,
  state: initialState,
  mutations: {
    LOGIN_PENDING(state){
      state.error = ''
      state.user = ''
      state.logedIn = false
      state.status = EStatus.LOGIN_PENDING
    },
    LOGIN_SUCCESS(state,result){
      state.status = EStatus.LOGIN_SUCCESS
      state.user = result
      state.logedIn = true
    },
    LOGIN_FAIL(state,error){
      state.error = error
      state.status = EStatus.LOGIN_FAIL
    },
    LOGOUT(state){
      state.logedIn = false
      state.user = ''
      state.error = ''
      state.status = ''
    },
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
        if (result.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(result.data));
        }
        location.reload();
        return true
      }catch(e){
        await commit('LOGIN_FAIL',e)
        return e
      }
    },
    async logout({commit}){
      localStorage.removeItem('user');
      await commit('LOGOUT')
    },
  },
}

export default auth
