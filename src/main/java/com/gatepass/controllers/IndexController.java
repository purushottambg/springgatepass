package com.gatepass.controllers;


import com.gatepass.dtos.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "index";
    }

    @GetMapping("")
    public String indexPage(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "index";
    }

}
