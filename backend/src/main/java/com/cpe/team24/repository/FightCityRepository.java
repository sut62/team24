package com.cpe.team24.repository;

import com.cpe.team24.entity.FightCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface FightCityRepository extends JpaRepository<FightCity,Long> {
    public Optional<FightCity> findById(Long id);
}
