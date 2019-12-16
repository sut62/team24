package com.cpe.team24.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class FlightBooking {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "FLIGHT_BOOKING_ID")
    private @NonNull Integer id;

    @Column(length = 6)
    private String bookId;

    private @NonNull Date date;

    @Column(length = 6)
    private String departSeatId;

    @Column(length = 6)
    private String returnSeatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_STATUS_ID", nullable = false)
    private BookingStatus bookingStatus;
}