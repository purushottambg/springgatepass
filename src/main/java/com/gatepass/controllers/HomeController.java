package com.gatepass.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping()
    public String indexPage(Model model) {
        PersonData user = new PersonData(1,"Purushottam Gutthe", "Java", 30);
        model.addAttribute("user", user);
        return "index";
    }
    @GetMapping("thmlf/variable-expression")
    public String variableExpression(Model model) {
        PersonData user = new PersonData(1,"Purushottam Gutthe", "Java", 30);
        model.addAttribute("user", user);
        return "thmlf/variable-expression";
    }

    @GetMapping("thmlf/selection-expression")
    public String selectionExpression(Model model){
        PersonData Samruddhi = new PersonData(2, "Samruddhi", "Toddler", 30);
        model.addAttribute("Samruddhi", Samruddhi);
        return "thmlf/selection-expression";
    }

    @GetMapping("thmlf/message-expression")
    public String messageExpression(){
        return "thmlf/message-expression";
    }
}
