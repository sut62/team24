package com.cpe.team24.repository;

import com.cpe.team24.entity.CheckInType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CheckInTypeRepository extends JpaRepository<CheckInType,Long> {
    public Optional<CheckInType> findById(Long id);
}
