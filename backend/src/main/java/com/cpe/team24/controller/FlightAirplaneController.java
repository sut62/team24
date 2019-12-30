package com.cpe.team24.controller;

import com.cpe.team24.entity.FlightAirplane;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightAirplaneController {

    @Autowired
    private final FlightAirplaneRepository flightAirplaneRepository;

    public FlightAirplaneController(FlightAirplaneRepository flightAirplaneRepository) {

        this.flightAirplaneRepository = flightAirplaneRepository;
    }

    @GetMapping("/flightAirplane")
    public Collection<FlightAirplane> flightAirplanes() {
        return flightAirplaneRepository.findAll().stream().collect(Collectors.toList());
    }

}
