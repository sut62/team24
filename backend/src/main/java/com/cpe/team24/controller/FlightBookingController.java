package com.cpe.team24.controller;

import com.cpe.team24.entity.Flight;
import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.entity.FlightBookingLink;
import com.cpe.team24.model.BodyFlightBooking;
import com.cpe.team24.repository.FlightBookingLinkRepository;
import com.cpe.team24.repository.FlightBookingRepository;
import com.cpe.team24.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightBookingController {
    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FlightBookingLinkRepository flightBookingLinkRepository;

    public FlightBookingController(
        FlightBookingRepository flightBookingRepository,
        FlightRepository flightRepository,
        FlightBookingLinkRepository flightBookingLinkRepository
    ){
        this.flightBookingRepository = flightBookingRepository;
        this.flightRepository = flightRepository;
        this.flightBookingLinkRepository = flightBookingLinkRepository;
    }

    @GetMapping("/flight-booking")
    public Collection<FlightBooking> getBooking() {
        return flightBookingRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/flight-booking/book")
    public FlightBooking book(@RequestBody BodyFlightBooking bodyFlightBooking) {
        FlightBooking flightBooking = new FlightBooking();
        Flight departFlight = flightRepository.findById(bodyFlightBooking.getDepartFlightId()).orElse(null);
        Flight returnFlight = flightRepository.findById(bodyFlightBooking.getReturnFlightId()).orElse(null);
        Integer departSeatId = 1;
        Integer returnSeatId = 1;
        flightBooking.book(departFlight,returnFlight,departSeatId,returnSeatId);
        return flightBookingRepository.save(flightBooking);
    }
}
