package com.gatepass.controllers;

import com.gatepass.entities.MembershipRequest;
import com.gatepass.entities.TempData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class OpsController {

    @PostMapping("ops/save-request")
    public String saveRequestData(Model model, @ModelAttribute("membershipRequest") MembershipRequest membershipRequest){            //Data entered by the user must be saved
        model.addAttribute("membershipRequest", membershipRequest);
        return "ops/saved-request";
    }

    @PostMapping("ops/tempdata")
    public String saveTempData(Model model, @ModelAttribute("tempData") TempData tempData){            //Data entered by the user must be saved

        model.addAttribute("tempData", tempData);
        return "ops/tempdata";
    }

}
