package com.cpe.team24.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class FlightBookingType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EFlightBookingType name;

    @JsonIgnore
    @OneToMany
    private Collection<FlightBookingLink> flightBookingLink;

    public FlightBookingType(){}
    public FlightBookingType(EFlightBookingType name){
        this.name = name;
    }
    public EFlightBookingType getName() {
        return name;
    }

    public void setName(EFlightBookingType name) {
        this.name = name;
    }
}
