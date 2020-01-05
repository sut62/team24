package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
@Table(name="PAYMENT_WAY")
public class PaymentWay {
    @Id
    @SequenceGenerator(name="PAYMENT_WAY_SEQ",sequenceName="PAYMENT_WAY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_WAY_SEQ")
    @Column(name="PAYMENT_WAY_ID",unique = true, nullable = true)
    private @NonNull Long id;

    private @NonNull String name;

    @ManyToOne
    private PaymentType paymentType;
    
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