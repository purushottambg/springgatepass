package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
🏆 What changed:
Centralized Authentication:

Moved authentication logic to AuthService.
OpsController only handles redirection and token management now.
Cleaner Code:

Removed redundant service calls.
Replaced multiple if-else conditions with cleaner checks.
Added Token:

Passed the generated token to the model, so you can use it in Thymeleaf templates if needed.
Logs:

Improved logging for better traceability.

 */

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class OpsController {

    private final AuthService authService;
    private final MembershipRequestService membershipRequestService;
    private final StaffService staffService;
    private final HODService hodService;
    private final ClerkService clerkService;
    private final PrincipalService principalService;

    private static final Logger logger = LoggerFactory.getLogger(OpsController.class);

    /**
     * Validates login and redirects to appropriate pages based on user type.
     */
    @PostMapping("ops/validate-login")
    public String logInValidation(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model) {
        String username = loginDTO.getUserName();

        // Check if the user is still in membership request
        if (membershipRequestService.existsByUsername(username)) {
            model.addAttribute("response", "Hi, " + username + " your membership is not approved yet");
            logger.info("User found in membership requests.");
            return "pages/member-request";
        }

        // Authenticate user and generate JWT
        String token = authService.authenticateUser(loginDTO);
        if (token == null) {
            model.addAttribute("failureResponse", "Invalid username or password");
            logger.warn("Authentication failed for user: {}", username);
            return "index";
        }

        model.addAttribute("token", token);
        model.addAttribute("success", loginDTO);
        logger.info("User {} authenticated successfully", username);

        // Redirect based on user type
        if (staffService.existsByUsername(username)) {
            PassDTO passDTO = new PassDTO();
            passDTO.setStaffid(Long.valueOf(1143)); // Ideally fetch this dynamically
            model.addAttribute("passDTO", passDTO);
            logger.info("Redirecting {} to Staff page", username);
            return "pages/staff";
        } else if (hodService.existByUserName(username)) {
            logger.info("Redirecting {} to HOD page", username);
            return "pages/hod-home";
        } else if (clerkService.existByUserName(username)) {
            logger.info("Redirecting {} to Clerk page", username);
            return "pages/clerk";
        } else if (principalService.existByUserName(username)) {
            logger.info("Redirecting {} to Principal page", username);
            return "pages/principal";
        } else {
            model.addAttribute("failureResponse", "User type not recognized");
            logger.warn("User type not recognized for {}", username);
            return "index";
        }
    }
}


/*package com.gatepass.controllers;


import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.service.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class OpsController {
    private final MembershipRequestService membershipRequestService;
    private final StaffService staffService;
    private final HODService hodService;
    private final ClerkService clerkService;
    private final PrincipalService principalService;
    Logger logger = LoggerFactory.getLogger(OpsController.class);

    @PostMapping("ops/validate-login")
    public String logInValidation(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model){

        if(membershipRequestService.existsByUsername(loginDTO.getUserName())) {
            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " your membership is not approved yet");
            logger.info("Found user into the membership requested");
            return "pages/member-request";               //Redirection should be based on the user type
        } else if (staffService.existsByUsername(loginDTO.getUserName())) {
            logger.info("first dto username is {}", loginDTO.getUserName());
            //logger.info("Generated Token value is: {}", staffService.loginStaff(loginDTO));
            //LoginDTO loginDTO1 = staffService.existsByUsername(loginDTO.getUserName());
            PassDTO passDTO = new PassDTO();
            passDTO.setStaffid(Long.valueOf(1143));
            model.addAttribute("passDTO", passDTO);
            model.addAttribute("success", loginDTO);
            logger.info("staffid in passing DTO is: {}  ", passDTO.getStaffid());
            return "pages/staff";
        } else if (hodService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            logger.info("user is the HOD");
            return "pages/hod-home";
        } else if (clerkService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            logger.debug("User is the Clerk in college");
            return "pages/clerk";
        } else if (principalService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            logger.info("User is the Principal");
            return "pages/principal";
        } else {
            model.addAttribute("failureResponse", "Did not find");
            return "index";
        }
    }

}

*/
