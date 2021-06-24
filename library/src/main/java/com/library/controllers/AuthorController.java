package com.library.controllers;

import com.library.models.Author;
import com.library.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CLASSE CONTROLLER PARA AUTHOR(AUTOR)
 */
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
@RequestMapping("/author")

public class AuthorController {

    @Autowired
    AuthorService authorService;

    /**
     * MÉTODO PARA LISTAR TODOS OS AUTHORS
     */
    @GetMapping
    public List<Author> getAuthors() {
        return authorService.findAll();
    }

    /**
     * MÉTODO PARA CRIAR AUTHORS
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author registerAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    /**
     * MÉTODO PARA ATUALIZAR AUTHORS
     */
    @PutMapping
    public Author changeAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }
}
