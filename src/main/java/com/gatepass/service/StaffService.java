package com.gatepass.service;

import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepo staffRepo;

    public Boolean existByUserName(String userName){
        return staffRepo.findByUsername(userName).isPresent();
    }
}
