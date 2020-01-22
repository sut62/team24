package com.cpe.team24.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private @NonNull String name;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    // mappedBy  = "city"
    private Collection<Airport> airports;

    // Getter Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}