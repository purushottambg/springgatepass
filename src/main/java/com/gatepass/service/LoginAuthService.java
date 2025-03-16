package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginAuthService {

    private final AuthenticationManager authenticationManager;
    //private final JWTService jwtService;
    private final MembershipRequestService membershipRequestService;
    @Lazy
    private final StaffService staffService;
    private final Logger logger = LoggerFactory.getLogger(LoginAuthService.class);

    public String logIn(LoginDTO loginDTO) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );

        if (!authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        // Fetch UserDetails based on authenticated user
        UserDetails userDetails;
        if (membershipRequestService.existsByUsername(loginDTO.getUserName())) {
            userDetails = membershipRequestService.loadUserByUsername(loginDTO.getUserName());
        } else if (staffService.existsByUsername(loginDTO.getUserName())) {
            userDetails = staffService.loadUserByUsername(loginDTO.getUserName());
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return "null";
    }
}




/*package com.gatepass.service;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.MembershipEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public class LoginAuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final Logger logger = LoggerFactory.getLogger(LoginAuthService.class);
    private final UserDetailsService userDetailsService;

    public LoginAuthService(AuthenticationManager authenticationManager, JWTService jwtService, UserDetailsService userDetailsService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }


    /**
     * Generate a JWT token for an authenticated user.
     */

/*    public String logIn(LoginDTO loginDTO){
        logger.info("First line in login auth");
        authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );
        logger.info("After return of the authentication object:");
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUserName());
//         MembershipEntity membershipEntity = (MembershipEntity) authentication.getPrincipal();
        logger.info("Generated Token is {}", jwtService.generateToken("membershipEntity"));
        return jwtService.generateToken(userDetails);
    }

    public String generateToken(String userName, String password) {
        logger.debug("before the Authentication take control for {} user and password is {}", userName, password);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password)
            );

            logger.debug("before the UserDetails take control");

            MembershipEntity membershipEntity = (MembershipEntity) authentication.getPrincipal(); // Retrieve user details
            logger.info("Authenticated user: {}", membershipEntity.getUsername());

            String token = jwtService.generateToken(membershipEntity); // Use username to generate JWT
            logger.trace("Generated token for {} user is {}", membershipEntity.getUsername(), token);

            return token;

        } catch (Exception e) {
            logger.error("An error occurred during authentication for user '{}': {}", userName, e.getMessage());
            throw new RuntimeException("Authentication failed due to an internal error");
        }

    }
}*/


