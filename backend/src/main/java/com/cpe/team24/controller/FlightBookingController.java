package com.cpe.team24.controller;

import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.model.BodyFlightBooking;
import com.cpe.team24.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightBookingController {
    @Autowired
    private FlightBookingRepository flightBookingRepository;

    public FlightBookingController(FlightBookingRepository flightBookingRepository){
        this.flightBookingRepository = flightBookingRepository;
    }

    @GetMapping("/flight-booking")
    public List<FlightBooking> getBooking() {
        return flightBookingRepository.findAll();
    }

    @PostMapping("/flight-booking/book")
    public FlightBooking book(@RequestBody BodyFlightBooking bodyFlightBooking) {
        return null;
    }
}
