package com.cpe.team24.controller;

import com.cpe.team24.entity.CheckInType;
import com.cpe.team24.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/api")
public class CheckInTypeController {

    @Autowired
    private final CheckInTypeRepository checkInTypeRepository;
    
    public CheckInTypeController(CheckInTypeRepository checkInTypeRepository) {
        this.checkInTypeRepository = checkInTypeRepository;
    }

    @GetMapping("/checkInType")
    public Collection<CheckInType> checkInType() {
        return checkInTypeRepository.findAll().stream().collect(Collectors.toList());
    }

}
