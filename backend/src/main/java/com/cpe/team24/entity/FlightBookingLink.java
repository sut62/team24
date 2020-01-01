package com.cpe.team24.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class FlightBookingLink {
    @Id
    @SequenceGenerator(name="FLIGHT_BOOKING_LINK_SEQ",sequenceName="FLIGHT_BOOKING_LINK_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_BOOKING_LINK_SEQ")
    @Column(name = "FLIGHT_BOOKING_LINK_ID",unique = true,nullable = false)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Flight.class)
    @JoinColumn(name = "FLIGHT_ID",nullable = false)
    private Flight flight;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = FlightBooking.class)
    @JoinColumn(name = "FLIGHT_BOOKING_ID",nullable = false)
    private FlightBooking flightBooking;

    @ManyToOne
    private FlightType flightType;

    //GETTER SETTER
    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public FlightBooking getFlightBooking() {
        return flightBooking;
    }

    public void setFlightBooking(FlightBooking flightBooking) {
        this.flightBooking = flightBooking;
    }


    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }
}