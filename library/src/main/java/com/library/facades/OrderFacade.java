package com.library.facades;

import com.library.models.Book;
import com.library.models.Order;
import com.library.models.User;
import com.library.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderFacade {
    @Autowired
    private OrderService orderService;

    public Order saveOrder(boolean status, User user){
        Order order = new Order(status,user);
        return orderService.save(order);
    }
    public void orderTotalValue(Long id, List<Book> books){
        Order order = orderService.findById(id);
        order.setBooks(books);
        double soma = 0;
        for(Book b: order.getBooks()){
            soma+=b.getPrice().doubleValue();
        }
        order.setTotalValue(BigDecimal.valueOf(soma));
        orderService.save(order);
    }

}
