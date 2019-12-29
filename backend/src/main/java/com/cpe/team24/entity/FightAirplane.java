package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
@Table(name="FIGHTAIRPLANE")
public class FightAirplane {
    @Id
    @SequenceGenerator(name="FIGHT_AIRPLANE_SEQ",sequenceName="FIGHT_AIRPLANE_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIGHT_AIRPLANE_SEQ")
    @Column(name="FIGHT_AIRPLANE_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name = "NAME")
    private @NonNull String name;

    @Column(name = "SEATAMOUNT")
    private @NonNull String seatAmout;

//    @OneToMany(fetch = FetchType.EAGER)
//    // mappedBy  = "airplane"
//    private Collection<Flight> flight;
}