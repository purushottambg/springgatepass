package com.gatepass.controllers;

import com.gatepass.entities.MembershipRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpsController {

    @PostMapping("ops/save-request")
    public String saveRequestData(Model model, @ModelAttribute("membershipRequest") MembershipRequest membershipRequest){            //Data entered by the user must be saved
        MembershipRequest membershipRequest1=new MembershipRequest();
        model.addAttribute("membershipRequest1", membershipRequest1);
        return "ops/saved-request";
    }
}
