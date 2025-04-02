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

/*
This is the entry point of the program/web application, index page can be accessed using two paths.
/index - this is the complete address
/ - this is the directory with half address
both the pages needs one log in DTO. which is passed from this route, that's it
 */

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

