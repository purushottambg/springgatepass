package com.gatepass.service;

import com.gatepass.exceptions.ResourceNotFoundException;
import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
@Primary
@Service
public class MembershipRequestService implements UserDetailsService{

    private final MembershipRepo membershipRepo;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(MembershipRequestService.class);

    public MembershipRequestService(MembershipRepo membershipRepo, PasswordEncoder passwordEncoder) {
        this.membershipRepo = membershipRepo;
        this.passwordEncoder=passwordEncoder;
    }
    /**
     * Save a membership request.
     * Generates a username from the user's first name and phone number and encode the password.
     */
    @Transactional
    public MembershipEntity saveRequest(MembershipEntity membershipEntity) {

        String firstName = membershipEntity.getFname().toLowerCase();
        String phone = membershipEntity.getPhone();
        String userName = firstName + phone.substring(phone.length() - 4);
        membershipEntity.setUsername(membershipEntity.getFname());
        logger.info("Generated username: {}", userName);

        membershipEntity.setUsername(userName);
        membershipEntity.setPassword(passwordEncoder.encode(membershipEntity.getPassword()));
        logger.info("Password encoded successfully");

        return membershipRepo.save(membershipEntity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return membershipRepo.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("User with "+username+" does not exist!"));
    }

    public Optional<MembershipEntity> findByID(Long appid) {
        return membershipRepo.findById(appid);
    }

    public boolean existByUserName(String userName) {
        logger.info("Checking if {} present in membership requested ppl", userName);
        return membershipRepo.findByUsername(userName).isPresent();
    }
}

