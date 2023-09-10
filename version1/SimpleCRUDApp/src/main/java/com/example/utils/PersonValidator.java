package com.example.utils;


import com.example.dao.PeopleDAO;
import com.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    PeopleDAO peopleDAO;

    @Autowired
    public PersonValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(peopleDAO.getPersonByFullName(person.getFullName()).isPresent())
            errors.rejectValue("fullName", "", "Person with same name exist");
    }
}
