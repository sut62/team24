package com.cpe.team24.controller;

import java.util.Collection;

import com.cpe.team24.entity.PaymentWay;
import com.cpe.team24.repository.PaymentWayRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payment-way")
@RestController
public class PaymentWayController{
    @Autowired
    private PaymentWayRepository paymentWayRepository;

    @GetMapping("")
    public Collection<PaymentWay> getAllPaymentWay(){
        return paymentWayRepository.findAll();
    }
}