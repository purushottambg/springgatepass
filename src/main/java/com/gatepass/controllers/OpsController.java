package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.service.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class OpsController {
    Logger logger = LoggerFactory.getLogger(OpsController.class);
    private MembershipRequestService membershipRequestService;
    private StaffService staffService;
    private HODService hodService;
    private ClerkService clerkService;
    private PrincipalService principalService;
    @Autowired
    private LoginAuthService loginAuthService;


    //We will be using this for the requested members only and not for the actual members
    @PostMapping("ops/validate-login")
    public ResponseEntity<String> logIn(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model){
        logger.info("Before the loginAuth, log in dto {}", loginDTO.getUserName());
        String token = loginAuthService.logIn(loginDTO);

        logger.info("Token generated for {} user is: ", token);
        return ResponseEntity.ok(token);
    }

//    public String logInValidation(@ModelAttribute("loginDTO") LoginDTO loginDTO, Model model, BindingResult bindingResult){
//
////        if(membershipRequestService.existByUserName(loginDTO.getUserName() )) {
////            String token = loginAuthService.login(l);
////            logger.trace("your token: {}");
////            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " your membership is not approved yet");
////            logger.info("Found user into the membership requested");
////
////            return "pages/member-request";               //Redirection should be based on the user type
////        } else if (staffService.existByUserName(loginDTO.getUserName())) {
////            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " Welcome");
////            model.addAttribute("success", loginDTO);
////            logger.info("user {} found in the staff records", loginDTO.getUserName());
////            return "pages/staff";
////        } else if (hodService.existByUserName(loginDTO.getUserName())) {
////            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
////            model.addAttribute("success", loginDTO);
////            logger.info("user is the HOD");
////            return "pages/hod-home";
////        } else if (clerkService.existByUserName(loginDTO.getUserName())) {
////            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
////            model.addAttribute("success", loginDTO);
////            logger.debug("User is the Clerk in college");
////            return "pages/clerk";
////        } else if (principalService.existByUserName(loginDTO.getUserName())) {
////            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
////            model.addAttribute("success", loginDTO);
////            logger.info("User is the Principal");
////            return "pages/principal";
////        } else {
////            model.addAttribute("failureResponse", "Did not find");
////            return "index";
//        }
//    }

}
