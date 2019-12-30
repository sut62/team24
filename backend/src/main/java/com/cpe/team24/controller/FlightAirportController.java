package com.cpe.team24.controller;

import com.cpe.team24.entity.FlightAirport;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FlightAirportController {

   @Autowired
   private final FlightAirportRepository flightAirportRepository ;

   public FlightAirportController(FlightAirportRepository flightAirportRepository) {
       this.flightAirportRepository = flightAirportRepository;
   }

   @GetMapping("/flightAirport")
   public Collection<FlightAirport> fightAirport() {
       return flightAirportRepository.findAll().stream().collect(Collectors.toList());
   }
}
