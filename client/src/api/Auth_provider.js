
import HttpRequest from './http_request'

class AuthProvider extends HttpRequest {

  login (data) {
    return this.create('/auth/login',data)
  }
}

export default AuthProvider
