package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.service.PassesService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(PassController.class);
    @PostMapping("/ops/save-pass")
    public String savePass(@ModelAttribute("passDTO") PassDTO passDTO, Model model){
        logger.info("Before passing to the passService staffid/descritpin {}",passDTO.getStaffid() );
        passesService.savePass(passDTO);
        return   "pages/staff";
    }
}
