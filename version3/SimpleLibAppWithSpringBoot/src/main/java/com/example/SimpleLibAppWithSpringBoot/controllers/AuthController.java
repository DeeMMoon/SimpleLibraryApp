package com.example.SimpleLibAppWithSpringBoot.controllers;

import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.services.RegistrationService;
import com.example.SimpleLibAppWithSpringBoot.utils.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private PersonValidator personValidator;
    private RegistrationService registrationService;

    @Autowired
    public AuthController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person")Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
     //   System.out.println(person.getPassword());
        if(bindingResult.hasErrors())
            return "auth/registration";
        registrationService.registry(person);
        return "redirect:auth/login";
    }
}
