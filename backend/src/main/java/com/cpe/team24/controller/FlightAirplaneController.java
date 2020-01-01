package com.cpe.team24.controller;

import com.cpe.team24.entity.Airplane;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightAirplaneController {

    @Autowired
    private final AirplaneRepository airplaneRepository;

    public FlightAirplaneController(AirplaneRepository airplaneRepository) {

        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping("/flightAirplane")
    public Collection<Airplane> flightAirplanes() {
        return airplaneRepository.findAll().stream().collect(Collectors.toList());
    }

}
