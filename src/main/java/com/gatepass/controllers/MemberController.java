package com.gatepass.controllers;

import com.gatepass.exceptions.ResourceNotFoundException;
import com.gatepass.models.MembershipEntity;
import com.gatepass.service.MembershipRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MemberController {

    @Autowired
    private MembershipRequestService membershipRequestService;
    Logger logger = LoggerFactory.getLogger(MemberController.class);


    /*
    When user entered credentials are correct then he should be redirected from this method
     */
    @GetMapping("pages/member-request")  //Page is designed to accept the data
    public String memberRequest(Model model){
        MembershipEntity membershipEntity = new MembershipEntity();
        logger.info("empty Membership Request sent to the page");
        model.addAttribute("membershipEntity", membershipEntity);
        return "pages/member-request";
    }

    @PostMapping("ops/save-request")
    public String saveRequestData(Model model, @ModelAttribute("membershipEntity") MembershipEntity membershipEntity){            //Data entered by the user must be saved

        logger.info("member sent for saving to the Service");
        MembershipEntity toBeSavedEntity = membershipRequestService.saveRequest(membershipEntity);

        MembershipEntity savedEntity = membershipRequestService.findByID(toBeSavedEntity.getAppid()).orElseThrow(()-> new ResourceNotFoundException("Appid do not exit"));

        model.addAttribute("membershipEntity", savedEntity);

        return "ops/saved-request";   //Redirect to the saved page
    }

}
