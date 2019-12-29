package com.cpe.team24.controller;

import com.cpe.team24.entity.FightAirplane;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FightAirplaneController {

    @Autowired
    private final FightAirplaneRepository fightAirplaneRepository;

    public FightAirplaneController(FightAirplaneRepository fightAirplaneRepository) {

        this.fightAirplaneRepository = fightAirplaneRepository;
    }

    @GetMapping("/fightAirplane")
    public Collection<FightAirplane> flightAirplanes() {
        return fightAirplaneRepository.findAll().stream().collect(Collectors.toList());
    }

}
