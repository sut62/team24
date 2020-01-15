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
public class FlightBookingLink {
    @Id
    @SequenceGenerator(name="FLIGHT_BOOKING_LINK_SEQ",sequenceName="FLIGHT_BOOKING_LINK_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_BOOKING_LINK_SEQ")
    @Column(name = "FLIGHT_BOOKING_LINK_ID",unique = true,nullable = false)
    private @NonNull Long id;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Flight.class)
    @JoinColumn(name = "FLIGHT_ID",nullable = false)
    @NotNull
    private Flight flight;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,targetEntity = FlightBooking.class)
    @JoinColumn(name = "FLIGHT_BOOKING_ID")
    @NotNull
    private FlightBooking flightBooking;

    @ManyToOne
    @NotNull
    private FlightBookingType flightBookingType;

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


    public FlightBookingType getFlightBookingType() {
        return flightBookingType;
    }

    public void setFlightBookingType(FlightBookingType flightBookingType) {
        this.flightBookingType = flightBookingType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}