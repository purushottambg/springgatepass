package com.gatepass.service;

import com.gatepass.models.MembershipEntity;
import com.gatepass.repository.MembershipRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MembershipRequestService implements UserDetailsService {

    private final MembershipRepo membershipRepo;
    private final PasswordEncoder passwordEncoder;


    private final Logger logger = LoggerFactory.getLogger(MembershipRequestService.class);

    /**
     * Save a membership request.
     * Generates a username from the user's first name and phone number and encode the password.
     */
    public MembershipEntity saveRequest(MembershipEntity membershipEntity) {
        validateMembershipRequest(membershipEntity);

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
     * Load user details for Spring Security.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MembershipEntity membershipEntity = membershipRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        logger.info("Loaded user: {}", membershipEntity.getUsername());
        return User.builder()
                .username(membershipEntity.getUsername())
                .password(membershipEntity.getPassword())
                .roles(formatRoles(membershipEntity.getDesignation()))
                .build();
    }

    /**
     * Validate membership request input.
     */
    private void validateMembershipRequest(MembershipEntity membershipEntity) {
        if (membershipEntity.getFname() == null || membershipEntity.getFname().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        if (membershipEntity.getPhone() == null || membershipEntity.getPhone().length() < 4) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (membershipEntity.getPassword() == null || membershipEntity.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    /**
     * Format roles to include the `ROLE_` prefix if not already present.
     */
    private String[] formatRoles(String roles) {
        return roles.startsWith("ROLE_") ? new String[]{roles} : new String[]{"ROLE_" + roles};
    }
}