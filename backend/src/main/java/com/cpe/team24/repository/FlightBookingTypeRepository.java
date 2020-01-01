package com.cpe.team24.repository;

import com.cpe.team24.entity.EFlightBookingType;
import com.cpe.team24.entity.FlightBookingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightBookingTypeRepository extends JpaRepository<FlightBookingType,Long> {
    public FlightBookingType findByName(EFlightBookingType name);
}
