package com.gatepass.controllers;

import com.gatepass.models.LogInData;
import com.gatepass.models.MembershipRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("logInData", new LogInData());
        return "index";
    }

    @GetMapping("")
    public String indexPage(Model model){
        model.addAttribute("logInData", new LogInData());
        return "index";
    }
    @GetMapping("pages/member-request")  //Page is designed to accept the data
    public String memberRequest(Model model){
        MembershipRequest membershipRequest = new MembershipRequest();
        model.addAttribute("membershipRequest", membershipRequest);
        return "pages/member-request";
    }
}
