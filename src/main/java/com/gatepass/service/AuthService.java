package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
Authenticates the user with AuthenticationManager.
Identifies the user type and fetches user details from the respective service (StaffService, HODService, etc.).
Generates and returns a JWT if authentication succeeds.
 */

@Service
@RequiredArgsConstructor
public class AuthService {

    @Lazy
    private final StaffService staffService;
    private final HODService hodService;
    private final ClerkService clerkService;
    private final PrincipalService principalService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final MembershipRequestService membershipRequestService;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    /**
     * Authenticates the user and generates a JWT token.
     */
    public String authenticateUser(LoginDTO loginDTO) {
        String username = loginDTO.getUserName();
        String password = loginDTO.getPassword();

        // Authentication check
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", username);
            return null;
        }

        // Identify user type and fetch details
        UserDetails userDetails = getUserDetails(username);
        if (userDetails == null) {
            logger.warn("No user found with username: {}", username);
            return null;
        }

        // Generate Token
        String token = jwtService.generateToken(username);
        logger.info("Generated token for {}: {}", username, token);
        return token;
    }

    /**
     * Identifies the user type and fetches user details accordingly.
     */
    private UserDetails getUserDetails(String username) {
        if (staffService.existsByUsername(username)) {
            return staffService.loadUserByUsername(username);
//        } else if (hodService.existByUserName(username)) {
//            return hodService.loadUserByUsername(username);
//        } else if (clerkService.existByUserName(username)) {
//            return clerkService.loadUserByUsername(username);
//        } else if (principalService.existByUserName(username)) {
//            return principalService.loadUserByUsername(username);
        } else if (membershipRequestService.existsByUsername(username)) {
            return null;
        }
        return null;
    }
}
