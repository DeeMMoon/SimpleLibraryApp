package com.example.SimpleLibAppWithSpringBoot.controllers;

import com.example.SimpleLibAppWithSpringBoot.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String index(Model model, Person person){

       // String role = personDetails.getAuthorities().toString();
       // model.addAttribute("person", role);
        return "main_page";
    }
}
