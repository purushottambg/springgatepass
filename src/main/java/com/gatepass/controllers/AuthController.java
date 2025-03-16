package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.service.AuthService;
import com.gatepass.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute LoginDTO loginDTO) {
        String username = loginDTO.getUserName();
        String password = loginDTO.getPassword();
        logger.info("AuthController: authenticateUser() invoked with username: {}", username);

        // Authentication
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info("Authentication successful for user: {}", username);
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", username);
            return "Authentication failed!";
        }

        // Fetch user details
        UserDetails userDetails = authService.getUserDetails(username);
        if (userDetails == null) {
            logger.warn("No user found with username: {}", username);
            return "User not found!";
        }

        // Generate token
        String token = jwtService.generateToken(username);
        logger.info("Generated token for {}: {}", username, token);
        return token;
    }
}