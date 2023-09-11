package com.example.SimpleLibAppWithSpringBoot.services;

import com.example.SimpleLibAppWithSpringBoot.models.Book;
import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.repositories.PeopleRepository;
import com.example.SimpleLibAppWithSpringBoot.security.PersonDetails;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)

public class PeopleService{

    private static final int EXPIRED_TIME = 864000000; //10 days
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findPersonById(long id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(long id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(long id) {
        peopleRepository.deleteById(id);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return peopleRepository.findByUsername(fullName);
    }

    public List<Book> getBooksByPersonId(long id) {
        Optional<Person> person = peopleRepository.findById(id);

        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            person.get().getBooks().forEach(book -> {
                if (Math.abs(book.getTakenAt().getTime() - new Date().getTime()) > EXPIRED_TIME)
                    book.setExpired(true);
            });

            return person.get().getBooks();
        }
        else {
            return Collections.emptyList();
        }
    }

}
