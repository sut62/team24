package  com.cpe.team24.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity
public class PaymentType {
    @Id
    @SequenceGenerator(name="PAYMENT_TYPE_SEQ",sequenceName="PAYMENT_TYPE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PAYMENT_TYPE_SEQ")
    @Column(name="PAYMENT_TYPE_ID",unique = true, nullable = true)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "paymentType")
    private Collection<PaymentWay> paymentWays;

    public PaymentType(){}
    public PaymentType(String name){
        this.name = name;
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

    public Collection<PaymentWay> getPaymentWays() {
        return this.paymentWays;
    }

    public void setPaymentWays(Collection<PaymentWay> paymentWays) {
        this.paymentWays = paymentWays;
    }

}