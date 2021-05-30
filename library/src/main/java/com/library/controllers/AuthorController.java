package com.library.controllers;

import com.library.models.Author;
import com.library.models.User;
import com.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author postAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }
}
