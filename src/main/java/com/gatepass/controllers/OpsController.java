package com.gatepass.controllers;

import com.gatepass.models.LogInData;
import com.gatepass.service.ClerkService;
import com.gatepass.service.HODService;
import com.gatepass.service.MembershipRequestService;
import com.gatepass.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    //We will be using this for the requested members only and not for the actual members
    @PostMapping("ops/validate-login")
    public String logInValidation(@ModelAttribute("logInData")LogInData logInData, Model model){
        if(membershipRequestService.existByUserName(logInData.getUserName())) {
            model.addAttribute("response", "Hi, " + logInData.getUserName() + " your membership is not approved yet");
            return "pages/member-request";               //Redirection should be based on the user type
        } else if (staffService.existByUserName(logInData.getUserName())) {
            model.addAttribute("response", "Hi, " + logInData.getUserName() + " Welcome");
            return "pages/staff";
        } else if (hodService.existByUserName(logInData.getUserName())) {
            model.addAttribute("response",logInData);
            return "pages/hod-home";
        } else if (clerkService.existByUserName(logInData.getUserName())) {
            model.addAttribute("response",logInData);
            return "pages/clerk";
        }
        else {
            model.addAttribute("failureResponse", "Did not find");
            return "index";
        }
    }

}
