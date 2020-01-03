
import HttpRequest from './http_request'

//  api/flight-booking
class BookFlightProvider extends HttpRequest {
  baseUrl = "/flight-booking"

  bookFlight (data) {
    return this.create( this.baseUrl + '/book', data)
  }
  getFlight (date) {
    return this.fetch('/flight/' + date)
  }
  getFlightByAirport (date,departAirportId,arriveAirportId) {
    return this.fetch('/flight/' + date + '/' + departAirportId + '/' + arriveAirportId)
  }
  getAirport(){
    return this.fetch('/airport')
  }
}

export default BookFlightProvider
