package com.cpe.team24.entity;

import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
public class BookingStatus {
    @Id
    @SequenceGenerator(name="BOOKING_STATUS_SEQ",sequenceName="BOOKING_STATUS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOOKING_STATUS_SEQ")
    @Column(name = "BOOKING_STATUS_ID",unique = true, nullable = true)
    private @NonNull Integer id;

    private EBookingStatus name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<FlightBooking> flightBookings;

    public BookingStatus(){}
    public BookingStatus(EBookingStatus name){
        this.name = name;
    }
    //getter setter

    public EBookingStatus getName() {
        return name;
    }

    public void setName(EBookingStatus name) {
        this.name = name;
    }
}