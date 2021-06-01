package com.library.facades;

import com.library.models.Author;
import com.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorFacade {
    @Autowired
    private AuthorService authorService;

    public void saveAuthor(String name, String bibliographicReference){
        Author author = new Author(name,bibliographicReference);
        authorService.save(author);
    }
    public List<Author> findByName(String name){
        return authorService.findByName(name);
    }
    public Author findById(Long authorId){
        return authorService.findById(authorId);
    }
}
