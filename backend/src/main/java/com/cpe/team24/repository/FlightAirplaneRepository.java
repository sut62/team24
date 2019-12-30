package com.cpe.team24.repository;

import com.cpe.team24.entity.FlightAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FlightAirplaneRepository extends JpaRepository<FlightAirplane,Long> {
    public Optional<FlightAirplane> findById(Long id);
}
