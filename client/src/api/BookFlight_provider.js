
import HttpRequest from './http_request'

//  api/flight-booking
class BookFlightProvider extends HttpRequest {
  baseUrl = "/flight-booking"

  bookFlight (data) {
    return this.create( this.baseUrl + '/book', data)
  }
  getFlight (data) {
    return this.fetch( this.baseUrl + '/flight/' + data)
  }
}

export default BookFlightProvider
