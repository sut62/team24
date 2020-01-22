package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public class PaymentWay {
    @Id
    @SequenceGenerator(name="PAYMENT_WAY_SEQ",sequenceName="PAYMENT_WAY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_WAY_SEQ")
    @Column(name="PAYMENT_WAY_ID",unique = true, nullable = true)
    private Long id;

    @NotNull
    private String name;

    @JsonIgnore
    @ManyToOne
    private PaymentType paymentType;
    
    @JsonIgnore
    @OneToMany
    private Collection<Payment> payments;

    public PaymentWay(){}
    
    public PaymentWay(String name,PaymentType paymentType){
        this.name = name;
        this.paymentType = paymentType;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Collection<Payment> getPayments() {
        return this.payments;
    }

    public void setPayments(Collection<Payment> payments) {
        this.payments = payments;
    }


}