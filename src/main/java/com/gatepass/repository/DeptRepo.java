package com.gatepass.repository;

import com.gatepass.models.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepo extends JpaRepository<DepartmentEntity, Long> {

}
