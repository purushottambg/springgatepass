package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpsController {

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

        if(membershipRequestService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " your membership is not approved yet");
            return "pages/member-request";               //Redirection should be based on the user type
        } else if (staffService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response", "Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            return "pages/staff";
        } else if (hodService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            return "pages/hod-home";
        } else if (clerkService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            return "pages/clerk";
        } else if (principalService.existByUserName(loginDTO.getUserName())) {
            model.addAttribute("response","Hi, " + loginDTO.getUserName() + " Welcome");
            model.addAttribute("success", loginDTO);
            return "pages/principal";
        } else if (bindingResult.hasErrors()) {
            model.addAttribute("failureResponse", "it has some errors");
            return "index";
        } else {
            model.addAttribute("failureResponse", "Did not find");
            return "index";
        }
    }

}
