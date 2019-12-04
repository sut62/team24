package com.cpe.backend.entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="FLIGHT")
public class Flight {

    @Id
    @SequenceGenerator(name="FLIGHT_seq",sequenceName="FLIGHT_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_seq")
    @Column(name = "FLIGHT_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="PRICE")
    private @NonNull Double price;

    @Column(name="DEPART")
    private @NonNull Date depart;

    @Column(name="ARRIVE")
    private @NonNull Date arrive;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airport.class)
    @JoinColumn(name = "AIRPORT_ID", insertable = true)
    private Airport Form;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airport.class)
    @JoinColumn(name = "AIRPORT_ID", insertable = true)
    private Airport To;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airplane.class)
    @JoinColumn(name = "AIRPLANE_ID", insertable = true)
    private Airplane Airplane;
}