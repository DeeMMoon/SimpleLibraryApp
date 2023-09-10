package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;

public class Person {

    private Integer id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 3, max = 80, message = "The name length must be from 3 to 80 characters")
    private String fullName;
    @Min(value = 1915, message = "Date of birth must be no earlier than 1915")
    @Max(value = 2023, message = "Date of birth must be no earlier than 1915")
    private Integer dateBorn;

    public Person(Integer id, String fullName, Integer dateBorn) {
        this.id = id;
        this.fullName = fullName;
        this.dateBorn = dateBorn;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getDateBorn() {
        return dateBorn;
    }

    public void setDateBorn(Integer dateBorn) {
        this.dateBorn = dateBorn;
    }
}
