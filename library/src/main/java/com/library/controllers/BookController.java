package com.library.controllers;

import com.library.models.Author;
import com.library.models.Book;
import com.library.services.AuthorService;
import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book postBooks(@RequestBody Book book) {
        return bookService.save(book);
    }
}
