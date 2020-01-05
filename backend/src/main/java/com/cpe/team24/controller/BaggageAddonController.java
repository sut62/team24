package com.cpe.team24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.cpe.team24.entity.BaggageAddon;
import com.cpe.team24.entity.BaggageType;
import com.cpe.team24.entity.BaggageImage;


import com.cpe.team24.repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BaggageAddonController {
    @Autowired
    private final BaggageAddonRepository baggageaddonRepository;
    @Autowired
    private BaggageImageRepository baggageimageRepository;
    @Autowired
    private BaggageTypeRepository baggagetypeRepository;
   

    public BaggageAddonController(BaggageAddonRepository baggageaddonRepository, BaggageImageRepository baggageimageRepository,
    BaggageTypeRepository baggagetypeRepository ) {
    this.baggageaddonRepository = baggageaddonRepository;
    this.baggageimageRepository = baggageimageRepository;
    this.baggagetypeRepository = baggagetypeRepository; }

    
    @GetMapping("/foodaddon")
    public Collection<BaggageAddon> Food() {
        return baggageaddonRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/foodaddon/{maxweight}/{price}/{baggageimage_id}/{baggagetype_id}")
    public BaggageAddon newBaggage(BaggageAddon newBaggage,
    
    @PathVariable String maxweight,
    @PathVariable String price,
   
    @PathVariable long baggageimage_id,
    @PathVariable long baggagetype_id) {
                        
    BaggageImage addbaggageimage = baggageimageRepository.findById(baggageimage_id);                    
    BaggageType addbaggagetype = baggagetypeRepository.findById(baggagetype_id);                                
                                  

    newBaggage.setMaxweight(maxweight);
    newBaggage.setPrice(price);

    newBaggage.setAddbaggageimage(addbaggageimage);
    newBaggage.setAddbaggagetype(addbaggagetype);


    return baggageaddonRepository.save(newBaggage); 

    
    
    }
}