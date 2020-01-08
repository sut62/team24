package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;


@Data
@Entity
@NoArgsConstructor
public class Payment {

    @Id
    @SequenceGenerator(name="payment_seq",sequenceName="payment_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_seq")
    private @NonNull Long id;

    private @NonNull Date payDate;

    private @NonNull Double amount;

    @ManyToOne
    private PaymentWay paymentWay;
    
    @OneToOne
    private FlightBooking flightBooking;

    @ManyToOne
    private PromotionCode promotionCode;

    //Getter Setter
    public PromotionCode getPromotionCode() {
        return this.promotionCode;
    }

    public void setPromotionCode(PromotionCode promotionCode) {
        this.promotionCode = promotionCode;
    }

    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPayDate() {
        return this.payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentWay getPaymentWay() {
        return this.paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }

    public FlightBooking getFlightBooking() {
        return this.flightBooking;
    }

    public void setFlightBooking(FlightBooking flightBooking) {
        this.flightBooking = flightBooking;
    }

}