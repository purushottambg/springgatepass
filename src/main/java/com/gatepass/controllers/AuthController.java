package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.models.MembershipEntity;
import com.gatepass.models.StaffEntity;
import com.gatepass.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper; 

    @PostMapping("/login")
    public String authenticateUser(Model model, @ModelAttribute LoginDTO loginDTO, HttpSession session) {
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
        }else{
            logger.info("User found with username: {}", username);
        }

        UserDetails userDetails  = (UserDetails) authentication.getPrincipal();
        final String token = jwtService.generateToken(userDetails);
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        session.setAttribute("Authorization", "Bearer "+token);

        Object userType = userDetails.getClass();

        if (userType.toString().contains("MembershipEntity")){
            logger.warn("User Identified as Requested Member");
            MembershipEntity membershipEntity= modelMapper.map(userDetails, MembershipEntity.class);
            model.addAttribute("membershipEntity", membershipEntity);
            return "ops/saved-request";
        }else if (userType.toString().contains("StaffEntity")){
            logger.warn("User Identified as teaching staff {}", userType);
            LoginDTO loginDTO1 = modelMapper.map(userDetails, LoginDTO.class);
            model.addAttribute("loginDTO", loginDTO1);
            model.addAttribute("passDTO", new PassDTO());
            return "pages/staff";
        }else if (userType.toString().contains("HODEntity")){
            logger.warn("User Identified as HOD {}", userType);
            return "pages/hod-home";
        }else {
            logger.warn("Failed to identify the userType {}", userType.toString().contains("MembershipEntity"));
        }

        return "login";
    }
}