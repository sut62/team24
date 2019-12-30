package com.cpe.team24.repository;

import com.cpe.team24.entity.FAir;
import com.cpe.team24.entity.FlightAirport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FlightAirportRepository extends JpaRepository<FlightAirport,Long> {
    public Optional<FlightAirport> findByName(FAir name);
}
