package com.cpe.team24.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Collection;


@Data
@Entity
@NoArgsConstructor
public class City {
    @Id
    @SequenceGenerator(name="FLIGHT_CITY_SEQ",sequenceName="FLIGHT_CITY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FLIGHT_CITY_SEQ")
    @Column(name="FLIGHT_CITY_ID",unique = true, nullable = true)
    private @NonNull Long id;

    @Column(name="NAME")
    private @NonNull String name;

	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   @OneToMany(fetch = FetchType.EAGER)
   // mappedBy  = "city"
   private Collection<Airport> airports;
}