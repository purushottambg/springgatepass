package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpsController {

    @PostMapping("ops/save-request")
    public String saveRequestData(){            //Data entered by the user must be saved
        return "ops/save-request";
    }
}
