package com.library.restControllers;

import com.library.models.Book;
import com.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookRestController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    //MÉTODO PARA LISTAR BOOKS DE FORMA PÁGINADA
    @GetMapping("/pages/{nbPage}")
    public List<Book> listAllBooks(@PathVariable(value = "nbPage") int nbPage) {
        return bookService.listAllBooks(nbPage);
    }

    //MÉTODO PARA LISTAR BOOKS PELO PREÇO
    @GetMapping("/price")
    public List<Book> listCheapBooksAvailable() {
        return bookService.listCheapBooksAvailable();
    }

    //MÉTODO PARA CRIAR UM BOOK
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book registerBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    //MÉTODO PARA ATUALIZAR UM BOOK
    @PutMapping
    public Book changeBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    //MÉTODO PARA DELETAR UM BOOK PELO ID
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable(value = "id") Long id) {
        bookService.deleteById(id);
    }

}
