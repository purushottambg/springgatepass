package com.gatepass.repository;

import com.gatepass.models.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepo extends JpaRepository<MembershipEntity, Long> {
    Optional<MembershipEntity> findByUsername(String username);
}