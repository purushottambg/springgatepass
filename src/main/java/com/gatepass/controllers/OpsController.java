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
