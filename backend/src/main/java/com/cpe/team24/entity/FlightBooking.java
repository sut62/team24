package com.cpe.team24.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Entity
@Data
@NoArgsConstructor
public class FlightBooking {
    @Id
    @SequenceGenerator(name="FLIGHT_BOOKING_SEQ",sequenceName="FLIGHT_BOOKING_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_BOOKING_SEQ")
    @Column(name = "FLIGHT_BOOKING_ID",unique = true,nullable = false)
    private Long id;

    @Size(min=6,max=10)
    @Pattern(regexp = "[A-Z0-9]*")
    @Column(unique = true)
    @NotNull
    private String bookId;

    @NotNull
    private Date date;

    @NotNull
    private Integer departSeatId = 0;

    @NotNull
    private Integer returnSeatId = 0;

    @OneToMany(mappedBy = "flightBooking")
    private Collection<FlightBookingLink> flightBookingLinks;

    @ManyToOne(fetch = FetchType.EAGER,targetEntity = BookingStatus.class)
    @JoinColumn(name = "BOOKING_STATUS_ID", insertable = true)
    @NotNull
    private BookingStatus bookingStatus; 

    @ManyToOne
    @NotNull
    private User user; 

    @OneToOne
    private Payment payment;

    @OneToOne
    private CheckIn checkIn;

    //Methods
    public void book(Integer departSeatId,Integer returnSeatId){
        this.departSeatId = departSeatId;
        this.returnSeatId = returnSeatId;
        this.bookId = RandomString.make(6).toUpperCase();
        this.setDate(new Date());
    }

    //Getter Setter
    public Long getId(){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<FlightBookingLink> getFlightBookingLinks() {
        return flightBookingLinks;
    }

    public void setFlightBookingLinks(Collection<FlightBookingLink> flightBookingLinks) {
        this.flightBookingLinks = flightBookingLinks;
    }
}