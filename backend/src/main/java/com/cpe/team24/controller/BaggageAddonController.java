package com.cpe.team24.controller;

import com.cpe.team24.entity.Airport;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.var;

import java.util.Collection;

import javax.validation.Valid;

import com.cpe.team24.entity.BaggageAddon;
import com.cpe.team24.entity.BaggageType;
import com.cpe.team24.entity.BaggageImage;


import com.cpe.team24.repository.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/adds-on")
public class BaggageAddonController {
    @Autowired
    private final BaggageAddonRepository baggageaddonRepository;
    @Autowired
    private BaggageImageRepository baggageimageRepository;
    @Autowired
    private BaggageTypeRepository baggagetypeRepository;
    @Autowired
    private AirportRepository airportRepository;

    public BaggageAddonController(BaggageAddonRepository baggageaddonRepository, BaggageImageRepository baggageimageRepository,
    BaggageTypeRepository baggagetypeRepository ) {
    this.baggageaddonRepository = baggageaddonRepository;
    this.baggageimageRepository = baggageimageRepository;
    this.baggagetypeRepository = baggagetypeRepository; }

    
    @GetMapping("")
    public Collection<BaggageAddon> getAllAddsOn() {
        return baggageaddonRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    @GetMapping("/airport/{id}")
    public Collection<BaggageAddon> getAllAddsOn(@PathVariable Long id) {
        return baggageaddonRepository.findAllByAirport(airportRepository.findById(id).orElse(null));
    }

    @PostMapping("/{maxweight}/{price}/{baggageimage_id}/{baggagetype_id}/{airport_id}")
    public BaggageAddon newBaggage(BaggageAddon newBaggage,
    
    @PathVariable Integer maxweight,
    @PathVariable Double price,
   
    @PathVariable long baggageimage_id,
    @PathVariable long baggagetype_id,
    @PathVariable long airport_id
   ) {
                        
    BaggageImage addbaggageimage = baggageimageRepository.findById(baggageimage_id);                    
    BaggageType addbaggagetype = baggagetypeRepository.findById(baggagetype_id);
    Airport airport = airportRepository.findById(airport_id).orElseThrow(()-> new MessageDescriptorFormatException("Can not find airport id"));

    newBaggage.setAirport(airport);

    newBaggage.setMaxWeight(maxweight);
    newBaggage.setPrice(price);

    newBaggage.setBaggageImage(addbaggageimage);
    newBaggage.setBaggageType(addbaggagetype);


    return baggageaddonRepository.save(newBaggage);

    
    
    }
    @DeleteMapping("/{id}")
    public boolean deleteBag(@Valid @PathVariable Long id) {
        baggageaddonRepository.deleteById(id);
        return true;
    }


}