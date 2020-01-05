package com.cpe.team24.controller;

import com.cpe.team24.entity.CheckInStatus;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class CheckInStatusController {

    @Autowired
    private final CheckInStatusRepository checkInStatusRepository;

    public CheckInStatusController(CheckInStatusRepository checkInStatusRepository) {

        this.checkInStatusRepository = checkInStatusRepository;
    }

    @GetMapping("/checkInStatus")
    public Collection<CheckInStatus> checkInStatus() {
        return checkInStatusRepository.findAll().stream().collect(Collectors.toList());
    }

}
