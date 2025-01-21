package com.gatepass.service;

import com.gatepass.repository.PrincipalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PrincipalService {
    @Autowired
    private PrincipalRepo principalRepo;

    public Boolean existByUserName(String userName){
        return principalRepo.findByUserName(userName).isPresent();
    }

}
