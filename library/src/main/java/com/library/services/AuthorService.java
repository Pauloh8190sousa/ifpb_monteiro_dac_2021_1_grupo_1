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

    public void save(Author author) {
        authorRepository.save(author);
    }

//    public void deleteById(Long idAuthor) {
//        Author author = authorRepository.findById(idAuthor).orElseThrow();
//        authorRepository.delete(author);
//    }

    public List<Author> findByName(String nameAuthor) {
        return authorRepository.findByName(nameAuthor);
    }

    public Author findById(Long idAuthor) {
        return authorRepository.findById(idAuthor).orElseThrow();
    }

    public void changeAuthor(Long idAuthor, String nameAuthor, String bibliographicReference) {
        Author updatedAuthor  = authorRepository.findById(idAuthor).orElseThrow();

        updatedAuthor.setName(nameAuthor);
        updatedAuthor.setBibliographicReference(bibliographicReference);

        save(updatedAuthor);
    }

}
