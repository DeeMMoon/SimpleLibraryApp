package com.example.SimpleLibAppWithSpringBoot.services;

import com.example.SimpleLibAppWithSpringBoot.models.Book;
import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
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
        if(updatedPerson.getPassword() == null)
            updatedPerson.setPassword(passwordEncoder.encode(peopleRepository.findById(id).get().getPassword()));
        else
            updatedPerson.setPassword(passwordEncoder.encode(updatedPerson.getPassword()));
        updatedPerson.setRole(peopleRepository.findById(id).get().getRole());
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(long id) {
        peopleRepository.deleteById(id);
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
