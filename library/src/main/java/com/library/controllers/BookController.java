package com.library.controllers;

import com.library.models.Author;
import com.library.models.Book;

import com.library.services.AuthorService;
import com.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//CLASSE CONTROLLER PARA BOOK(LIVRO)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @ModelAttribute("allAuthors")
    public List<Author> authors(){
        return authorService.findAll();
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.GET)
    public String createBook() {
        return "Book/BookForm";
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String createBook(Book book) {

        bookService.save(book);

        return "redirect:/Home";
    }
    @RequestMapping("/listBook")
    public ModelAndView BookList() {
        ModelAndView modelAndView = new ModelAndView("Book/BookList");
        List<Book> books = bookService.listAllBooks(1);
//        List<Book> books = listAllBooks(5);
//        Collections.sort(books);
        modelAndView.addObject("books", books);


        return modelAndView;
    }

    @RequestMapping("/{id}")
    public ModelAndView BookDetails(@PathVariable("id") long id) {
        Book book = bookService.findById(id);

        ModelAndView modelAndView = new ModelAndView("Book/BookDetails");
        modelAndView.addObject("book", book);

        return modelAndView;
    }

//    @GetMapping
//    public List<Book> getBooks() {
//        return bookService.findAll();
//    }
//
    //MÉTODO PARA LISTAR BOOKS DE FORMA PÁGINADA
//    @GetMapping("/pages/{nbPage}")
//    public List<Book> listAllBooks(@PathVariable(value = "nbPage") int nbPage) {
//        return bookService.listAllBooks(nbPage);
//    }
//
//    //MÉTODO PARA LISTAR BOOKS PELO PREÇO
//    @GetMapping("/price")
//    public List<Book> listCheapBooksAvailable() {
//        return bookService.listCheapBooksAvailable();
//    }
//
//    //MÉTODO PARA CRIAR UM BOOK
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book registerBook(@RequestBody Book book) {
//        return bookService.save(book);
//    }
//
//    //MÉTODO PARA ATUALIZAR UM BOOK
//    @PutMapping
//    public Book changeBook(@RequestBody Book book) {
//        return bookService.save(book);
//    }
//
//    //MÉTODO PARA DELETAR UM BOOK PELO ID
//    @DeleteMapping("/{id}")
//    public void deleteBookById(@PathVariable(value = "id") Long id) {
//        bookService.deleteById(id);
//    }
}
