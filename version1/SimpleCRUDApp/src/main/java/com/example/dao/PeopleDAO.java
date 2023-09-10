package com.example.dao;

import com.example.models.Book;
import com.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PeopleDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PeopleDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> getPersonByFullName(String fullName){
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name = ?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void addPerson(Person person){
        jdbcTemplate.update("INSERT INTO Person(full_name, date_born) VALUES (?,?)",
                person.getFullName(), person.getDateBorn());
    }

    public void updatePerson(Integer id, Person updatePerson){
        jdbcTemplate.update("UPDATE Person SET full_name=?, date_born=? WHERE id=?",
                updatePerson.getFullName(), updatePerson.getDateBorn(), id);
    }

    public void deletePerson(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public List<Book> personHasBooks(int id){
       return jdbcTemplate.query("SELECT * FROM Book WHERE person_id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
