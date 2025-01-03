package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class IndexController {

    @GetMapping("index")
    public String index(){
        return "index";
    }

    @GetMapping("")
    public String indexPage(){
        return "index";
    }

    @GetMapping("member-request")
    public String memberRequest(){
        return "member-request";
    }
}
