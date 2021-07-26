package com.library.controllers;

import com.library.models.Author;
import com.library.models.Book;

import com.library.services.AuthorService;
import com.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping("/listBook/{action}")
    public ModelAndView BookList(@PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Book/BookList");
        List<Book> books;
        if(action != null){
            books = bookService.listAllBooks(action);

        }else{
            books = bookService.listAllBooks(0);
        }

        modelAndView.addObject("books", books);

        return modelAndView;
    }

        @GetMapping("/listBook")
        public ModelAndView listBookPageable() {
                ModelAndView modelAndView = new ModelAndView("Book/BookList");
                List<Book> books = bookService.listAllBooks(0);

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

}
