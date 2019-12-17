package com.cpe.team24.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class BookingStatus {
    @Id
    @SequenceGenerator(name="BOOKING_STATUS_SEQ",sequenceName="BOOKING_STATUS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BOOKING_STATUS_SEQ")
    @Column(name = "BOOKING_STATUS_ID",unique = true, nullable = true)
    private @NonNull Integer id;

    private String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Collection<FlightBooking> flightBookings;

    //getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}