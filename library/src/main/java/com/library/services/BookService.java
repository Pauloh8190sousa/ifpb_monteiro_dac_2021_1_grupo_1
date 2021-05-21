package com.library.services;

import com.library.models.Book;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long idBook) {
        Book book = bookRepository.findById(idBook).orElseThrow();
        bookRepository.delete(book);
    }

    public List<Book> findByName(String titleBook) {
        return bookRepository.findByTitle(titleBook);
    }

    public Book findById(Long idBook) {
        return bookRepository.findById(idBook).orElseThrow();
    }





}
