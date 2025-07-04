package com.gatepass.repository;

import com.gatepass.models.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepo extends JpaRepository<StaffEntity, Long> {

    Optional<StaffEntity> findByUsername(String username);
    boolean existsByUsername(String username);


}
