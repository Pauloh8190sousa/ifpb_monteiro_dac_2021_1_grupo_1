package com.library.restControllers;

import com.library.models.Author;
import com.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorRestController {

    @Autowired
    private AuthorService authorService;

    /**
     * MÉTODO PARA LISTAR TODOS OS AUTHORS
     */
    @GetMapping("/listAuthorsRest")
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
