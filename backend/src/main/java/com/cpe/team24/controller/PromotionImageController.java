package com.cpe.team24.controller;

import com.cpe.team24.entity.PromotionImage;
import com.cpe.team24.repository.PromotionImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/promotion-image")
public class PromotionImageController {

    @Autowired
    private PromotionImageRepository promotionImageRepository;

    @GetMapping("")
    private Collection<PromotionImage> getAllPromotionImages(){
        return promotionImageRepository.findAll();
    }

}
