package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")

public class HomeController {
    @GetMapping("index")
    public String indexPage(Model model) {
        model.addAttribute("message", "Hey there");
        return "index Hello";  // Maps to templates/index.html
    }

    @GetMapping("asach")
    public String asach(){
        return "asach";
    }
}