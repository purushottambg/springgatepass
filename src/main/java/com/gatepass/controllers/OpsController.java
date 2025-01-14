package com.gatepass.controllers;

import com.gatepass.models.LogInData;
import com.gatepass.models.MembershipRequest;
import com.gatepass.service.MembershipRequestService;
import com.gatepass.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("ops/save-request")
    public String saveRequestData(Model model,  @ModelAttribute("membershipRequest") MembershipRequest membershipRequest){            //Data entered by the user must be saved

        membershipRequestService.saveRequest(membershipRequest);
        model.addAttribute("membershipRequest", membershipRequest);
        return "ops/saved-request";   //Redirect to the saved page
    }

    //We will be using this for the requested members only and not for the actual members
    @PostMapping("ops/validate-login")
    public String logInValidation(@ModelAttribute("logInData")LogInData logInData, Model model){
        if(membershipRequestService.existByUserName(logInData.getUserName())) {
            model.addAttribute("response", "Hi, " + logInData.getUserName() + " Welcome");
            return "pages/staff";               //Redirection should be based on the user type
        } else if (staffService.existByUserName(logInData.getUserName())) {
            model.addAttribute("response", "Hi, " + logInData.getUserName() + " Welcome");
            return "pages/staff";
        }
        model.addAttribute("failureResponse","Did not find");
        return  "index";
    }

}
