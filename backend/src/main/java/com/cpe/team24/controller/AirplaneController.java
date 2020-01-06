package com.cpe.team24.controller;

import com.cpe.team24.entity.Airplane;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/airplane")
public class AirplaneController {

    @Autowired
    private final AirplaneRepository airplaneRepository;

    public AirplaneController(AirplaneRepository airplaneRepository) {

        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping("")
    public Collection<Airplane> flightAirplanes() {
        return airplaneRepository.findAll().stream().collect(Collectors.toList());
    }

}
