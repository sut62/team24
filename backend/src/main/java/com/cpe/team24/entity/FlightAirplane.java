package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
@Table(name="FLIGHTAIRPLANE")
public class FlightAirplane {
    @Id
    @SequenceGenerator(name="FIGHT_AIRPLANE_SEQ",sequenceName="FLIGHT_AIRPLANE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_AIRPLANE_SEQ")
    @Column(name="FLIGHT_AIRPLANE_ID",unique = true, nullable = true)
    private Long id;
    private String name;
    private String seatAmout;

    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }


    public String getSeatAmout() {
    	return this.seatAmout;
    }
    public void setSeatAmout(String seatAmout) {
    	this.seatAmout = seatAmout;
    }


//    @OneToMany(fetch = FetchType.EAGER)
//    // mappedBy  = "airplane"
//    private Collection<Flight> flight;
}