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
    private Order order;

    @GetMapping("/Cart")
    public ModelAndView createOrder() {
        ModelAndView modelAndView = new ModelAndView("Order/Cart");
        modelAndView.addObject("orderBooks", orderBookService.findAll());
        return modelAndView;
    }

    @GetMapping("/amountChange/{id}/{action}")
    public ModelAndView amountChange (@PathVariable Long id, @PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Order/Cart");

        for (OrderBook orderBook: orderBookService.findAll()) {
            if(orderBook.getBook().getId().equals(id)) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
                if(action.equals(1)) {
                    orderBookSaved.setAmount(orderBookSaved.getAmount() + 1);
                }else if(action.equals(0)) {
                    orderBookSaved.setAmount(orderBookSaved.getAmount() - 1);
                }

             //   orderBookSaved.setTotalValue(book.getPrice() * orderBookSaved.getAmount());
                orderBookService.save(orderBookSaved);
            //    orderBook.setAmount(orderBookSaved.getAmount());
            //    orderBook.setTotalValue(orderBookSaved.getTotalValue());
            }
        }

        modelAndView.addObject("orderBooks", orderBookService.findAll());

        return modelAndView;
    }

    @GetMapping("/bookRemove/{id}")
    public ModelAndView bookRemove (@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("Order/Cart");

        for (OrderBook orderBook: orderBookService.findAll()) {
            if(orderBook.getBook().getId().equals(id)) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
            //    orderBooks.remove(orderBookSaved);
                orderBookService.delete(orderBookSaved);

            //    orderService.delete(order);

            }
        }

        modelAndView.addObject("orderBooks", orderBookService.findAll());

        return modelAndView;
    }


    //FALTA AJUSTAR A PARTE DE SE O PEDIDO AINDA ESTÁ EM ABERTO, OS ORDERBOOS TEM QUE SER ADIONADOS NESSE PEDIDO
    @GetMapping("/AddToCart/{id}")
    public ModelAndView addToCart(@PathVariable("id") Long id) {
        Book book = bookService.findById(id);
        ModelAndView modelAndView = new ModelAndView("Order/Cart");

        if(orderBooks.size() == 0) {
            User user = userService.findAll().get(0);
            order = new Order(true, user);
            orderService.save(order);
        }


        boolean repeatedBook = false;
        for (OrderBook orderBook: orderBookService.findAll()) {
            if(orderBook.getBook().getId().equals(book.getId())) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
                orderBookSaved.setAmount(orderBookSaved.getAmount() + 1);
                orderBookSaved.setTotalValue(book.getPrice() * orderBookSaved.getAmount());
                orderBookService.save(orderBookSaved);
                    //    orderBook.setAmount(orderBookSaved.getAmount());
                    //    orderBook.setTotalValue(orderBookSaved.getTotalValue());
                repeatedBook = true;
            }
        }



        if(!repeatedBook) {
            OrderBook orderBook = new OrderBook();
            orderBook.setBook(book);
            orderBook.setAmount(orderBook.getAmount() + 1);

            orderBook.setTotalValue(book.getPrice() * orderBook.getAmount());
            orderBookService.save(orderBook);

            orderBookService.findAll().add(orderBook);

            orderService.addOrderBook(order.getId(), orderBookService.findAll());
        }


        modelAndView.addObject("orderBooks", orderBookService.findAll());

        return modelAndView;
    }

}
