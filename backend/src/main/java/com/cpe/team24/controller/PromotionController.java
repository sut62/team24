package com.cpe.team24.controller;

import com.cpe.team24.entity.Promotion;
import com.cpe.team24.entity.PromotionCode;
import com.cpe.team24.model.BodyPromotion;
import com.cpe.team24.repository.PromotionCodeRepository;
import com.cpe.team24.repository.PromotionImageRepository;
import com.cpe.team24.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionImageRepository promotionImageRepository;

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @GetMapping("")
    public Collection<Promotion> getAllPromotions(){
        return promotionRepository.findAll();
    }

    @PostMapping("")
    public Promotion createPromotion(@RequestBody BodyPromotion bodyPromotion) throws ParseException {
        Promotion promotion = new Promotion();
        promotion.setName(bodyPromotion.getProName());
        promotion.setDesc(bodyPromotion.getDesc());
        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setCode(bodyPromotion.getCode());
        promotionCode.setDiscount(bodyPromotion.getDiscount());
        Date expDate = new SimpleDateFormat("YYYY-MM-DD").parse(bodyPromotion.getExp());
        promotionCode.setExp(expDate);
        promotion.setPromotionImage(promotionImageRepository.findById(bodyPromotion.getImg_id()).get());
        promotion.setPromotionCode(promotionCode);
        promotionCodeRepository.save(promotionCode);
        return promotionRepository.save(promotion);
    }
}
