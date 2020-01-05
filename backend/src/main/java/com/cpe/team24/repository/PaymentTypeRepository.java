package com.cpe.team24.repository;

import java.util.Optional;

import com.cpe.team24.entity.PaymentType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PaymentTypeRepository extends JpaRepository<PaymentType,Long> {
    public Optional<PaymentType> findById(Long id);
}
