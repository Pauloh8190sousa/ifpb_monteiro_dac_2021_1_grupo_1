package com.library.controllers;

import com.library.models.Author;
import com.library.models.Book;

import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
//    @Autowired
//    BookService bookService;
//
//    @GetMapping
//    public List<Book> getBooks() {
//        return bookService.findAll();
//    }
//
//    @GetMapping("/pages/{nbPage}")
//    public List<Book> listAllBooks(@PathVariable(value = "nbPage") int nbPage) {
//        return bookService.listAllBooks(nbPage);
//    }
//
//    @GetMapping("/price")
//    public List<Book> listCheapBooksAvailable() {
//        return bookService.listCheapBooksAvailable();
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book registerBook(@RequestBody Book book) {
//        return bookService.save(book);
//    }
//
//    @PutMapping
//    public Book changeBook(@RequestBody Book book) {
//        return bookService.save(book);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBookById(@PathVariable(value = "id") Long id) {
//        bookService.deleteById(id);
//    }
}
