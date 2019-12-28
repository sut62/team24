package com.cpe.team24.controller;

import com.cpe.team24.entity.FightCity;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class FightCityController {

    @Autowired
    private final FightCityRepository fightCityRepository ;

    public FightCityController(FightCityRepository fightCityRepository) {

        this.fightCityRepository = fightCityRepository;
    }

    @GetMapping("/fightCity")
    public Collection<FightCity> fightCity() {
        return fightCityRepository.findAll().stream().collect(Collectors.toList());
    }

}
