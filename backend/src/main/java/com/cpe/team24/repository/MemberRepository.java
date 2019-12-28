package com.cpe.team24.repository;

import com.cpe.team24.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface MemberRepository extends JpaRepository<Member,Long> {
    public Optional<Member> findById(Long id);
    public Optional<Member> findByName(String name);
}
