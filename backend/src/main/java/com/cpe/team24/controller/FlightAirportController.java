package com.cpe.team24.controller;

import com.cpe.team24.entity.Airport;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightAirportController {

   @Autowired
   private final AirportRepository airportRepository;

   public FlightAirportController(AirportRepository airportRepository) {
       this.airportRepository = airportRepository;
   }

   @GetMapping("/flightAirport")
   public Collection<Airport> fightAirport() {
       return airportRepository.findAll().stream().collect(Collectors.toList());
   }
}
