package com.cpe.team24.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlightType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EFlightType name;

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
