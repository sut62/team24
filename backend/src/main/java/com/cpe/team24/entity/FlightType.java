package com.cpe.team24.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class FlightType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EFlightType name;

    @JsonIgnore
    @OneToMany
    private Collection<FlightBookingLink> flightBookingLink;

    public FlightType(){}
    public FlightType(EFlightType name){
        this.name = name;
    }
    public EFlightType getName() {
        return name;
    }

    public void setName(EFlightType name) {
        this.name = name;
    }
}
