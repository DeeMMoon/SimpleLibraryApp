package com.example.SimpleLibAppWithSpringBoot.controllers;

import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.security.PersonDetails;
import com.example.SimpleLibAppWithSpringBoot.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final PeopleService peopleService;

    @Autowired
    public UserController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public String mainPage() {
        return "user/main_page";
    }

    @GetMapping("/profile")
    public String myPage(Model model, @AuthenticationPrincipal PersonDetails person) {
        model.addAttribute("person", peopleService.findPersonById(person.getPerson().getId()));
        return "user/profile";
    }

}
