package com.cpe.team24.entity;

import lombok.*;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="BAGGAGE")
public class BaggageAddon {

    @Id
    @SequenceGenerator(name="BAGGAGE_SEQ",sequenceName="BAGGAGE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BAGGAGE_SEQ")
    @Column(name = "BAGGAGE_ID", unique = true, nullable = true)
    private @NonNull Long id;
    @Column(name="baggage")

    private @NonNull String maxweight;

    private @NonNull String price;


   /* @OneToOne(cascade = CascadeType.ALL, targetEntity = BaggageImage.class)
    @JoinColumn(name = "BAGGAGEIMAGE_ID", referencedColumnName = "BAGGAGEIMAGE_ID",unique = true)
    private BaggageImage addbaggageimage;*/

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BaggageType.class)
    @JoinColumn(name = "BAGGAGETYPE_ID", insertable = true)
    private  BaggageType addbaggagetype;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BaggageImage.class)
    @JoinColumn(name = "BAGGAGEIMAGE_ID", insertable = true)
    private  BaggageImage addbaggageimage;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airport.class)
    @JoinColumn(name = "FIGHT_CITY_ID", insertable = true)
    private City addflightairports;

}