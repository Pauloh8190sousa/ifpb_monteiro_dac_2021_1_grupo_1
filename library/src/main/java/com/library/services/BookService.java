package com.library.services;

import com.library.models.Author;
import com.library.models.Book;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    public void addAuthorToBook(Long authorId, Book book) {
        Author author = authorRepository.findById(authorId).orElseThrow();

        book.getAuthors().add(author);

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

//    public void changeBook(Long idBook, String title, BigDecimal price, String description, int nbOfPages, int ISBN, boolean illustration) {
//        Book updatedBook = bookRepository.findById(idBook).orElseThrow();
//
//        updatedBook.setTitle(title);
//        updatedBook.setPrice(price);
//        updatedBook.setDescription(description);
//        updatedBook.setNbOfPages(nbOfPages);
//        updatedBook.setISBN(ISBN);
//        updatedBook.setIllustration(illustration);
//
//        save(updatedBook);
//    }

    public List<Book> listCheapBooksAvailable() {
        PageRequest pageRequest = PageRequest.of(0,5, Sort.Direction.ASC, "price");
        return bookRepository.findAll(pageRequest).getContent();
    }

    public List<Book> listAllBooks(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber,5, Sort.Direction.ASC, "title");
        return bookRepository.findAll(pageRequest).getContent();
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }
}
