package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Lazy
    private final StaffService staffService;
    private final HODService hodService;
    private final ClerkService clerkService;
    private final PrincipalService principalService;
    private final JwtService jwtService;
    private final MembershipRequestService membershipRequestService;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    /*
      Identifies the user type and fetches user details accordingly.
     */
    public UserDetails getUserDetails(String username) {
        logger.info("AuthService: Checking which User is this: {}", username);
        logger.info("AuthService: Checking if User is Staff: {}", staffService.existsByUsername(username));
        if (staffService.existsByUsername(username)) {
            logger.info("AuthService: User is from StaffService: {}", username);
            return staffService.loadUserByUsername(username);
        } else if (membershipRequestService.existsByUsername(username)) {
            logger.info("AuthService: User is from Membership: {}", username);
            return membershipRequestService.loadUserByUsername(username);
        }
        return null;
    }
}
