package com.cpe.team24.controller;

import com.cpe.team24.entity.PromotionCode;
import com.cpe.team24.repository.PromotionCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promotion-code")
public class PromotionCodeController {

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @GetMapping("{code}")
    public PromotionCode getAllPromotions(@PathVariable String code){
        return promotionCodeRepository.findByCode(code);
    }
}
