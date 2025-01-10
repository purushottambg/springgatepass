package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PrincipalController {

    @GetMapping("pages/principal")
    public String principalHome(){
        return "pages/principal";
    }

    @GetMapping("pages/principal-pass-report")
    public String hodPassReport(){
        return "pages/principal-pass-report";
    }
}
