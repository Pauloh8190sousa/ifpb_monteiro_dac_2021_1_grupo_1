package com.library.controllers;
import com.library.models.Book;
import com.library.models.Order;
import com.library.models.OrderBook;
import com.library.services.BookService;
import com.library.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class CartController {

    @Autowired
    OrderService orderService;

    @Autowired
    BookService bookService;

    private ArrayList<OrderBook> orderBooks = new ArrayList<>();

    @GetMapping("/Cart")
    public ModelAndView createOrder(){
        ModelAndView modelAndView = new ModelAndView("Order/Cart");
        modelAndView.addObject("orderBooks", orderBooks);
        return modelAndView;
    }

    //Depois tenho que mudar esse método!!!! Para adicionar um user ao pedido
    @GetMapping("/AddToCart/{id}")
    public ModelAndView addToCart(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("Order/Cart");

        OrderBook orderBook = new OrderBook();
        orderBook.setBook(book);
        orderBook.setAmount(orderBook.getAmount());

        orderBook.setTotalValue(book.getPrice());
        orderBooks.add(orderBook);

        modelAndView.addObject("orderBooks", orderBooks);

        return modelAndView;
    }

}
