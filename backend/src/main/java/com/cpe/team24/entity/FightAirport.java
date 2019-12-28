package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
@Table(name="FIGHTAIRPORT")
public class FightAirport {
    @Id
    @SequenceGenerator(name="FIGHT_AIRPORT_SEQ",sequenceName="FIGHT_AIRPORT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIGHT_AIRPORT_SEQ")
    @Column(name="FIGHT_AIRPORT_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="NAME")
    private @NonNull String name;

//    @ManyToOne(fetch = FetchType.EAGER, targetEntity = FightCity.class)
//    @JoinColumn(name = "FIGHT_CITY_ID", insertable = true)
//    private FightCity city;

//    @OneToMany(fetch = FetchType.EAGER)
//    // mappedBy  = "from"
//    private Collection<Fight> flight;

}