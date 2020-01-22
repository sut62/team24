package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
@Table(name="AIRPLANE")
public class Airplane {
    @Id
    @SequenceGenerator(name="AIRPLANE_SEQ",sequenceName="AIRPLANE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AIRPLANE_SEQ")
    @Column(name="AIRPLANE_ID",unique = true, nullable = true)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private Integer seatAmout;


    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "airplane"
    private Collection<Flight> flight;

    //Getter Setter
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


    public Integer getSeatAmout() {
    	return this.seatAmout;
    }
    public void setSeatAmout(Integer seatAmout) {
    	this.seatAmout = seatAmout;
    }
}