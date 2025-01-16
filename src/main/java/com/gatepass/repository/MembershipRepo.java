package com.gatepass.repository;

import com.gatepass.models.MembershipRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepo extends JpaRepository<MembershipRequest , Long> {
    Optional<MembershipRequest> findByUsername(String username);
}