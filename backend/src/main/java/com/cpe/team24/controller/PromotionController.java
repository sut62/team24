package com.cpe.team24.controller;

import com.cpe.team24.entity.Promotion;
import com.cpe.team24.entity.PromotionCode;
import com.cpe.team24.entity.PromotionImage;
import com.cpe.team24.repository.PromotionCodeRepository;
import com.cpe.team24.repository.PromotionImageRepository;
import com.cpe.team24.repository.PromotionRepository;
import com.cpe.team24.repository.PromotionStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.*;

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
    private PromotionStatusRepository promotionStatusRepository;

    @Autowired
    private PromotionImageRepository promotionImageRepository;

    @Autowired
    private PromotionCodeRepository promotionCodeRepository;

    @GetMapping("")
    public Collection<Promotion> getAllPromotions(){
        return promotionRepository.findAll();
    }

    @PostMapping("/{name}/{desc}/{code}/{discount}/{exp}/{img_id}")
    public Promotion createPromotion(@PathVariable String name,@PathVariable String desc,@PathVariable String code,@PathVariable Integer discount,@PathVariable String exp,@PathVariable Long img_id) throws ParseException {
        Promotion promotion = new Promotion();
        promotion.setName(name);
        promotion.setDesc(desc);
        PromotionCode promotionCode = new PromotionCode();
        promotionCode.setCode(code);
        promotionCode.setDiscount(discount);
        Date expDate = new SimpleDateFormat("YYYY-MM-DD").parse(exp);
        promotionCode.setExp(expDate);
        promotion.setPromotionImage(promotionImageRepository.findById(img_id).get());
        promotion.setPromotionCode(promotionCode);
        promotionCodeRepository.save(promotionCode);
        return promotionRepository.save(promotion);
    }
}
