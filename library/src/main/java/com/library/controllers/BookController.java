package com.library.controllers;

import com.library.models.Author;
import com.library.models.Book;

import com.library.models.Category;
import com.library.models.PublishingCompany;
import com.library.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

//CLASSE CONTROLLER PARA BOOK(LIVRO)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublishingCompanyService publishingCompanyService;

    @ModelAttribute("allAuthors")
    public List<Author> authors() {
        return authorService.findAll();
    }

    @ModelAttribute("allCategories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("allPublishingCompanies")
    public List<PublishingCompany> publishingCompanies() {
        return publishingCompanyService.findAll();
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.GET)
    public String createBook() {
        return "Book/BookForm";
    }

    @RequestMapping(value = "/createBook", method = RequestMethod.POST)
    public String createBook(Book book) {
        Validation validation = new Validation();
        try {
            if(validation.validationPrice(BigDecimal.valueOf(book.getPrice())) &&
            validation.validationISBN(book.getIsbn()) &&
            validation.validationTitle(book.getTitle()) &&
            validation.pageLimit(book.getNbOfPages()) &&
            validation.validationDescriptionBook(book.getDescription())){

                bookService.save(book);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }


        return "redirect:/listBookConfig";
    }

    @PostMapping("/listBook/{id}")
    public ModelAndView editBook(Book book) {
        Validation validation = new Validation();

        bookService.save(book);

        return new ModelAndView("Admin/BookConfig");
    }

    @GetMapping("/listBook/{id}")
    public ModelAndView editBook(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Admin/BookEdit");
        Book book = bookService.findById(id);

        modelAndView.addObject("book", book);

        return modelAndView;
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") long id) {

        bookService.deleteById(id);

        return "redirect:/listBookConfig";
    }

    @GetMapping("/listBookConfig")
    public ModelAndView listBookPageableConfig() {
        ModelAndView modelAndView = new ModelAndView("Admin/BookConfig");
        List<Book> books = bookService.listAllBooks(0);

        modelAndView.addObject("books", books);

        return modelAndView;
    }

    @RequestMapping("/listBookConfig/{action}")
    public ModelAndView listBookPageableConfig(@PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Admin/BookConfig");
        List<Book> books;
        if(action != null){
            books = bookService.listAllBooks(action);

        }else{
            books = bookService.listAllBooks(0);
        }

        modelAndView.addObject("books", books);

        return modelAndView;
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

    @GetMapping("/listSearch")
    public ModelAndView listBook(@RequestParam(defaultValue = "") String title) {

        List<Book> books = bookService.findByTitleContaining(title);

        ModelAndView mv = new ModelAndView("Book/BookList");

        mv.addObject("books",books);

        return mv;
    }

}
