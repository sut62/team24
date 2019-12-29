import {AuthService} from '../api'

const EStatus = {
  LOGIN_PENDING: 'login_pendding',
  LOGIN_SUCCESS: 'login_success',
}

const auth = {
  namespaced: true,
  state: {
    status: '',
    username:'',
    password:'',
    jwt:''
  },
  mutations: {
    LOGIN_PENDING({state}){
      state.status = EStatus.LOGIN_PENDING
    },
    LOGIN_SUCCESS({state},result){
      state.status = EStatus.LOGIN_SUCCESS
      state.jwt = result
    }
  },
  actions: {
    async requestLogin({state,commit}){
      await commit('LOGIN_PENDING')
      await AuthService.login({
        'username': state.username,
        'password': state.password
      }).then(result => {
        this.commit('LOGIN_SUCCESS',result.data)
      }).catch(e => console.log(e))
    }
  },
}

export default auth
