package com.cpe.team24.repository;

import com.cpe.team24.entity.EFlightAirportType;
import com.cpe.team24.entity.FlightAirportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightAirportTypeRepository extends JpaRepository<FlightAirportType,Long> {
    public FlightAirportType findByName(EFlightAirportType name);
}
