package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
@Table(name="FIGHTCITY")
public class FightCity {
    @Id
    @SequenceGenerator(name="FIGHT_CITY_SEQ",sequenceName="FIGHT_CITY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FIGHT_CITY_SEQ")
    @Column(name="FIGHT_CITY_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="NAME")
    private @NonNull String name;

	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    


//    @OneToMany(fetch = FetchType.EAGER)
//    // mappedBy  = "city"
//    private Collection<FightAirport> airport;
}