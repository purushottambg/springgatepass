package com.gatepass.controllers;

import com.gatepass.models.MembershipRequest;
import com.gatepass.models.TempData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("")
    public String indexPage(){
        return "index";
    }

    @GetMapping("registertempform")
    public String registerTempData(Model model){
        TempData tempData = new TempData();
        model.addAttribute("tempData", tempData);    //Empty object passing so, it can hold the data.
        return "registertempform";
    }

    @GetMapping("member-request")
    public String memberRequest(Model model){
        MembershipRequest membershipRequest = new MembershipRequest();
        model.addAttribute("membershipRequest", membershipRequest);
        return "member-request";
    }
}
