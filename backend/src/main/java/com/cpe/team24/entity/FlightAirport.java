package com.cpe.team24.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @JsonIgnore
    @ManyToOne
    private Flight flight;

    @NotNull
    @ManyToOne
    private FlightAirportType flightAirportType;

    @NotNull
    @ManyToOne
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