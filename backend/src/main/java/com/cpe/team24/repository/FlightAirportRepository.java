package com.cpe.team24.repository;

import com.cpe.team24.entity.FlightAirport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightAirportRepository extends JpaRepository<FlightAirport,Long> {

}
