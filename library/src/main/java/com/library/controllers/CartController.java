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
import java.sql.SQLOutput;
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
    private Order order;
    private Book book;

    public void calculateTotalOrderPrice() {
        order.setTotalOrderPrice(0);
        for (OrderBook orderBook: orderBookService.findAll()) {
            order.setTotalOrderPrice(order.getTotalOrderPrice() + orderBook.getTotalValue());
            orderService.save(order);
        }

    }

    @GetMapping("/Cart")
    public ModelAndView createOrder() {
        ModelAndView modelAndView = new ModelAndView("Order/Cart");

        if(order != null) {
            calculateTotalOrderPrice();
        } else if(order == null){
            order = new Order();
            order.setTotalOrderPrice(0);
        }

        modelAndView.addObject("order", order);
        modelAndView.addObject("orderBooks", orderBookService.findAll());
        return modelAndView;
    }

    @GetMapping("/amountChange/{id}/{action}")
    public String amountChange (@PathVariable Long id, @PathVariable Integer action) {

        for (OrderBook orderBook: orderBookService.findAll()) {
            if(orderBook.getBook().getId().equals(id)) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
                if(action.equals(1)) {
                    orderBookSaved.setTotalValue(0);
                    orderBookSaved.setAmount(orderBookSaved.getAmount() + 1);
                    orderBookSaved.setTotalValue(orderBookSaved.getTotalValue() + (book.getPrice() * orderBookSaved.getAmount()));
                }else if(action.equals(0)) {
                    orderBookSaved.setTotalValue(0);
                    orderBookSaved.setAmount(orderBookSaved.getAmount() - 1);
                    orderBookSaved.setTotalValue(orderBookSaved.getTotalValue() + (book.getPrice() * orderBookSaved.getAmount()));
                }
                orderBookService.save(orderBookSaved);
            }
        }

        return "redirect:/Cart";
    }

    @GetMapping("/bookRemove/{id}")
    public String bookRemove (@PathVariable Long id) {

        for (OrderBook orderBook: orderBookService.findAll()) {
            if(orderBook.getBook().getId().equals(id)) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
                orderBookService.delete(orderBookSaved);
                orderService.save(order);
            }
        }

        return "redirect:/Cart";
    }


    //FALTA TERMINAR A PARTE DE CRIAR MAIS DE UM PEDIDO
    @GetMapping("/AddToCart/{id}")
    public String addToCart(@PathVariable("id") Long id) {
        book = bookService.findById(id);

        if(orderBookService.findAll().size() == 0 ) {
            User user = userService.findAll().get(0);
            order = new Order(true, user);
            orderService.save(order);
        }
//        } else if(order.isOpen()) {
//            orderService.save(order);
//        } else if(!order.isOpen()) {
//
//        }

        boolean repeatedBook = false;
        for (OrderBook orderBook: orderBookService.findAll()) {
            if(orderBook.getBook().getId().equals(book.getId())) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
                orderBookSaved.setTotalValue(0);
                orderBookSaved.setAmount(orderBookSaved.getAmount() + 1);
                orderBookSaved.setTotalValue(orderBookSaved.getTotalValue() + (book.getPrice() * orderBookSaved.getAmount()));
                orderBookService.save(orderBookSaved);
                repeatedBook = true;
            }
        }

        if(!repeatedBook) {
            OrderBook orderBook = new OrderBook();
            orderBook.setBook(book);
            orderBook.setAmount(orderBook.getAmount() + 1);

            orderBook.setTotalValue(orderBook.getTotalValue() + (book.getPrice() * orderBook.getAmount()));
            orderBookService.save(orderBook);

            orderBookService.findAll().add(orderBook);

            orderService.addOrderBook(order.getId(), orderBookService.findAll());
        }

        return "redirect:/Cart";
    }

}
