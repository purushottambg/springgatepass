package com.gatepass.controllers;

import com.gatepass.dtos.PassDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StaffController {

    @GetMapping("pages/staff")
    public String adminPage(Model model){
        model.addAttribute("passDTO", new PassDTO());
        return "pages/staff";
    }
}
