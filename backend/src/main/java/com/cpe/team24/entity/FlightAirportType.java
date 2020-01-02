package com.cpe.team24.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class FlightAirportType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EFlightAirportType name;

    @JsonIgnore
    @OneToMany
    private Collection<FlightBookingLink> flightBookingLink;

    public FlightAirportType(){}
    public FlightAirportType(EFlightAirportType name){
        this.name = name;
    }
    public EFlightAirportType getName() {
        return name;
    }

    public void setName(EFlightAirportType name) {
        this.name = name;
    }
}
