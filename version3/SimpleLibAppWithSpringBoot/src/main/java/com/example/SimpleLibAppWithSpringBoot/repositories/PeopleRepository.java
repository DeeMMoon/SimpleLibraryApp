package com.example.SimpleLibAppWithSpringBoot.repositories;


import com.example.SimpleLibAppWithSpringBoot.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByUsername(String fullName);
}
