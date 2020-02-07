package com.cpe.team24.repository;

import com.cpe.team24.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CheckInRepository extends JpaRepository<CheckIn,Long> {
    public Optional<CheckIn> findById(Long id);
    public CheckIn findFirstByOrderByIdDesc();
}
