package com.cpe.team24.controller;

import com.cpe.team24.entity.BookingStatus;
import com.cpe.team24.entity.Flight;
import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.entity.FlightBookingLink;
import com.cpe.team24.model.BodyFlightBooking;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "http://localhost:8080")
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
    private MemberRepository memberRepository;

    public FlightBookingController(
        FlightBookingRepository flightBookingRepository,
        FlightRepository flightRepository,
        FlightBookingLinkRepository flightBookingLinkRepository,
        BookingStatusRepository bookingStatusRepository,
        MemberRepository memberRepository
    ){}

    @GetMapping("")
    public Collection<FlightBooking> getBooking() {
        return flightBookingRepository.findAll();
    }

    @PostMapping("/book")
    public FlightBooking book(@RequestBody BodyFlightBooking bodyFlightBooking) {
        FlightBooking flightBooking = new FlightBooking();
        Integer departSeatId = 1;
        Integer returnSeatId = 1;
        flightBooking.book(departSeatId,returnSeatId);
        flightBooking.setMember(memberRepository.findById(bodyFlightBooking.getMemberId()).orElse(null));
        BookingStatus bs = bookingStatusRepository.findById(1).orElse(null);
        flightBooking.setBookingStatus(bs);
        flightBooking = flightBookingRepository.save(flightBooking);

        // Add Depart's Flight and Return's Flight to TableLink
        Flight departFlight = flightRepository.findById(bodyFlightBooking.getDepartFlightId()).orElse(null);
        Flight returnFlight = flightRepository.findById(bodyFlightBooking.getReturnFlightId()).orElse(null);

        FlightBookingLink flightBookingLink= new FlightBookingLink();
        flightBookingLink.setFlight(departFlight);
        flightBookingLink.setFlightBooking(flightBooking);
        flightBookingLink.setDepartFlight(true);
        flightBookingLinkRepository.save(flightBookingLink);

        flightBookingLink = new FlightBookingLink();
        flightBookingLink.setFlight(returnFlight);
        flightBookingLink.setFlightBooking(flightBooking);
        flightBookingLink.setDepartFlight(false);
        flightBookingLinkRepository.save(flightBookingLink);

        return flightBooking;
    }
}