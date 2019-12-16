package com.cpe.team24.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Data
@NoArgsConstructor
public class FlightBooking {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "FLIGHT_BOOKING_ID")
    private @NonNull Integer id;

    @Column(nullable = false)
    private String bookId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer departSeatId; //

    private Integer returnSeatId; //

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_STATUS_ID", nullable = false)
    private BookingStatus bookingStatus; //

    //Methods
    public void book(Integer departSeatId,Integer returnSeatId){
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