package com.cpe.team24.controller;

import com.cpe.team24.entity.PromotionStatus;
import com.cpe.team24.repository.PromotionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/promotion-status")
public class PromotionStatusController {

    @Autowired
    private PromotionStatusRepository promotionStatusRepository;

    @GetMapping("")
    private Collection<PromotionStatus> getAllPromotionImages(){
        return promotionStatusRepository.findAll();
    }

}
