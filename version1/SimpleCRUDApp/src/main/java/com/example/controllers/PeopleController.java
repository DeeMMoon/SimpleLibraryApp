package com.example.controllers;

import com.example.dao.PeopleDAO;
import com.example.models.Person;
import com.example.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PeopleDAO peopleDAO;
    private PersonValidator validator;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, PersonValidator validator) {
        this.peopleDAO = peopleDAO;
        this.validator = validator;
    }

    @GetMapping()
    public String mainPage(Model model){
        model.addAttribute("people", peopleDAO.getPeople());
        return "people/index";
    }

    @GetMapping("/new")
    public String addNewPerson(@ModelAttribute("person") Person person){
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        validator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        peopleDAO.addPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleDAO.getPersonById(id));
        model.addAttribute("books", peopleDAO.personHasBooks(id));
        return "people/personPage";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") Integer id, Model model){
        model.addAttribute("person", peopleDAO.getPersonById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String edit(@PathVariable("id") Integer id, @ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "people/edit";
        peopleDAO.updatePerson(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        peopleDAO.deletePerson(id);
        return "redirect:/people";
    }
}
