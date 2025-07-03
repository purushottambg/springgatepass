package com.gatepass.controllers;

import com.gatepass.dtos.LoginDTO;
import com.gatepass.dtos.PassDTO;
import com.gatepass.service.PassesService;
import com.gatepass.service.StaffService;
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
    private final StaffService staffService;

    Logger logger = LoggerFactory.getLogger(PassController.class);

    @PostMapping("/ops/save-pass")
    public String savePass(@ModelAttribute("passDTO") PassDTO passDTO, Model model){

        logger.info("PassController  Pass Id: {}", passDTO.getPassId());
        logger.info("PassController  staff id: {}", passDTO.getStaffId());
        logger.info("PassController  staff userName: {}", passDTO.getUserName());
        logger.info("PassController  pass date: {}", passDTO.getDate());
        logger.info("PassController  Pass description: {}", passDTO.getDescription());
        logger.info("PassController  out time: {}", passDTO.getOuttime());
        logger.info("PassController  in time : {}", passDTO.getIntime());
        logger.info("PassController  main reason: {}", passDTO.getReason());
        logger.info("PassController  sub reason: {}", passDTO.getSubreason());

        if(passDTO==null){
            logger.info("Pass DTO is null at PASS CONTROLLER Layer");
        }

        Long createdPassId = passesService.savePass(passDTO);
        logger.info("Created Pass Id:{} at the successful times ",createdPassId);
        if(createdPassId!=null && createdPassId>0){
            logger.info("Created Pass Id is {}", createdPassId);
            model.addAttribute("passsubmission","Pass is successfully created with pass id: "+createdPassId);
        }else{
            model.addAttribute("passsubmission","Pass creation failed");
        }

        LoginDTO loggedInUserDTO = staffService.findByUsername(passDTO.getUserName());
        model.addAttribute("loggedInUserDTO", loggedInUserDTO);
        logger.info("Logged in user pass ID: "+loggedInUserDTO.getId());
        PassDTO newPassDTO = new PassDTO();
        passDTO.setStaffId(loggedInUserDTO.getId());
        passDTO.setUserName(passDTO.getUserName());
        model.addAttribute("passDTO", passDTO);

        return "pages/staff";
    }
}
