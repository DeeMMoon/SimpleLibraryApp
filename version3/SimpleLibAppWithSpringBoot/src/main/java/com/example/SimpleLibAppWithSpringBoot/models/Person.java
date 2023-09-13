package com.example.SimpleLibAppWithSpringBoot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 80, message = "The name length must be from 3 to 80 characters")
    @Column(name = "username")
    private String username;

    @Min(value = 1915, message = "Date of birth must be no earlier than 1915")
    @Max(value = 2023, message = "Date of birth must be no earlier than 1915")
    @Column(name = "date_born")
    private int dateBorn;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    @NotEmpty(message = "Password can't be empty")
//    @Size(min = 3, max = 15, message = "The password length must be from 3 to 80 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public Person() {

    }

    public Person(String username, int dateBorn) {
        this.username = username;
        this.dateBorn = dateBorn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(int dateBorn) {
        this.dateBorn = dateBorn;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}