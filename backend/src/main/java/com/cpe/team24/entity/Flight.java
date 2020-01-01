package com.cpe.team24.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name="FLIGHT")
public class Flight {

    @Id
    @SequenceGenerator(name="FLIGHT_seq",sequenceName="FLIGHT_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_seq")
    @Column(name = "FLIGHT_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="PRICE")
    private @NonNull Double price;

    @Column(name="DEPART")
    private @NonNull Date depart;

    @Column(name="ARRIVE")
    private @NonNull Date arrive;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private Collection<FlightBookingLink> flightBookingLinks;
    // ========== Add by JOKE ==========
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "FLIGHT_FLIGHTAIRPORT",
            joinColumns = @JoinColumn(name = "FLIGHT_ID"),
            inverseJoinColumns = @JoinColumn(name = "FLIGHT_AIRPORT_ID"))
    private Set<Airport> airports = new HashSet<>();

    public Set<Airport> getAirports() {
        return airports;
    }

    public void setRoles(Set<Airport> airports) {
        this.airports = airports;
    }
    // ========== End by JOKE ==========
    //Getter Setter
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDepart() {
        return depart;
    }

    public void setDepart(Date depart) {
        this.depart = depart;
    }

    public Date getArrive() {
        return arrive;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

//    @JsonIgnore
//    @OneToMany(fetch = FetchType.EAGER)
//    private Collection<FlightBooking> flightBookings;

//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FightAirport.class)
//    @JoinColumn(name = "FIGHT_AIRPORT_ID", insertable = true)
//    private FightAirport form;
//
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FightAirport.class)
//    @JoinColumn(name = "FIGHT_AIRPORT_ID", insertable = true)
//    private FightAirport to;
//
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FightAirplane.class)
//    @JoinColumn(name = "FIGHT_AIRPLANE_ID", insertable = true)
//    private FightAirplane airplane;
}