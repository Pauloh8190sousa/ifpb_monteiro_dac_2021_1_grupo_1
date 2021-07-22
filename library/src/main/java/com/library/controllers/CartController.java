package com.library.controllers;
import com.library.models.Book;
import com.library.models.Order;
import com.library.models.OrderBook;
import com.library.models.User;
import com.library.services.BookService;
import com.library.services.OrderBookService;
import com.library.services.OrderService;
import com.library.services.UserService;
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
    private OrderService orderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderBookService orderBookService;

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

        User user = userService.findAll().get(0);
        Order order = new Order(true, user);
        orderService.save(order);

        OrderBook orderBook = new OrderBook();
        orderBook.setBook(book);
        orderBook.setAmount(orderBook.getAmount().add(BigDecimal.valueOf(1)));

        orderBook.setTotalValue(book.getPrice());
        orderBookService.save(orderBook);

        orderBooks.add(orderBook);

        orderService.addOrderBook(order.getId(), orderBooks);

        modelAndView.addObject("orderBooks", orderBooks);

        return modelAndView;
    }

}
