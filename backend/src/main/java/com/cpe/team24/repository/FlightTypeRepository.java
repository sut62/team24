package com.cpe.team24.repository;

import com.cpe.team24.entity.EFlightType;
import com.cpe.team24.entity.FlightType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightTypeRepository extends JpaRepository<FlightType,Long> {
    public FlightType findByName(EFlightType name);
}
