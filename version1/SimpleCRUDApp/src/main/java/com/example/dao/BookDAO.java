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
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void updateBook(Integer id, Book updateBook){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
    }

    public void deleteBook(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public Optional<Person> getBookOwner(Integer id){
        return jdbcTemplate.query("SELECT * FROM Book JOIN Person ON Person.id = Book.person_id WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void release(Integer id){
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE id = ?", id);
    }

    public void assign(Integer id, Person person){
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE id = ?", person.getId(), id);
    }

}
