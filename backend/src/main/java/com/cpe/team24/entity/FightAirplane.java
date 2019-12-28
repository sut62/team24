package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
@Entity
@NoArgsConstructor
@Table(name="FIGHT_AIRPLANE")
public class FightAirplane {
    @Id
    @SequenceGenerator(name="FIGHT_AIRPLANE_SEQ",sequenceName="FIGHT_AIRPLANE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIGHT_AIRPLANE_SEQ")
    @Column(name="FIGHT_AIRPLANE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "NAME",nullable = false)
    private @NonNull String name;

    @Column(name = "SEATAMOUNT",nullable = false)
    private @NonNull String seatamout;

//    @OneToMany(fetch = FetchType.EAGER)
//    // mappedBy  = "airplane"
//    private Collection<Flight> flight;
}