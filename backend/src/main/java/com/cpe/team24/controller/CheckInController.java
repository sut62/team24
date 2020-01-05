package com.cpe.team24.controller;

import com.cpe.team24.entity.User;
import com.cpe.team24.model.BodyCheckIn;
import com.cpe.team24.entity.CheckIn;
import com.cpe.team24.entity.CheckInStatus;
import com.cpe.team24.entity.CheckInType;
import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cpe.team24.model.BodyCheckIn;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

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

    // @PostMapping("/create/{email}/{checkInStatusId}/{checkInTypeId}/{flightBookingId}")
    // public CheckIn newCheckIn(CheckIn newCheckIn,
    // @PathVariable String email,@PathVariable Long checkInStatusId ,@PathVariable Long CheckInTypeId ,@PathVariable Long flightBookingId) {
    //     CheckInStatus checkInStatus = checkInStatusRepository.findById(checkInStatusId).orElse(null);
    //     CheckInType checkInType = checkInTypeRepository.findById(CheckInTypeId).orElse(null);
    //     FlightBooking flightBooking = flightBookingRepository.findById(flightBookingId).orElse(null);
    //     return checkInRepository.save(newCheckIn); // บันทึก Objcet ชื่อ MenuDesign
    // }

    @PostMapping("/create")
    public CheckIn newCheckIn(@RequestBody final BodyCheckIn bodyCheckIn) {
        CheckIn checkIn = new CheckIn();
        checkIn.setBoardingPass("dsfsdf");
        checkIn.setEmail(bodyCheckIn.getEmail());
        checkIn.setDate(new Date());
        checkIn.setCheckInStatus(checkInStatusRepository.findById(bodyCheckIn.getCheckInStatusId()).orElse(null));
        checkIn.setCheckInType(checkInTypeRepository.findById(bodyCheckIn.getCheckInTypeId()).orElse(null));
        checkIn.setFlightBooking(flightBookingRepository.findById(bodyCheckIn.getFlightBookingId()).orElse(null));
        return checkInRepository.save(checkIn);
    }

}
