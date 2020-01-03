package com.cpe.team24.repository;

import com.cpe.team24.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    Optional<User> findByLastName(String lastname);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
