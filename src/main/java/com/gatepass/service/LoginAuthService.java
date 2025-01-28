package com.gatepass.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginAuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final Logger logger = LoggerFactory.getLogger(LoginAuthService.class);

    /**
     * Generate a JWT token for an authenticated user.
     */
    public String generateToken(String userName, String password) {
        logger.debug("before the Authentication take control for {} user and password is {}", userName, password);

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, password)
            );

            logger.debug("before the UserDetails take control");

            UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // Retrieve user details
            logger.info("Authenticated user: {}", userDetails.getUsername());

            String token = jwtService.generateToken(userDetails.getUsername()); // Use username to generate JWT
            logger.trace("Generated token for {} user is {}", userDetails.getUsername(), token);

            return token;

        } catch (Exception e) {
            logger.error("An error occurred during authentication for user '{}': {}", userName, e.getMessage());
            throw new RuntimeException("Authentication failed due to an internal error");
        }

    }
}