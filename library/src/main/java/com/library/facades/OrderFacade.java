package com.library.facades;

import com.library.models.Book;
import com.library.models.Order;
import com.library.models.OrderBook;
import com.library.models.User;
import com.library.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Component
public class OrderFacade {
    @Autowired
    private OrderService orderService;


//    public Order saveOrder(boolean status, User user, BigDecimal totalValue) {
//        Order order = new Order(status,user, totalValue);
//        return orderService.save(order);
//    }
//    public void orderTotalValue(Long orderId) {
//        Order order = orderService.findById(orderId);
//
//        double soma = 0;
//        for(Book b: orderBookService.getBooks()){
//            soma+=b.getPrice().doubleValue();
//        }
//        order.setTotalValue(BigDecimal.valueOf(soma));
//        orderService.save(order);
//
//    }

//    public void addOrderBook(Long orderId, List<Book> books) {
//        orderService.addOrderBook(orderId, books);
//    }

    public Order selectOrder() {
        Scanner read = new Scanner(System.in);

        List<Order> orders = orderService.findAll();

        for (Order order: orders) {
            System.out.println("Id: "+ order.getId()+ "   Usuário: "+ order.getUser().getName() );
        }

        System.out.println("Selecione um pedido pelo Id: ");
        Long orderId = Long.parseLong(read.nextLine());

        return orderService.findById(orderId);
    }

}
