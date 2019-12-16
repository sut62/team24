package com.cpe.team24.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class BookingStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "BOOKING_STATUS_ID")
    private @NonNull Integer id;

    private String name;

//    @OneToMany(
//        mappedBy = "bookingStatus",cascade = CascadeType.ALL,fetch = FetchType.LAZY
//    )
//    private Collection<FlightBooking> flightBookings;

    //getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}