package com.cpe.team24.controller;

import com.cpe.team24.entity.CheckIn;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CheckInController {

    @Autowired
    private final CheckInRepository checkInRepository;

    public CheckInController(CheckInRepository checkInRepository) {

        this.checkInRepository = checkInRepository;
    }

    @GetMapping("/checkIn")
    public Collection<CheckIn> checkIn() {
        return checkInRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/checkIn/{id}")
    public Optional<CheckIn> checkIn(@PathVariable Long id) {
        Optional<CheckIn> checkIn = checkInRepository.findById(id);
        return checkIn;
    }

}
