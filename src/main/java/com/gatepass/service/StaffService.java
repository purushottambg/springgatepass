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

    public LoginDTO getStaffDetails(String userName) {
        StaffEntity staffEntity = staffRepo.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return modelMapper.map(staffEntity, LoginDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("StaffService: Inside loadUserByUsername for user: {}", username);

        StaffEntity staff = staffRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        logger.debug("User found: {} with ID: {}", staff.getUsername(), staff.getStaffid());
        return staff;
    }
}


/*package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    private final StaffRepo staffRepo;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    Logger logger = LoggerFactory.getLogger(StaffService.class);

    public Boolean existByUserName(String userName){
        return staffRepo.findByUsername(userName).isPresent();
    }

    public LoginDTO existByUserName2(String userName){
        logger.info("Username in service is {}", userName);
        StaffEntity staffEntity = staffRepo.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Did not find the user"));
        logger.info("staffEntity username in service is {}", staffEntity.getUsername());

        /*
        modelmapper.typeMap is a kinda mapper that defines how mapping should take place
         */
/*
        modelMapper.typeMap(StaffEntity.class, LoginDTO.class)
                .setConverter(context -> {
                    StaffEntity src = context.getSource();
                    LoginDTO dest = new LoginDTO();
                    dest.setId(src.getStaffid());
                    return dest;
                });

        LoginDTO repoDTO = modelMapper.map(staffEntity, LoginDTO.class);
        logger.info("retrieved DTO staffid is: {}", repoDTO.getId());
        return repoDTO;
    }


    public String loginStaff(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        logger.info("Generated Token is : {}", token);
        return token;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return staffRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user with "+username+" found in the database!"));
    }
}*/