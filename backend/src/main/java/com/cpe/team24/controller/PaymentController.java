package com.cpe.team24.controller;

import java.util.Date;
import java.util.Collection;

import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.entity.FlightBookingLink;
import com.cpe.team24.entity.Payment;
import com.cpe.team24.repository.FlightBookingRepository;
import com.cpe.team24.repository.PaymentRepository;
import com.cpe.team24.repository.PaymentWayRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payment")
@RestController
public class PaymentController{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentWayRepository paymentWayRepository;
    @Autowired
    private FlightBookingRepository flightBookingRepository;

    @GetMapping("")
    public Collection<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
    @PostMapping("/{flight_booking_id}/{payment_way_id}")
    public Payment createPayment(@PathVariable Long flight_booking_id,@PathVariable Long payment_way_id){
        Payment payment = new Payment();
        payment.setPayDate(new Date());
        FlightBooking flightBooking = flightBookingRepository.findById(flight_booking_id).orElse(null);

        Double total = (double) 0;
        for(FlightBookingLink fbl : flightBooking.getFlightBookingLinks()){
            total += fbl.getFlight().getPrice();
        }
        payment.setAmount(total);
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWayRepository.findById(payment_way_id).orElse(null));
        return paymentRepository.save(payment);
    }
    
    
}