package com.gatepass.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("thmlf/link-expression")
    public String linkExpression(Model model){
        model.addAttribute("id", 1);
        return "thmlf/link-expression";
    }

    @GetMapping("thmlf/fragments-expression")   //fragments are used in this page
    public String fragmentExpression(){
        return "thmlf/fragments-expression";
    }

    @GetMapping("thmlf/each-expression")
    public String eachExpression(Model model){
        PersonData user = new PersonData(1,"Purushottam", "Java", 30);
        PersonData Samruddhi = new PersonData(2, "Samruddhi", "Toddler", 30);
        PersonData Damodhar = new PersonData(3, "Damodhar", "Adult", 30);
        PersonData Gokarna = new PersonData(4, "Gokarna", "HouseWife", 30);
        List<PersonData> Users = new ArrayList<>();
        Users.add(user);
        Users.add(Samruddhi);
        Users.add(Damodhar);
        Users.add(Gokarna);
        model.addAttribute("Users", Users);
        return "thmlf/each-expression";
    }
}
