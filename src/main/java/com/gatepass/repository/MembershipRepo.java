package com.gatepass.repository;

import com.gatepass.models.MembershipRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepo extends JpaRepository<MembershipRequest, Long> {

}