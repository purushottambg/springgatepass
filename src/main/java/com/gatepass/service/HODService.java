package com.gatepass.service;

import com.gatepass.repository.HODRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HODService {

    @Autowired
    private HODRepo hodRepo;

    public boolean existByUserName(String userName) {
        return hodRepo.findByuserName(userName).isPresent();
    }
}
