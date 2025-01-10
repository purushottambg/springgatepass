package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HODController {

    @GetMapping("pages/HOD")
    public String hodHome(){
        return "pages/HOD";
    }

    @GetMapping("pages/hod-pass-report")
    public String hodPassReport(){
        return "pages/hod-pass-report";
    }
}
