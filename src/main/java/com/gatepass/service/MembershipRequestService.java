package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@RequiredArgsConstructor
@Service
public class MembershipRequestService{

    private final MembershipRepo membershipRepo;
    private final PasswordEncoder passwordEncoder;


    private final Logger logger = LoggerFactory.getLogger(MembershipRequestService.class);

    /**
     * Save a membership request.
     * Generates a username from the user's first name and phone number and encode the password.
     */
    public MembershipEntity saveRequest(MembershipEntity membershipEntity) {

        String firstName = membershipEntity.getFname().toLowerCase();
        String phone = membershipEntity.getPhone();
        String userName = firstName + phone.substring(phone.length() - 4);
        logger.info("Generated username: {}", userName);

        membershipEntity.setUsername(userName);
        membershipEntity.setPassword(passwordEncoder.encode(membershipEntity.getPassword()));
        logger.info("Password encoded successfully");

        return membershipRepo.save(membershipEntity);
    }

    /**
     * Check if a user exists by username.
     */
    public boolean existByUserName(String userName) {
        boolean exists = membershipRepo.findByUsername(userName).isPresent();
        logger.info("User '{}' exists return status is: {}", userName, exists);
        return exists;
    }


    /**
     * Format roles to include the `ROLE_` prefix if not already present.
     */
    private String[] formatRoles(String roles) {
        return roles.startsWith("ROLE_") ? new String[]{roles} : new String[]{"ROLE_" + roles};
    }
}