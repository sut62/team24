package com.cpe.team24.repository;

import com.cpe.team24.entity.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface AirplaneRepository extends JpaRepository<Airplane,Long> {
    public Optional<Airplane> findById(Long id);
}
