package com.cpe.team24.controller;

import com.cpe.team24.entity.*;
import com.cpe.team24.model.BodyFlightBooking;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    private FlightTypeRepository flightTypeRepository;

    @Autowired
    private UserRepository userRepository;

    public FlightBookingController(
            FlightBookingRepository flightBookingRepository,
            FlightRepository flightRepository,
            FlightBookingLinkRepository flightBookingLinkRepository,
            BookingStatusRepository bookingStatusRepository,
            UserRepository userRepository
    ){}

    @GetMapping("")
    public Collection<FlightBooking> getBooking() {
        return flightBookingRepository.findAll();
    }

    @PostMapping("/book")
    @PreAuthorize("hasRole('MEMBER')")
    public FlightBooking book(@RequestBody BodyFlightBooking bodyFlightBooking, Authentication authentication) {
        System.out.println(authentication.getName());
        FlightBooking flightBooking = new FlightBooking();
        Integer departSeatId = 1;
        Integer returnSeatId = 1;
        flightBooking.book(departSeatId,returnSeatId);
        flightBooking.setUser(userRepository.findByUsername(authentication.getName()).orElse(null));
        BookingStatus bs = bookingStatusRepository.findById(1).orElse(null);
        flightBooking.setBookingStatus(bs);
        flightBooking = flightBookingRepository.save(flightBooking);

        // Add Depart's Flight and Return's Flight to TableLink
        Flight departFlight = flightRepository.findById(bodyFlightBooking.getDepartFlightId()).orElse(null);
        Flight returnFlight = flightRepository.findById(bodyFlightBooking.getReturnFlightId()).orElse(null);

        FlightBookingLink flightBookingLink= new FlightBookingLink();
        flightBookingLink.setFlight(departFlight);
        flightBookingLink.setFlightBooking(flightBooking);
        flightBookingLink.setFlightType(flightTypeRepository.findByName(EFlightType.DEPART_FLIGHT));
        flightBookingLinkRepository.save(flightBookingLink);

        flightBookingLink = new FlightBookingLink();
        flightBookingLink.setFlight(returnFlight);
        flightBookingLink.setFlightBooking(flightBooking);
        flightBookingLink.setFlightType(flightTypeRepository.findByName(EFlightType.RETURN_FLIGHT));
        flightBookingLinkRepository.save(flightBookingLink);

        return flightBooking;
    }
}
