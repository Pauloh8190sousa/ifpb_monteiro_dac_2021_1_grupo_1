package com.library.repositories;

import com.library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//CLASSE REPOSITORY DE AUTHOR(AUTOR)
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    //MÉTODO PARA LISTAR AUTHORS PELO NOME
    public List<Author> findByName(String nameAuthor);
}
