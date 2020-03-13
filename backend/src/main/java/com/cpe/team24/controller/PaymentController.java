package com.cpe.team24.controller;

import java.util.Date;
import java.util.Collection;

import com.cpe.team24.entity.*;
import com.cpe.team24.repository.*;

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
    @Autowired
    private PromotionCodeRepository promotionCodeRepository;
    @Autowired
    private BookingStatusRepository bookingStatusRepository;
    @Autowired
    private FlightBookingTypeRepository flightBookingTypeRepository;
    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("")
    public Collection<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
    @GetMapping("{id}")
    public Payment makeBill(@PathVariable Long id){
        return paymentRepository.findById(id).get();
    }
    @PostMapping("/{flight_booking_id}/{payment_way_id}/{promotion_code}/{phone}/{email}")
    public Payment payFlight(@PathVariable Long flight_booking_id,@PathVariable Long payment_way_id,@PathVariable String promotion_code,@PathVariable String phone,@PathVariable String email ) throws Exception{
        Payment payment = new Payment();

        FlightBooking flightBooking = flightBookingRepository.findById(flight_booking_id).orElse(null);
        PaymentWay paymentWay = paymentWayRepository.findById(payment_way_id).orElse(null);
        PromotionCode promotionCode = promotionCodeRepository.findByCode(promotion_code);

        System.out.print(promotionCode);
        payment.setPayDate(new Date());
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPromotionCode(promotionCode);
        payment.setPhone(phone);
        payment.setEmail(email);
        try{
            this.submitBooking(flightBooking);
        }catch (Exception ex){
            throw ex;
        }

        Double total = (double) 0;
        for(FlightBookingLink fbl : flightBooking.getFlightBookingLinks()){
            total += fbl.getFlight().getPrice();
            if(fbl.getBaggageAddon() != null){
                total += fbl.getBaggageAddon().getPrice();
            }
        }
        total -= (double) promotionCode.getDiscount()/100.0 * total;
        payment.setAmount(total);
        return paymentRepository.save(payment);
    }
    
    void submitBooking(FlightBooking flightBooking) throws Exception{
        flightBooking.setBookingStatus(bookingStatusRepository.findByName(EBookingStatus.SUBMIT));
        try {
            setSeat(flightBooking);
        }catch (Exception ex){
            throw ex;
        }
        flightBookingRepository.save(flightBooking);
    }
    /**
     * method สำหรับจัดสรรที่นั่ง
     */
    void setSeat(FlightBooking flightBooking) throws Exception{
        Integer departSeatId = 0;
        Integer returnSeatId = 0;
        Collection<FlightBookingLink> flightBookingLinks = flightBooking.getFlightBookingLinks();
        for(FlightBookingLink fl : flightBookingLinks){
            if(fl.getFlightBookingType().getName() == EFlightBookingType.DEPART_FLIGHT){
                Flight departFlight = fl.getFlight();
                departSeatId = departFlight.getLastSeatId() + 1;
                if(departSeatId <= departFlight.getAirplane().getSeatAmout()){
                    departFlight.setLastSeatId(departSeatId);
                    flightRepository.save(departFlight);
                }else{
                    throw new Exception("Depart Flight's seat not available");
                }
            }else if(fl.getFlightBookingType().getName() == EFlightBookingType.RETURN_FLIGHT){
                Flight returnFlight = fl.getFlight();
                returnSeatId = returnFlight.getLastSeatId() + 1;
                if(departSeatId <= returnFlight.getAirplane().getSeatAmout()){
                    returnFlight.setLastSeatId(returnSeatId);
                    flightRepository.save(returnFlight);
                }else{
                    throw new Exception("Return Flight's seat not available");
                }
            }
        }
        flightBooking.setDepartSeatId(departSeatId);
        flightBooking.setReturnSeatId(returnSeatId);
    }

    @PostMapping("/{flight_booking_id}/{payment_way_id}/{phone}/{email}")
    public Payment payFlightWithoutCode(@PathVariable Long flight_booking_id,@PathVariable Long payment_way_id,@PathVariable String phone,@PathVariable String email ) throws Exception{
        Payment payment = new Payment();

        FlightBooking flightBooking = flightBookingRepository.findById(flight_booking_id).orElse(null);
        PaymentWay paymentWay = paymentWayRepository.findById(payment_way_id).orElse(null);

        payment.setPayDate(new Date());
        payment.setFlightBooking(flightBooking);
        payment.setPaymentWay(paymentWay);
        payment.setPhone(phone);
        payment.setEmail(email);
        try{
            this.submitBooking(flightBooking);
        }catch (Exception ex){
            throw ex;
        }

        
        Double total = (double) 0;
        for(FlightBookingLink fbl : flightBooking.getFlightBookingLinks()){
            total += fbl.getFlight().getPrice();
            if(fbl.getBaggageAddon() != null){
                total += fbl.getBaggageAddon().getPrice();
            }
        }
        payment.setAmount(total);
        return paymentRepository.save(payment);
    }
    
}