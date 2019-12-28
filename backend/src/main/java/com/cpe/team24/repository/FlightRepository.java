package com.cpe.team24.repository;

import com.cpe.team24.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findAllByDepartBetween(Date departDayStart,Date departDayEnd);
}
