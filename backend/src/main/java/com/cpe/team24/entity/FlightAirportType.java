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
    private Collection<FlightAirport> flightAirports;

    public FlightAirportType(){}
    public FlightAirportType(EFlightAirportType name){
        this.name = name;
    }

    //Getter Setter
    public EFlightAirportType getName() {
        return name;
    }

    public void setName(EFlightAirportType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Collection<FlightAirport> getFlightAirports() {
        return flightAirports;
    }

    public void setFlightAirports(Collection<FlightAirport> flightAirports) {
        this.flightAirports = flightAirports;
    }
}
