package com.cpe.team24.repository;

import com.cpe.team24.entity.Airport;
import com.cpe.team24.entity.EFlightAirportType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AirportRepository extends JpaRepository<Airport,Long> {
    public Optional<Airport> findById(Long id);
}
