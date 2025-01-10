package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ClerkController {

    @GetMapping("pages/clerk")
    public String clerkHome(){
        return "pages/clerk";
    }
}
