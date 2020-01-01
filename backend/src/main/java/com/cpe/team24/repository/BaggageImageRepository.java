package com.cpe.team24.repository;

import com.cpe.team24.entity.BaggageImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public
interface BaggageImageRepository extends JpaRepository<BaggageImage, Long> {
    BaggageImage findById(long id);
}