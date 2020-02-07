package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private Long id;

    @NotNull
    private Date payDate;

    @NotNull
    private Double amount;

    @NotNull
    @Size(min=10 ,max=10)
    @Pattern(regexp = "[0-9]*")
    private String phone;

    @NotNull
    @Email
    private String email;

    @ManyToOne
    @NotNull
    private PaymentWay paymentWay;
    
    @OneToOne
    @NotNull
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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}