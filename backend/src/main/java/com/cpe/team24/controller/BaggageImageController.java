package com.cpe.team24.controller;

import com.cpe.team24.entity.BaggageImage;
import com.cpe.team24.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BaggageImageController {

    @Autowired
    private final BaggageImageRepository baggageImageRepository;

    public BaggageImageController(BaggageImageRepository baggageImageRepository) {
        this.baggageImageRepository = baggageImageRepository;
    }

    @GetMapping("/bagimage")
    public Collection<BaggageImage> Foodimg() {
        return baggageImageRepository.findAll().stream().collect(Collectors.toList());
    }

}