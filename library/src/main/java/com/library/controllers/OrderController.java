package com.library.controllers;

import com.library.models.Book;
import com.library.models.Order;
import com.library.services.BookService;
import com.library.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrders(@RequestBody Order order) {
        return orderService.save(order);
    }
}
