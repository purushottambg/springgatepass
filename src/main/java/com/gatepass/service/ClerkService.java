package com.gatepass.service;

import com.gatepass.models.ClerkEntity;
import com.gatepass.repository.ClerkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public class ClerkService {
    @Autowired
    private ClerkRepo clerkRepo;

    public boolean existByUserName(String userName){
        return clerkRepo.findByUsername(userName).isPresent();
    }
}
