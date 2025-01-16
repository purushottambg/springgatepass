package com.gatepass.repository;

import com.gatepass.models.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepo extends JpaRepository<MembershipEntity, Long> {
    Optional<MembershipEntity> findByUsername(String username);
}