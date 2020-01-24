package com.cpe.team24.entity;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;


@Data
@Entity
@NoArgsConstructor
public class CheckIn {

    @Id
    @SequenceGenerator(name = "CHECKIN_SEQ", sequenceName = "CHECKIN_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHECKIN_SEQ")
    @Column(name = "CHECKIN_ID", unique = true, nullable = true)
    private Long id;

    @NotNull
    private Date date;

    @NotNull
    @Email
    private String email;
    
    @NotNull 
    @Size(min=6,max=10)
    @Pattern(regexp = "[A-Z0-9]*")
    @Column(unique = true)
    private String boardingPass;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CheckInStatus.class)
    @JoinColumn(name = "CHECKINSTATUS_ID", insertable = true)
    @NotNull
    private CheckInStatus checkInStatus;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CheckInType.class)
    @JoinColumn(name = "CHECKINTYPE_ID", insertable = true)
    @NotNull
    private CheckInType checkInType;

    @NotNull
    @OneToOne
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
