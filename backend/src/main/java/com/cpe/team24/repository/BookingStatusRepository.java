package com.cpe.team24.repository;

import com.cpe.team24.entity.BookingStatus;
import com.cpe.team24.entity.EBookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BookingStatusRepository extends JpaRepository<BookingStatus,Long> {
    public BookingStatus findByName(EBookingStatus name);
}
