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
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/createAuthor", method = RequestMethod.GET)
    public String createAuthor() {
        return "Author/AuthorForm";
    }
    @RequestMapping(value = "/createAuthor", method = RequestMethod.POST)
    public String createAuthor(Author author) {
        authorService.save(author);
        return "redirect:/Home";
    }

}
