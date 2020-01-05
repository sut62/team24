package com.cpe.team24.controller;

import java.util.Collection;

import com.cpe.team24.entity.PaymentType;
import com.cpe.team24.repository.PaymentTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payment-type")
@RestController
public class PaymentTypeController{
    @Autowired
    private PaymentTypeRepository paymentTypeRepository;

    @GetMapping("")
    public Collection<PaymentType> getAllPaymentType(){
        return paymentTypeRepository.findAll();
    }
}