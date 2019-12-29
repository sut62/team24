import BookFlightProvider from './BookFlight_provider'
import AuthProvider from './Auth_provider'

// Give arg to provider to start endpoint with specific path for example = xxx.com/api/person
export const BookFlightService = new BookFlightProvider("flight-booking")
export const AuthService = new AuthProvider("auth")