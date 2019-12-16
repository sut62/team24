package com.cpe.team24.controller;

import com.cpe.team24.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightBookingController {
    @Autowired
    private FlightBookingRepository flightBookingRepository;

    public FlightBookingController(FlightBookingRepository flightBookingRepository){
        this.flightBookingRepository = flightBookingRepository;
    }

    @GetMapping("/book-flight/book")
    public boolean bookFlight() {

        return true;
    }
}
