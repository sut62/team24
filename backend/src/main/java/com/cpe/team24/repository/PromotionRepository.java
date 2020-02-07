package com.cpe.team24.repository;

import java.util.Optional;

import com.cpe.team24.entity.Promotion;
import com.cpe.team24.entity.PromotionCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PromotionRepository extends JpaRepository<Promotion,Long> {
    public Promotion findByPromotionCode(PromotionCode promotionCode);
    public Optional<Promotion> findById(Long id);
}
