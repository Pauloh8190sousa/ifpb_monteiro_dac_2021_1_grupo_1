package com.library.repositories;

import com.library.unidade.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//CLASSE REPOSITORY DE BOOK(LIVRO)
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //MÉTODO PARA LISTAR BOOKS PELO TITULO
    public List<Book> findByTitle(String titleBook);






}
