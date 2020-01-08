package com.cpe.team24.repository;

import com.cpe.team24.entity.PromotionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PromotionCodeRepository extends JpaRepository<PromotionCode,Long> {
    public PromotionCode findByCode(String code);
}
