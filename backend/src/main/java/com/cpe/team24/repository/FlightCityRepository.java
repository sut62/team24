package com.cpe.team24.repository;

import com.cpe.team24.entity.FlightCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FlightCityRepository extends JpaRepository<FlightCity,Long> {
    public Optional<FlightCity> findById(Long id);
}
