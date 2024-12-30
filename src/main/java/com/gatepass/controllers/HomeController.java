package com.gatepass.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String indexPage(Model model) {
        PersonData user = new PersonData(1,"Purushottam Gutthe", "Java", 30);
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("register")
    public String registerPage(Model model){
        PersonData Samruddhi = new PersonData(2, "Samruddhi", "Toddler", 30);
        model.addAttribute("Samruddhi", Samruddhi);
        return "register";
    }
}
