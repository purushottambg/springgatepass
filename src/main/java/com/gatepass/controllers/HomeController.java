package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("index")
    public String indexPage(Model model) {
        model.addAttribute("message", "Message for the index from controller!");
        return "index";  // Template: src/main/resources/templates/index.html
    }

    @GetMapping("register")
    public String registerPage(Model model){
        model.addAttribute("message", "Dynamic Message from Controller!");
        return "register";
    }
}
