package com.cpe.team24.repository;

import com.cpe.team24.entity.BaggageType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BaggageTypeRepository extends JpaRepository<BaggageType, Long> {
	BaggageType findById(long id);
}