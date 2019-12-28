package com.cpe.team24.repository;

import com.cpe.team24.entity.FightAirplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FightAirplaneRepository extends JpaRepository<FightAirplane,Long> {
    public Optional<FightAirplane> findById(Long id);
}
