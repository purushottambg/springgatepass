package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    private final StaffRepo staffRepo;
    private final ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(StaffService.class);

    public boolean existsByUsername(String userName) {
        return staffRepo.findByUsername(userName).isPresent();
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