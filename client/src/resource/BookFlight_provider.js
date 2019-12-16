
import HttpRequest from './http_request'

//  api/book-flight
class BookFlightProvider extends HttpRequest {
  bookFlight (data) {
    return this.create('/book', data)
  }
}

export default BookFlightProvider
