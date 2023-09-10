package com.example.controllers;

import com.example.dao.BookDAO;
import com.example.dao.PeopleDAO;
import com.example.models.Book;
import com.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private BookDAO bookDAO;
    private PeopleDAO peopleDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PeopleDAO peopleDAO) {
        this.bookDAO = bookDAO;
        this.peopleDAO = peopleDAO;
    }


    @GetMapping()
    public String booksMainPage(Model model){
        model.addAttribute("books", bookDAO.getAllBooks());
        return "books/index";
    }

    @GetMapping("/new")
    public String addNewBook(@ModelAttribute("book")Book book){
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Integer id, @ModelAttribute("book") Book book){
        bookDAO.getBookById(id);
        return "books/edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
    @GetMapping("/{id}")
    public String bookPage(@PathVariable("id") Integer id, @ModelAttribute("person") Person person, Model model){
        model.addAttribute("book", bookDAO.getBookById(id));
        Optional<Person> owner = bookDAO.getBookOwner(id);

        if(owner.isPresent()){
            model.addAttribute("owner", owner.get());
        }
        else {
            model.addAttribute("people", peopleDAO.getPeople());
        }
        return "books/book";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/book";
        bookDAO.updateBook(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") Integer id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") Integer id, @ModelAttribute("person") Person person) {
        bookDAO.assign(id, person);
        return "redirect:/books/" + id;
    }
}
