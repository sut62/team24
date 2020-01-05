package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@CrossOrigin(origins = "*")
@Data
@Entity
@NoArgsConstructor
@Table(name = "CHECKIN")
public class CheckIn {

    @Id
    @SequenceGenerator(name = "CHECKIN_SEQ", sequenceName = "CHECKIN_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_SEQ")
    @Column(name = "CHECKIN_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "Date")
    private @NonNull Date date;

    @Column(name = "Email")
    private @NonNull String email;

    @Column(name = "BoardingPass")
    private @NonNull String boardingPass;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CheckInStatus.class)
    @JoinColumn(name = "CHECKINSTATUS_ID", insertable = true)
    private @NonNull CheckInStatus checkInStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CheckInType.class)
    @JoinColumn(name = "CHECKINTYPE_ID", insertable = true)
    private @NonNull CheckInType checkInType;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = FlightBooking.class)
    @JoinColumn(name = "FLIGHT_BOOKING_ID", referencedColumnName = "FLIGHT_BOOKING_ID",unique = true)
    private FlightBooking flightBooking;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBoardingPass() {
        return this.boardingPass;
    }

    public void setBoardingPass(String boardingPass) {
        this.boardingPass = boardingPass;
    }

    public CheckInStatus getCheckInStatus() {
        return this.checkInStatus;
    }

    public void setCheckInStatus(CheckInStatus checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public CheckInType getCheckInType() {
        return this.checkInType;
    }

    public void setCheckInType(CheckInType checkInType) {
        this.checkInType = checkInType;
    }

    public FlightBooking getFlightBooking() {
        return this.flightBooking;
    }

    public void setFlightBooking(FlightBooking flightBooking) {
        this.flightBooking = flightBooking;
    }



}
