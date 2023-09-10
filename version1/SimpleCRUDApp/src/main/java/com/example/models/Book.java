package com.example.models;

import lombok.Data;

import javax.validation.constraints.*;

public class Book {
    private Integer id;
    @NotEmpty(message = "Title can't be empty")
    @Size(min = 1, max = 50, message = "The title length must be from 1 to 50 characters")
    private String title;
    @NotEmpty(message = "Book must have author")
    @Size(min = 1, max = 50, message = "The author name length must be from 1 to 50 characters")
    private String author;
    @Min(value = 900, message = "This book can't be early 900")
    @Max(value = 2023, message = "This book can't be older 2023")
    private Integer year;


    public Book(Integer id, String title, String author, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
