package com.library.services;

import com.library.models.Author;
import com.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//CLASSE DE SERVIÇOS PARA AUTHOR(AUTOR)
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    //MÉTODO PARA SALVAR UM AUTHOR
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    //MÉTODO PARA DELETAR UM AUTHOR PELO ID
    public void deleteById(Long idAuthor) {
        Author author = authorRepository.findById(idAuthor).orElseThrow();
        authorRepository.delete(author);
    }

    //MÉTODO PARA LISTAR AUTHORS PELO NOME
    public List<Author> findByName(String nameAuthor) {
        return authorRepository.findByName(nameAuthor);
    }

    //MÉTODO PARA CONSULTAR UM AUTHOR PELO ID
    public Author findById(Long idAuthor) {
        return authorRepository.findById(idAuthor).orElseThrow();
    }

    //MÉTODO PARA ALTERAR UM AUTHOR
    public Author changeAuthor(Author author) {
        Author updatedAuthor = authorRepository.findById(author.getId()).orElseThrow();

        updatedAuthor.setName(updatedAuthor.getName());
        updatedAuthor.setBibliographicReference(updatedAuthor.getBibliographicReference());

        return save(updatedAuthor);
    }

    //MÉTODO PARA LISTAR TODOS OS AUTHORS
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

}
