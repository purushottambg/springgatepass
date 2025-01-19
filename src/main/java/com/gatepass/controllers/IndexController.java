package com.gatepass.controllers;


import com.gatepass.dtos.LoginDTO;
import com.gatepass.models.MembershipEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("logInData", new LoginDTO());
        return "index";
    }

    @GetMapping("")
    public String indexPage(Model model){
        model.addAttribute("logInData", new LoginDTO());
        return "index";
    }
    @GetMapping("pages/member-request")  //Page is designed to accept the data
    public String memberRequest(Model model){
        MembershipEntity membershipEntity = new MembershipEntity();
        model.addAttribute("membershipEntity", membershipEntity);
        return "pages/member-request";
    }
}
