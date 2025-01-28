package com.gatepass.service;

import com.gatepass.repository.ClerkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClerkService {
    @Autowired
    private ClerkRepo clerkRepo;

    public boolean existByUserName(String UserName){
        return clerkRepo.findByUserName(UserName).isPresent();
    }

    public static class LoginAuthService {
    }
}
