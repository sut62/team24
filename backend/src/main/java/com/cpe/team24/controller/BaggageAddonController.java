package com.cpe.team24.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
   

    public BaggageAddonController(BaggageAddonRepository baggageaddonRepository, BaggageImageRepository baggageimageRepository,
    BaggageTypeRepository baggagetypeRepository ) {
    this.baggageaddonRepository = baggageaddonRepository;
    this.baggageimageRepository = baggageimageRepository;
    this.baggagetypeRepository = baggagetypeRepository; }

    
    @GetMapping("")
    public Collection<BaggageAddon> Food() {
        return baggageaddonRepository.findAll();
    }

    @PostMapping("/{maxweight}/{price}/{baggageimage_id}/{baggagetype_id}")
    public BaggageAddon newBaggage(BaggageAddon newBaggage,
    
    @PathVariable String maxweight,
    @PathVariable String price,
   
    @PathVariable long baggageimage_id,
    @PathVariable long baggagetype_id) {
                        
    BaggageImage addbaggageimage = baggageimageRepository.findById(baggageimage_id);                    
    BaggageType addbaggagetype = baggagetypeRepository.findById(baggagetype_id);                                
                                  

    newBaggage.setMaxWeight(maxweight);
    newBaggage.setPrice(price);

    newBaggage.setBaggageImage(addbaggageimage);
    newBaggage.setBaggageType(addbaggagetype);


    return baggageaddonRepository.save(newBaggage); 

    
    
    }
}