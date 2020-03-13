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
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"FLIGHT_BOOKING_ID", "FLIGHT_BOOKING_TYPE_ID"})
)
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
    private BaggageAddon baggageAddon;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "FLIGHT_BOOKING_TYPE_ID")
    private FlightBookingType flightBookingType;

    //Method
    @PrePersist
    public void prePersist() throws Exception{
        Integer tempSeatId = flight.getLastTempSeatId() + 1;
        if(tempSeatId <= flight.getAirplane().getSeatAmout()){
            flight.setLastTempSeatId(tempSeatId);
        }else{
            if(flightBookingType.getName() == EFlightBookingType.DEPART_FLIGHT){
                throw new Exception("Depart flight's seat not available");
            }else{
                throw new Exception("Return flight's seat not available");
            }
        }
    }

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

    public BaggageAddon getBaggageAddon() {
        return baggageAddon;
    }

    public void setBaggageAddon(BaggageAddon baggageAddon) {
        this.baggageAddon = baggageAddon;
    }
}