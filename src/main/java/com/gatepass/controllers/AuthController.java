package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.models.MembershipEntity;
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

/*
Identify the type of user, get it authenticated with the help of spring security.
generate token by calling the function for JWTService.
redirect the user to his home page for his further operations.
 */

public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ModelMapper modelMapper; 

    @PostMapping("/login")
    public String authenticateUser(Model model, @ModelAttribute LoginDTO loginDTO, HttpSession session) throws Exception{


        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword()));
        logger.info("Authentication successful for user: {}", loginDTO.getUserName());


        if (authentication == null) {
            logger.warn("No user found with username: {}", loginDTO.getUserName());
        }else{
            logger.info("User found with username: {}",  loginDTO.getUserName());
        }

        UserDetails userDetails  = (UserDetails) authentication.getPrincipal();
        logger.info("Trying to print the userDetails:{}",userDetails.getUsername());
        final String token = jwtService.generateToken(userDetails);
        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        logger.info("JWT Session is created:{}",token);

        session.setAttribute("Authorization", "Bearer "+token);

        Object userType = userDetails.getClass();

        if (userType.toString().contains("MembershipEntity")){
            logger.warn("User Identified as Requested Member");
            MembershipEntity membershipEntity= modelMapper.map(userDetails, MembershipEntity.class);
            model.addAttribute("membershipEntity", membershipEntity);
            model.addAttribute("success", membershipEntity.getUsername());

            return "ops/saved-request";     //Forward the request to the reqesters home page

        }else if (userType.toString().contains("StaffEntity")){
            logger.warn("User Identified as teaching staff {}", userType);
            LoginDTO loggedInUserDTO = modelMapper.map(userDetails, LoginDTO.class);
            model.addAttribute("loggedInUserDTO", loggedInUserDTO);
            PassDTO passDTO = new PassDTO();
            passDTO.setUserName(loggedInUserDTO.getUserName());
            model.addAttribute("passDTO", passDTO);

            return "pages/staff";       //Forward the request to the staff home page

        }else if (userType.toString().contains("HODEntity")){
            logger.warn("User Identified as HOD {}", userType);
            LoginDTO loginDTO1 = modelMapper.map(userDetails, LoginDTO.class);
            model.addAttribute("success", loginDTO1);
            return "pages/hod-home";
        }else {
            logger.warn("Failed to identify the userType {}", userType.toString().contains("MembershipEntity"));
        }

        return "login";
    }
}