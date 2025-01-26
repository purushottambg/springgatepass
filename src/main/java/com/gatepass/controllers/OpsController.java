package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpsController {
    Logger logger = LoggerFactory.getLogger(OpsController.class);
    @Autowired
    private MembershipRequestService membershipRequestService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private HODService hodService;
    @Autowired
    private ClerkService clerkService;
    @Autowired
    private PrincipalService principalService;


    //We will be using this for the requested members only and not for the actual members
    @PostMapping("ops/validate-login")
    public String logInValidation(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model, BindingResult bindingResult){

        if(membershipRequestService.existByUserName(loginDTO.getUserName() )) {
            String token = membershipRequestService.generateToken(loginDTO.getUserName(), loginDTO.getPassword());
            logger.trace("your token: {}"+token);
            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " your membership is not approved yet");
            logger.info("Found user into the membership requested");
            logger.debug("This should be redirected to the members");
            logger.error("This is the error in case");
            logger.trace("This is the trace in case");

            return "pages/member-request";               //Redirection should be based on the user type
        } else if (staffService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            logger.info("user found in the staff records");
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
