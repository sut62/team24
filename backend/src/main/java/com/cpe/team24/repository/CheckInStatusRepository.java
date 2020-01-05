package com.cpe.team24.repository;

import com.cpe.team24.entity.CheckInStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CheckInStatusRepository extends JpaRepository<CheckInStatus,Long> {
    public Optional<CheckInStatus> findById(Long id);
}
