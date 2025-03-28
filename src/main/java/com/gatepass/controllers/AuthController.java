package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String authenticateUser(@ModelAttribute LoginDTO loginDTO) {
        String username = loginDTO.getUserName();
        String password = loginDTO.getPassword();
        logger.info("AuthController: authenticateUser() invoked with username: {}", username);

        Authentication authentication;
        // Authentication
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info("Authentication successful for user: {}", username);
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", username);
            return "AuthController: Authentication failed!"+e;
        }

        if (authentication == null) {
            logger.warn("No user found with username: {}", username);
            return "User not found!";
        }else{
            logger.info("User found with username: {}", username);
        }

        UserDetails userDetails  = (UserDetails) authentication.getPrincipal();
        logger.info("found the type of user is: {}",userDetails.getAuthorities());

        // Generate token
        String token = jwtService.generateToken(userDetails);
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        logger.info("Generated token for {}: {}", username, token);
        logger.info("AuthController: Username from the security context: {}", authentication1.getName());
        return jwtService.extractUsername(token)+" is the username while token is: "+token+" Valid: "+jwtService.isTokenValid(token, userDetails);
    }
}