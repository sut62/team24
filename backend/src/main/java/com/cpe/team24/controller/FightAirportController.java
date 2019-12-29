package com.cpe.team24.controller;

import com.cpe.team24.entity.FightAirport;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FightAirportController {

   @Autowired
   private final FightAirportRepository fightAirportRepository ;

   public FightAirportController(FightAirportRepository fightAirportRepository) {
       this.fightAirportRepository = fightAirportRepository;
   }

   @GetMapping("/fightAirport")
   public Collection<FightAirport> fightAirport() {
       return fightAirportRepository.findAll().stream().collect(Collectors.toList());
   }
}
