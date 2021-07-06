package com.library.controllers;

import com.library.models.Book;
import com.library.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//CLASSE CONTROLLER DO INDEX DA APLICACAO
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class IndexController {

    @Autowired
    BookService bookService;

    //MÉTODO PARA RETORNAR PAGINA
    @RequestMapping("/Home")
    public void index() {
//        ModelAndView modelAndView = new ModelAndView("index");
//        List<Book> books = bookService.findAll();
//        modelAndView.addObject("books", books);
//
//        return modelAndView;
    }

}
