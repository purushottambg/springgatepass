package com.gatepass.controllers;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/form/")
public class FormController {
    @GetMapping("register")
    public String registrationPage(Model model){
        FormData formData = new FormData();
        model.addAttribute("formData", formData);
        List<Boolean> teachingVals = Arrays.asList(true, false);
        model.addAttribute("teachingVals", teachingVals);
        return "form/register";
    }

    @PostMapping("/form/save")
    public String showData(Model model, @ModelAttribute("formData") FormData formData){
        model.addAttribute("formData", formData);
        return "/form/saved-data";
    }
}
