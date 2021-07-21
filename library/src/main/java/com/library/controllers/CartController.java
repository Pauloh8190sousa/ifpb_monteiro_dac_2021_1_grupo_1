package com.library.controllers;
import com.library.models.Book;
import com.library.models.Order;
import com.library.services.BookService;
import com.library.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class CartController {

    @Autowired
    OrderService orderService;

    @Autowired
    BookService bookService;

    @GetMapping("/Cart")
    public ModelAndView createOrder(){
        return new ModelAndView("Order/Cart");
    }

    @GetMapping("/AddToCart/{id}")
    public ModelAndView addToCart(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);

        ModelAndView modelAndView = new ModelAndView("Order/Cart");
        modelAndView.addObject("book", book);

        return modelAndView;
    }

}
