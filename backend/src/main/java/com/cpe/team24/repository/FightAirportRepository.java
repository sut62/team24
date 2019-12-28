package com.cpe.team24.repository;

import com.cpe.team24.entity.FightAirport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FightAirportRepository extends JpaRepository<FightAirport,Long> {
    public Optional<FightAirport> findById(Long id);
}
