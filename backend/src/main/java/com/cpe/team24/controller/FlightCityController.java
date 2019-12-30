package com.cpe.team24.controller;

import com.cpe.team24.entity.FlightCity;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/flight-city")
public class FlightCityController {

    @Autowired
    private final FlightCityRepository flightCityRepository ;

    public FlightCityController(FlightCityRepository flightCityRepository) {

        this.flightCityRepository = flightCityRepository;
    }

    @GetMapping("")
    public Collection<FlightCity> fightCity() {
        return flightCityRepository.findAll().stream().collect(Collectors.toList());
    }

}
