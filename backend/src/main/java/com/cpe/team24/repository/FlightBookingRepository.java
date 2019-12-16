package com.cpe.team24.repository;

import com.cpe.team24.entity.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {
}
