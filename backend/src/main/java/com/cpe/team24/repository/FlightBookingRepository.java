package com.cpe.team24.repository;

import com.cpe.team24.entity.BookingStatus;
import com.cpe.team24.entity.FlightBooking;
import com.cpe.team24.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.Optional;

@RepositoryRestResource
public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {
    public Optional<FlightBooking> findById(Long id);
    public Optional<FlightBooking> findByBookId(String bookId);
    public Collection<FlightBooking> findAllByUser(User user);
    public Collection<FlightBooking> findAllByBookingStatus(BookingStatus lastname);
    //public Optional<FlightBooking> findByLastName(String lastname);
}

