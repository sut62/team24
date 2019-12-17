package com.cpe.team24.repository;

import com.cpe.team24.entity.FlightBookingLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FlightBookingLinkRepository extends JpaRepository<FlightBookingLink,Long> {

}
