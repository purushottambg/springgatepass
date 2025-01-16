package com.gatepass.repository;

import com.gatepass.models.HODEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HODRepo extends JpaRepository<HODEntity, Long> {
    public Optional<HODEntity> findByuserName(String userName);
}
