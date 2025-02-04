package com.gatepass.repository;

import com.gatepass.models.PassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassesRepo  extends JpaRepository<PassEntity, Long> {

}
