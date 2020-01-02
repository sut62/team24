package com.cpe.team24.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class FlightAirport {
    @Id
    @SequenceGenerator(name="FLIGHT_AIRPORT_SEQ",sequenceName="FLIGHT_AIRPORT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_AIRPORT_SEQ")
    @Column(name = "FLIGHT_AIRPORT_ID",unique = true,nullable = false)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Flight.class)
    @JoinColumn(name = "FLIGHT_ID",nullable = false)
    private Flight flight;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = FlightAirportType.class)
    @JoinColumn(name = "FLIGHT_AIRPORT_TYPE_ID",nullable = false)
    private FlightAirportType flightAirportType;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Airport.class)
    @JoinColumn(name = "AIRPORT_ID",nullable = false)
    private Airport airport;

    //GETTER SETTER
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public FlightAirportType getFlightAirportType() {
        return flightAirportType;
    }

    public void setFlightAirportType(FlightAirportType flightAirportType) {
        this.flightAirportType = flightAirportType;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
}