package com.example.SimpleLibAppWithSpringBoot.services;

import com.example.SimpleLibAppWithSpringBoot.models.Person;
import com.example.SimpleLibAppWithSpringBoot.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    private final PeopleRepository peopleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(PeopleRepository peopleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.peopleRepository = peopleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void registryNewAdmin(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_ADMIN");
        peopleRepository.save(person);
    }
}
