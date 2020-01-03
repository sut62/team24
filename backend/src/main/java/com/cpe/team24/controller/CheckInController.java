package com.cpe.team24.controller;

import com.cpe.team24.entity.User;
import com.cpe.team24.entity.CheckIn;
import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api/checkIn")
public class CheckInController {

    @Autowired
    private final CheckInRepository checkInRepository;
    private UserRepository userRepository;
    private FlightBookingRepository flightBookingRepository;

    public CheckInController(final CheckInRepository checkInRepository) {

        this.checkInRepository = checkInRepository;
    }

    @GetMapping("")
    public Collection<CheckIn> checkIn() {
        return checkInRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<CheckIn> checkIn(@PathVariable final Long id) {
        final Optional<CheckIn> checkIn = checkInRepository.findById(id);
        return checkIn;
    }



}
