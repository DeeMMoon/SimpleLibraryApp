package com.example.SimpleLibAppWithSpringBoot.controllers;

import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.security.PersonDetails;
import com.example.SimpleLibAppWithSpringBoot.services.AdminService;
import com.example.SimpleLibAppWithSpringBoot.services.PeopleService;
import com.example.SimpleLibAppWithSpringBoot.utils.PersonValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final PersonValidator personValidator;

    private final AdminService adminService;

    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator, AdminService adminService) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
        this.adminService = adminService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public String people(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", peopleService.findPersonById(id));
        model.addAttribute("books", peopleService.getBooksByPersonId(id));

        return "people/personPage";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/new";

        adminService.registryNewAdmin(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("person", peopleService.findPersonById(id));
        return "people/edit";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") long id, @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult, @AuthenticationPrincipal PersonDetails maker) {
        if (maker.getPerson().getRole().equals("ROLE_ADMIN")){
            if(bindingResult.hasFieldErrors("password")) {
                peopleService.update(id, person);
                return "redirect:/people";
            } else
                return "people/edit";
        } else if (maker.getPerson().getRole().equals("ROLE_USER")) {
            if (bindingResult.hasErrors())
                return "people/edit";
            peopleService.update(id, person);
            return "redirect:/user/profile";
        }else
            throw new AccessDeniedException("You do not have permission to perform this action");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}

