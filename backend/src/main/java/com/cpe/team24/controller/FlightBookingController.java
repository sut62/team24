package com.cpe.team24.controller;

import com.cpe.team24.entity.*;
import com.cpe.team24.model.BodyFlightBooking;
import com.cpe.team24.repository.*;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(path="/api/flight-booking")
public class FlightBookingController {
    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightBookingLinkRepository flightBookingLinkRepository;

    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @Autowired
    private FlightBookingTypeRepository flightBookingTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightAirportTypeRepository flightAirportTypeRepository;

    @Autowired
    private BaggageAddonRepository baggageAddonRepository;

    @Autowired
    private AirportRepository airportRepository;

    public FlightBookingController(
            final FlightBookingRepository flightBookingRepository,
            final FlightRepository flightRepository,
            final FlightBookingLinkRepository flightBookingLinkRepository,
            final BookingStatusRepository bookingStatusRepository,
            final UserRepository userRepository
    ){}

    @GetMapping("")
    public Collection<FlightBooking> getBooking() {
        return flightBookingRepository.findAll();
    }

    @GetMapping("/pending")
    public Collection<FlightBooking> getBookingPendingOnly() {
        return flightBookingRepository.findAllByBookingStatus(bookingStatusRepository.findByName(EBookingStatus.PENDING));
    }

    @GetMapping("/pending/user")
    public Collection<FlightBooking> getBookingPendingOnlyByUser(Authentication authentication) {
        Collection<FlightBooking> fiBookings = flightBookingRepository.findAllByBookingStatus(bookingStatusRepository.findByName(EBookingStatus.PENDING));
        Collection<FlightBooking> result = new ArrayList();
        for(FlightBooking flightBooking : fiBookings){
            if(flightBooking.getUser() == userRepository.findByUsername(authentication.getName()).get()){
                result.add(flightBooking);
            }
        }
        return result;
    }
    
    @PostMapping("/book")
    @PreAuthorize("hasRole('ROLE_MEMBER')")
    public FlightBooking book(@RequestBody final BodyFlightBooking bodyFlightBooking, final Authentication authentication) {
        System.out.println(authentication.getName());
        FlightBooking flightBooking = new FlightBooking();
        final Integer departSeatId = 1;
        final Integer returnSeatId = 1;
        flightBooking.book(departSeatId,returnSeatId);
        flightBooking.setUser(userRepository.findByUsername(authentication.getName()).orElse(null));
        flightBooking.setBookingStatus(bookingStatusRepository.findByName(EBookingStatus.PENDING));
        flightBooking = flightBookingRepository.save(flightBooking);

        // Add Depart's Flight and Return's Flight to TableLink 
        FlightBookingLink flightBookingLink= new FlightBookingLink();
        final Flight departFlight = flightRepository.findById(bodyFlightBooking.getDepartFlightId()).orElse(null);
        flightBookingLink.setFlight(departFlight);
        flightBookingLink.setFlightBooking(flightBooking);
        flightBookingLink.setFlightBookingType(flightBookingTypeRepository.findByName(EFlightBookingType.DEPART_FLIGHT));
        flightBookingLink.setBaggageAddon(baggageAddonRepository.findById(bodyFlightBooking.getDepartBaggageAddsOnId()).orElse(null));
        flightBookingLinkRepository.save(flightBookingLink);

        flightBookingLink = new FlightBookingLink();
        final Flight returnFlight = flightRepository.findById(bodyFlightBooking.getReturnFlightId()).orElse(null);
        flightBookingLink.setFlight(returnFlight);
        flightBookingLink.setFlightBooking(flightBooking);
        flightBookingLink.setFlightBookingType(flightBookingTypeRepository.findByName(EFlightBookingType.RETURN_FLIGHT));
        flightBookingLink.setBaggageAddon(baggageAddonRepository.findById(bodyFlightBooking.getReturnBaggageAddsOnId()).orElse(null));
        flightBookingLinkRepository.save(flightBookingLink);

        return flightBooking;
    }

    @GetMapping("/{book_id}")
    public FlightBooking FlightBookings(@PathVariable String book_id) {
        return flightBookingRepository.findByBookId(book_id).orElse(null);
    }   

    @GetMapping("/checkin/{lastname}/{depart_airport_id}/{arrive_airport_id}")
    public FlightBooking Users(@PathVariable final String lastname,@PathVariable final Long depart_airport_id,@PathVariable final Long arrive_airport_id) {
        User user = userRepository.findByLastName(lastname).orElseThrow(()->new MessageDescriptorFormatException("User Id not founded"));

        FlightBooking result = null;
        Collection<FlightBooking> flightBookings = flightBookingRepository.findAllByUser(user);
        for(FlightBooking flightBooking : flightBookings ){
            if(flightBooking.getBookingStatus() == bookingStatusRepository.findByName(EBookingStatus.PENDING)){
                for( FlightBookingLink flightBookingLink : flightBooking.getFlightBookingLinks()){
                    Integer correct = 0;
                    for(FlightAirport flightAirport : flightBookingLink.getFlight().getFlightAirports()){
                        if(flightAirport.getFlightAirportType() == flightAirportTypeRepository.findByName(EFlightAirportType.DEPART_AIRPORT)){
                            if(flightAirport.getAirport() == airportRepository.findById(depart_airport_id).orElseThrow(()->new MessageDescriptorFormatException("depart_airport_id not founded"))){
                                correct ++;
                            }else{
                                break;
                            }
                        }else{
                            if(flightAirport.getFlightAirportType() == flightAirportTypeRepository.findByName(EFlightAirportType.ARRIVE_AIRPORT)){
                                if(flightAirport.getAirport() == airportRepository.findById(arrive_airport_id).orElseThrow(()->new MessageDescriptorFormatException("depart_airport_id not founded"))){
                                    correct++;
                                    if(correct == 2){
                                        result = flightBooking;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @GetMapping("/user")
    public Collection<FlightBooking> getByUser(){
        System.out.println(flightBookingRepository.findAllByUser(userRepository.findByUsername("alice").get()));
        return flightBookingRepository.findAllByUser(userRepository.findByUsername("alice").get());
    }


}
