package com.example.SimpleLibAppWithSpringBoot.utils;

import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.services.PeopleService;
import com.example.SimpleLibAppWithSpringBoot.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person)o;
        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        }catch (UsernameNotFoundException e)
        {
            return;
        }
        errors.rejectValue("username", "", "Человек с таким ФИО уже существует");
    }
}