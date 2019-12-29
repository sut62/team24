
import HttpRequest from './http_request'

class BookFlightProvider extends HttpRequest {

  BookFlightProvider(path){
    this.path = path
  }

  login (data) {
    return this.request( 'POST',this.path + '/login',data)
  }

}

export default BookFlightProvider
