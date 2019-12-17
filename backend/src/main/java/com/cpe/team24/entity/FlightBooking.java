package com.cpe.team24.entity;

import java.sql.Timestamp;
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
    @SequenceGenerator(name="FLIGHT_BOOKING_SEQ",sequenceName="FLIGHT_BOOKING_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_BOOKING_SEQ")
    @Column(name = "FLIGHT_BOOKING_ID",unique = true,nullable = false)
    private @NonNull Integer id;

    @Column(nullable = false)
    private String bookId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer departSeatId; //

    private Integer returnSeatId; //

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Flight.class)
    @JoinColumn(name = "FLIGHT_ID", nullable = false)
    private Flight departFlight; //

//    @ManyToOne(fetch = FetchType.EAGER,targetEntity = Flight.class)
//    @JoinColumn(name = "FLIGHT_ID", nullable = true)
//    private Flight returnFlight; //

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = BookingStatus.class)
    @JoinColumn(name = "BOOKING_STATUS_ID", nullable = false, insertable = true)
    private BookingStatus bookingStatus; //

    //Methods
    public void book(Flight departFlight,Flight returnFlight,Integer departSeatId,Integer returnSeatId ){
        this.departFlight = departFlight;
//        this.returnFlight = returnFlight;
        this.departSeatId = departSeatId;
        this.returnSeatId = returnSeatId;
        this.bookId = "BXXXXX"; // To do
        this.date = new Date();
    }
    //Getter Setter
    public Integer getDepartSeatId() {
        return departSeatId;
    }

    public void setDepartSeatId(Integer departSeatId) {
        this.departSeatId = departSeatId;
    }

    public Integer getReturnSeatId() {
        return returnSeatId;
    }

    public void setReturnSeatId(Integer returnSeatId) {
        this.returnSeatId = returnSeatId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}