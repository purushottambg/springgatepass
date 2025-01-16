package com.gatepass.controllers;

import com.gatepass.models.MembershipEntity;
import com.gatepass.service.MembershipRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MemberController {

    @Autowired
    private MembershipRequestService membershipRequestService;

    @PostMapping("ops/save-request")
    public String saveRequestData(Model model, @ModelAttribute("membershipEntity") MembershipEntity membershipEntity){            //Data entered by the user must be saved

        membershipRequestService.saveRequest(membershipEntity);
        model.addAttribute("membershipEntity", membershipEntity);

        return "ops/saved-request";   //Redirect to the saved page
    }

}
