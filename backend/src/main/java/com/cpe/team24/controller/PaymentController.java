package com.cpe.team24.controller;

import java.util.Date;
import java.util.Collection;

import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.entity.FlightBookingLink;
import com.cpe.team24.entity.Payment;
import com.cpe.team24.entity.PaymentWay;
import com.cpe.team24.entity.Promotion;
import com.cpe.team24.entity.PromotionCode;
import com.cpe.team24.repository.FlightBookingRepository;
import com.cpe.team24.repository.PaymentRepository;
import com.cpe.team24.repository.PaymentWayRepository;
import com.cpe.team24.repository.PromotionCodeRepository;
import com.cpe.team24.repository.PromotionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;

@RequestMapping("/api/payment")
@RestController
public class PaymentController{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentWayRepository paymentWayRepository;
    @Autowired
    private FlightBookingRepository flightBookingRepository;
    @Autowired
    private PromotionRepository promotionRepository ;
    @Autowired
    private PromotionCodeRepository promotionCodeRepository;
    @GetMapping("")
    public Collection<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
    @PostMapping("/{flight_booking_id}/{payment_way_id}/{promotion_code}")
    public Payment createPayment(@PathVariable Long flight_booking_id,@PathVariable Long payment_way_id,@PathVariable String promotion_code ){
        Payment payment = new Payment();

        FlightBooking flightBooking = flightBookingRepository.findById(flight_booking_id).orElse(null);
        PaymentWay paymentWay = paymentWayRepository.findById(payment_way_id).orElse(null);
        PromotionCode promotionCode = promotionCodeRepository.findByCode(promotion_code);

        payment.setPayDate(new Date());
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        
        Double total = (double) 0;
        for(FlightBookingLink fbl : flightBooking.getFlightBookingLinks()){
            total += fbl.getFlight().getPrice();
        }
        total -= (double) promotionCode.getDiscount()/100.0 * total;
        payment.setAmount(total);
        return paymentRepository.save(payment);
    }
    
    @PostMapping("/{flight_booking_id}/{payment_way_id}")
    public Payment createPayment(@PathVariable Long flight_booking_id,@PathVariable Long payment_way_id ){
        Payment payment = new Payment();

        FlightBooking flightBooking = flightBookingRepository.findById(flight_booking_id).orElse(null);
        PaymentWay paymentWay = paymentWayRepository.findById(payment_way_id).orElse(null);

        payment.setPayDate(new Date());
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        
        Double total = (double) 0;
        for(FlightBookingLink fbl : flightBooking.getFlightBookingLinks()){
            total += fbl.getFlight().getPrice();
        }
        payment.setAmount(total);
        return paymentRepository.save(payment);
    }
    
}