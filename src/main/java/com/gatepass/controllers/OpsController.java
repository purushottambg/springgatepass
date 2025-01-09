package com.gatepass.controllers;

import com.gatepass.models.MembershipRequest;
import com.gatepass.service.MembershipRequestService;
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

    @PostMapping("ops/save-request")
    public String saveRequestData(Model model, @ModelAttribute("membershipRequest") MembershipRequest membershipRequest){            //Data entered by the user must be saved

        membershipRequestService.saveRequest(membershipRequest);
        model.addAttribute("membershipRequest", membershipRequest);
        return "ops/saved-request";   //Redirect to the saved page

    }

}
