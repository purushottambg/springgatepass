package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements UserDetailsService {

    private final StaffRepo staffRepo;
    private final ModelMapper modelMapper;

    public StaffService(@Qualifier("StaffEntityToLogInDTO") ModelMapper modelMapper, StaffRepo staffRepo){
        this.staffRepo=staffRepo;
        this.modelMapper=modelMapper;
    }


    private final Logger logger = LoggerFactory.getLogger(StaffService.class);

    public boolean existsByUsername(String userName) {
        return staffRepo.findByUsername(userName).isPresent();
    }

    public LoginDTO findByUsername(String userName) {
        StaffEntity staff = staffRepo.findByUsername(userName).orElseThrow( ()-> new RuntimeException());
        logger.info("Fethced staff by username in staff service, staff id: "+staff.getStaffid());
        logger.info("Fethced staff by username in staff service, staff username: "+staff.getUsername());
        return modelMapper.map(staff, LoginDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("StaffService: Inside loadUserByUsername for user: {}", username);

        StaffEntity staff = staffRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        logger.debug("User found: {} with ID: {}", staff.getUsername(), staff.getStaffid());
        return staff;
    }

    public StaffEntity getUserByID(Long userId) {
        return staffRepo.getById(userId);
    }
}