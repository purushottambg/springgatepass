package com.gatepass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gatepass.models.MembershipEntity;

import java.util.List;
import java.util.Optional;

public interface MembershipRepo extends JpaRepository<MembershipEntity, Long> {
    Optional<MembershipEntity> findByUsername(String username);
    List<MembershipEntity> findByAppid(Long appid);
}