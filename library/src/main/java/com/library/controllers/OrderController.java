package com.library.controllers;
import com.library.models.Book;
import com.library.models.Order;
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

//    @GetMapping
//    public List<Order> getOrders() {
//        return orderService.findAll();
//    }
//    @GetMapping("/books_order/{id}")
//    public List<Book> listAllBooks(@PathVariable(value = "id") Long orderId){
//        return orderService.listAllBooks(orderId);
//    }
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Order postOrders(@RequestBody Order order) {
//        return orderService.save(order);
//    }
//    @PostMapping("/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addOrderBook(@PathVariable(value = "id") Long id, @RequestBody Book book){
//        orderService.addOrderBook(id,book);
//    }
}
