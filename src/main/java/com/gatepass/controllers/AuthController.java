package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.service.CustomUserDetailsService;
import com.gatepass.service.JwtService;
import com.gatepass.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


/*
Accepts username and password via /auth/login.
Authenticates the user, then generates and returns a JWT token.
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public String authenticateUser(@RequestBody LoginDTO loginDTO) {
        logger.info("Authenticating user: {}", loginDTO.getUserName());

        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );

        // Fetch user details and generate JWT token
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUserName());
        String token = jwtService.generateToken(userDetails);
        logger.info("Generated token for user {}: {}", loginDTO.getUserName(), token);

        return token;
    }
}
