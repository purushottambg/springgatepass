package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.service.PassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PassController {
    private final PassesService passesService;
    @PostMapping("/ops/save-pass")
    public String savePass(@ModelAttribute("passDTO") PassDTO passDTO, Model model){
        passesService.savePass(passDTO);
        return   "pages/staff";
    }
}
