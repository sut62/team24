package com.cpe.team24.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

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

//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airport.class)
//    @JoinColumn(name = "AIRPORT_ID", insertable = true)
//    private Airport Form;
//
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airport.class)
//    @JoinColumn(name = "AIRPORT_ID", insertable = true)
//    private Airport To;
//
//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Airplane.class)
//    @JoinColumn(name = "AIRPLANE_ID", insertable = true)
//    private Airplane Airplane;
}