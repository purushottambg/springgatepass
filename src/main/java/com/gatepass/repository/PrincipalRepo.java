package com.gatepass.repository;

import com.gatepass.models.PrincipalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PrincipalRepo extends JpaRepository<PrincipalEntity, Long> {
    public Optional<PrincipalEntity> findByUserName(String userName);
}
