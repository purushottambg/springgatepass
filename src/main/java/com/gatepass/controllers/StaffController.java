package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StaffController {
    @GetMapping("pages/staff")
    public String adminPage(Model model){
        String message = "Hello Hardy, Work hard, there's a lot to do";
        model.addAttribute(message, "message");
        return "pages/staff";
    }
}
