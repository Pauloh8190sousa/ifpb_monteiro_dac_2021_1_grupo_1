package com.library.controllers;

import com.library.models.Author;
import com.library.services.Validation;
import com.library.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @PostMapping("/createAuthor")
    public String createAuthor(Author author) {
        Validation validation = new Validation();
        if(validation.validationBibliographicReference(author.getBibliographicReference())){
            authorService.save(author);
        }

        return "redirect:/listAuthor";
    }

    @PostMapping("/listAuthor/{id}")
    public ModelAndView editAuthor(Author author) {
        Validation validation = new Validation();
        if(validation.validationBibliographicReference(author.getBibliographicReference())){
            authorService.save(author);
        }

        return new ModelAndView("Admin/AuthorConfig");
    }

    @GetMapping("/listAuthor/{id}")
    public ModelAndView editAuthor(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Admin/AuthorEdit");
        Author author = authorService.findById(id);

        modelAndView.addObject("author", author);

        return modelAndView;
    }

    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id") long id) {

        authorService.deleteById(id);

        return "redirect:/listAuthor";
    }


    @RequestMapping("/listAuthor/{action}")
    public ModelAndView AuthorList(@PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Admin/AuthorConfig");
        List<Author> authors;
        if(action != null) {
            authors = authorService.listAllAuthors(action);

        }else{
            authors = authorService.listAllAuthors(0);
        }

        modelAndView.addObject("authors", authors);

        return modelAndView;
    }

    @GetMapping("/listAuthor")
    public ModelAndView listAuthorPageable() {
        ModelAndView modelAndView = new ModelAndView("Admin/AuthorConfig");
        List<Author> authors = authorService.listAllAuthors(0);

        modelAndView.addObject("authors", authors);

        return modelAndView;
    }

}
