package com.gatepass.repository;

import com.gatepass.models.ClerkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClerkRepo extends JpaRepository<ClerkEntity, Long> {
    public Optional<ClerkEntity> findByUserName(String UserName);
}
