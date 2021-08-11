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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        } else if(order == null) {
            order = new Order();
            order.setTotalOrderPrice(0);
        }
        List<OrderBook> orderBooks = orderService.findAll().get(orderService.findAll().size() - 1).getOrderBooks();

        modelAndView.addObject("order", order);
        modelAndView.addObject("orderBooks", orderBooks);
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


    @GetMapping("/AddToCart/{id}")
    public String addToCart(@PathVariable("id") Long id) {

//        if (orderBookService.findAll().size() > 1) {
//            for (OrderBook orderBook: orderBookService.findAll()) {
//                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
//                orderBookService.delete(orderBookSaved);
//            }
//        }

        book = bookService.findById(id);

        if(orderService.findAll().size() == 0 ) {
            User user = userService.findAll().get(0);
            order = new Order(true, user);
            orderService.save(order);
        } else if(orderService.findAll().size() > 0 && orderService.findAll().get(orderService.findAll().size() - 1).isOpen()) {
            orderService.save(order);
        } else if(orderService.findAll().size() > 0 && !orderService.findAll().get(orderService.findAll().size() - 1).isOpen()) {
            User user = userService.findAll().get(0);
            order = new Order(true, user);
            orderService.save(order);
        }

        boolean repeatedBook = false;
        for (OrderBook orderBook: orderService.findAll().get(orderService.findAll().size() - 1).getOrderBooks() ) {
            if(orderBook.getBook().getId().equals(book.getId()) && orderService.findAll().get(orderService.findAll().size() - 1).isOpen()) {
                OrderBook orderBookSaved = orderBookService.findById(orderBook.getId());
                orderBookSaved.setTotalValue(0);
                orderBookSaved.setAmount(orderBookSaved.getAmount() + 1);
                orderBookSaved.setTotalValue(orderBookSaved.getTotalValue() + (book.getPrice() * orderBookSaved.getAmount()));
                repeatedBook = true;
            }
        }
        //O PROBLEMA TÁ NESSE IF
        if(!repeatedBook) {
            OrderBook orderBook = new OrderBook();
            orderBook.setBook(book);
            orderBook.setAmount(orderBook.getAmount() + 1);

            orderBook.setTotalValue(orderBook.getTotalValue() + (book.getPrice() * orderBook.getAmount()));
            orderBookService.save(orderBook);

           // orderService.findAll().get(orderService.findAll().size() - 1).getOrderBooks().add(orderBook);
          //  orderBookService.findAll().add(orderBook);

            order.getOrderBooks().add(orderBook);

            orderService.addOrderBook(order.getId(), order.getOrderBooks());
            orderService.save(order);

        }

        return "redirect:/Cart";
    }


}
