package com.library.facades;

import com.library.models.Author;
import com.library.models.Book;
import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class BookFacade {

    @Autowired
    private BookService bookService;


    public Book saveBook(String title, BigDecimal price, String description, int nbOfPages, int isbn, boolean illustration){
        Book book = new Book(title, price, description, nbOfPages, isbn, illustration);
        return bookService.save(book);
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
    public List<Book> findAllBooks(int page){
        return bookService.listAllBooks(page);
    }
    public List<Book> listCheapBook(){
        return bookService.listCheapBooksAvailable();
    }
    public Book findById(Long idBook){
        return bookService.findById(idBook);
    }

    public int listBooksInStock(Book book) {
        return bookService.listBooksInStock(book);
    }

    public void addBookToStock(Long bookId) {
        bookService.addBookToStock(bookId);
    }

    public Book selectBook() {
        Scanner read = new Scanner(System.in);

        List<Book> books = bookService.findAll();

        for (Book book: books) {
            System.out.println("Id: "+ book.getId()+ "   Título: "+ book.getTitle() );
        }

        System.out.println("Selecione um livro pelo Id: ");
        Long bookId = Long.parseLong(read.nextLine());

        return bookService.findById(bookId);
    }

    public void addAuthorToBook(Long bookId, List<Author> authors) {
        bookService.addAuthorToBook(bookId, authors);
    }

}
