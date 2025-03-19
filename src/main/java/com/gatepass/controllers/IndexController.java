package com.gatepass.controllers;


import com.gatepass.dtos.LoginDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        logger.info("Redirecting to the /index page with the LogInDTO");
        return "index";
    }

    @GetMapping("")
    public String indexPage(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        logger.info("Redirecting to the index page with the LogInDTO");
        return "index";
    }

}

