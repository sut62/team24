package com.cpe.team24.entity;

import lombok.*;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@Entity
@NoArgsConstructor
public class BaggageAddon {

    @Id
    @SequenceGenerator(name="BAGGAGE_SEQ",sequenceName="BAGGAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BAGGAGE_SEQ")
    @Column(name = "BAGGAGE_ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    @PositiveOrZero
    @Max(10000)
    private Integer maxWeight;
 

    @NotNull
    @PositiveOrZero
    @Max(100000)
    private Double price;


   /* @OneToOne(cascade = CascadeType.ALL, targetEntity = BaggageImage.class)
    @JoinColumn(name = "BAGGAGEIMAGE_ID", referencedColumnName = "BAGGAGEIMAGE_ID",unique = true)
    private BaggageImage addbaggageimage;*/

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BaggageType.class)
    @JoinColumn(name = "BAGGAGETYPE_ID", insertable = true)
    private  BaggageType baggageType;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BaggageImage.class)
    @JoinColumn(name = "BAGGAGEIMAGE_ID", insertable = true)
    private  BaggageImage baggageImage;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airport.class)
    @JoinColumn(name = "AIRPORT_ID", insertable = true)
    private Airport airport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Integer maxweight) {
        this.maxWeight = maxweight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BaggageType getBaggageType() {
        return baggageType;
    }

    public void setBaggageType(BaggageType addbaggagetype) {
        this.baggageType = addbaggagetype;
    }

    public BaggageImage getBaggageImage() {
        return baggageImage;
    }

    public void setBaggageImage(BaggageImage addbaggageimage) {
        this.baggageImage = addbaggageimage;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport addflightairports) {
        this.airport = addflightairports;
    }
}