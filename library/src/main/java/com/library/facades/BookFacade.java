package com.library.facades;

import com.library.models.Book;
import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BookFacade {
    @Autowired
    private BookService bookService;

    public void saveBook(String title, BigDecimal price, String description, int nbOfPages, int isbn, boolean illustration){
        Book book = new Book(title, price, description, nbOfPages, isbn, illustration);
        bookService.save(book);
    }
    public void changeBook(Long id, String title, BigDecimal price, String description, int nbOfPages, int isbn, boolean illustration){
       Book book = findById(id);
       book.setTitle(title);
       book.setPrice(price);
       book.setDescription(description);
       book.setNbOfPages(nbOfPages);
       book.setIsbn(isbn);
       book.setIllustration(illustration);
       bookService.save(book);
    }
    public void deleteBook(Long id){
        bookService.deleteById(id);
    }

    public Book findById(Long idBook){
        return bookService.findById(idBook);
    }
}
