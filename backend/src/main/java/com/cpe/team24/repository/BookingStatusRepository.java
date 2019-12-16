package com.cpe.team24.repository;

import com.cpe.team24.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface BookingStatusRepository extends JpaRepository<BookingStatus,Long> {
    public Optional<BookingStatus> findById(Integer id);
}
