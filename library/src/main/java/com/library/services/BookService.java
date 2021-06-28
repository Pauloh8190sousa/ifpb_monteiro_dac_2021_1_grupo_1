package com.library.services;

import com.library.unidade.Author;
import com.library.unidade.Book;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//CLASSE DE SERVIÇOS PARA BOOK(LIVRO)
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    //MÉTODO PARA SALVAR UM BOOK
    public Book save(Book book) {

        return bookRepository.save(book);
    }

    //MÉTODO PARA ADICONAR AUTHORS A UM BOOK
    public void addAuthorToBook(Long bookId, List<Author> authors) {
        Book book = bookRepository.findById(bookId).orElseThrow();

        book.setAuthors(authors);

        bookRepository.save(book);

    }

    //MÉTODO PARA DELETAR UM BOOK PELO ID
    public void deleteById(Long idBook) {
        Book book = bookRepository.findById(idBook).orElseThrow();
        bookRepository.delete(book);
    }

    //MÉTODO PARA LISTAR BOOKS PELO TITULO
    public List<Book> findByTitle(String titleBook) {
        return bookRepository.findByTitle(titleBook);
    }

    //MÉTODO PARA CONSULTAR UM BOOK PELO ID
    public Book findById(Long idBook) {
        return bookRepository.findById(idBook).orElseThrow();
    }

    //MÉTODO PARA ADICIONAR UM BOOK AO STOCK
    public void addBookToStock(Long bookId) {
       Book book = bookRepository.findById(bookId).orElseThrow();

       int bookStock = book.getStock() + 1;
       book.setStock(bookStock);

       save(book);
    }

    //MÉTODO PARA ALTERAR UM BOOK
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

    //MÉTODO PARA LISTAR OS CINCO BOOKS MAIS BARATOS DISPONIVEIS NO STOCK
    public List<Book> listCheapBooksAvailable() {
        PageRequest pageRequest = PageRequest.of(0,5, Sort.Direction.ASC, "price");

        ArrayList<Book> books = new ArrayList<>();

        for (Book book: bookRepository.findAll(pageRequest).getContent()) {
            if (book.getStock() > 0) {
                books.add(book);
            }
        }

        return books;
    }

    //MÉTODO PARA LISTAR TODOS OS BOOKS ORDENADOS DE FORMA ASCENDENTE PELO TITULO E DE FORMA PAGINADA
    public List<Book> listAllBooks(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber,5, Sort.Direction.ASC, "title");
        return bookRepository.findAll(pageRequest).getContent();
    }

    //MÉTODO PARA LISTAR TODOS OS BOOKS
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

}
