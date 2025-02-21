package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.StaffEntity;
import com.gatepass.repository.StaffRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
@RequiredArgsConstructor
public class StaffService implements UserDetailsService {

    @Autowired
    private final StaffRepo staffRepo;
    @Autowired
    private final ModelMapper modelMapper;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return staffRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("No user with "+username+" found in the database!"));
    }
}
