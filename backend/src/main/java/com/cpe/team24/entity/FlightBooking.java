package com.cpe.team24.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.bytebuddy.utility.RandomString;

@Entity
@Data
@NoArgsConstructor
public class FlightBooking {
    @Id
    @SequenceGenerator(name="FLIGHT_BOOKING_SEQ",sequenceName="FLIGHT_BOOKING_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_BOOKING_SEQ")
    @Column(name = "FLIGHT_BOOKING_ID",unique = true,nullable = false)
    private @NonNull Long id;

    @Column(nullable = false,unique = true)
    private String bookId;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Integer departSeatId; //

    private Integer returnSeatId; //

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "flightBooking")
    private Collection<FlightBookingLink> flightBookingLinks;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = BookingStatus.class)
    @JoinColumn(name = "BOOKING_STATUS_ID", nullable = false, insertable = true)
    private BookingStatus bookingStatus; //

    @ManyToOne
    private User user; //

    //Methods
    public void book(Integer departSeatId,Integer returnSeatId){
        this.departSeatId = departSeatId;
        this.returnSeatId = returnSeatId;
        this.bookId = RandomString.make(6).toUpperCase();
        this.date = new Date();
    }

    //Getter Setter
    public @NonNull Long getId(){
        return this.id;
    }
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}