package com.cpe.team24.repository;

import com.cpe.team24.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CityRepository extends JpaRepository<City,Long> {
    public Optional<City> findById(Long id);
}
