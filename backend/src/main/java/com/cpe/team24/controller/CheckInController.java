package com.cpe.team24.controller;

import com.cpe.team24.model.BodyCheckIn;
import com.cpe.team24.entity.CheckIn;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping(path="/api/checkin")
public class CheckInController {

    @Autowired
    private final CheckInRepository checkInRepository;
    @Autowired
    private CheckInStatusRepository checkInStatusRepository;
    @Autowired
    private CheckInTypeRepository checkInTypeRepository;
    @Autowired
    private FlightBookingRepository flightBookingRepository;
    public CheckInController(
        CheckInRepository checkInRepository,
        CheckInStatusRepository checkInStatusRepository,
        CheckInTypeRepository checkInTypeRepository,
        FlightBookingRepository flightBookingRepository) {


        this.checkInRepository = checkInRepository;
        this.checkInStatusRepository = checkInStatusRepository;
        this.checkInTypeRepository = checkInTypeRepository;
        this.flightBookingRepository = flightBookingRepository;
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

    @PostMapping("/create")
    public CheckIn newCheckIn(@RequestBody final BodyCheckIn bodyCheckIn) {
        CheckIn checkIn = new CheckIn();
        checkIn.setCheckInStatus(checkInStatusRepository.findById((1L)).orElse(null));
        checkIn.setCheckInType(checkInTypeRepository.findById((1L)).orElse(null));
        checkIn.setFlightBooking(flightBookingRepository.findById(bodyCheckIn.getFlightBookingId()).orElse(null));
        checkIn.setBoardingPass(RandomString.make(6).toUpperCase());
        checkIn.setEmail(bodyCheckIn.getEmail());
        checkIn.setDate(new Date());
        return checkInRepository.save(checkIn);
    }

}
