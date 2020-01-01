package com.cpe.team24.controller;

import com.cpe.team24.entity.City;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/flight-city")
public class FlightCityController {

    @Autowired
    private final CityRepository cityRepository;

    public FlightCityController(CityRepository cityRepository) {

        this.cityRepository = cityRepository;
    }

    @GetMapping("")
    public Collection<City> fightCity() {
        return cityRepository.findAll().stream().collect(Collectors.toList());
    }

}
