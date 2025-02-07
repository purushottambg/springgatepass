package com.gatepass.controllers;

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
        Long createdPassId = passesService.savePass(passDTO);
        if(createdPassId!=null && createdPassId>0){
            logger.info("Created Pass Id is {}", createdPassId);
            model.addAttribute("passsubmission","Pass is successfully created with passid: "+createdPassId);
        }else{
            model.addAttribute("passsubmission","Pass creation failed");
        }


        return   "pages/staff";
    }
}
