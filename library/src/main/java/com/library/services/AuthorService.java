package com.library.services;

import com.library.models.Author;
import com.library.models.Book;
import com.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public void deleteById(Long idAuthor) {
        Author author = authorRepository.findById(idAuthor).orElseThrow();
        authorRepository.delete(author);
    }

    public List<Author> findByName(String nameAuthor) {
        return authorRepository.findByName(nameAuthor);
    }

    public Author findById(Long idAuthor) {
        return authorRepository.findById(idAuthor).orElseThrow();
    }

//    public Author changeAuthor(Author author) {
//        Author updatedAuthor = authorRepository.findById(author.getId()).orElseThrow();
//
//        updatedAuthor.setName(updatedAuthor.getName());
//        updatedAuthor.setBibliographicReference(updatedAuthor.getBibliographicReference());
//
//        return save(updatedAuthor);
//    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

}
