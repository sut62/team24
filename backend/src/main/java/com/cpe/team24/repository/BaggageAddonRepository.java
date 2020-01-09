package com.cpe.team24.repository;

import com.cpe.team24.entity.Airport;
import com.cpe.team24.entity.BaggageAddon;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public
interface BaggageAddonRepository extends JpaRepository<BaggageAddon, Long> {
    BaggageAddon findById(long id);
    Collection<BaggageAddon> findAllByAirport(Airport airport);
    void deleteById(Long id);
}