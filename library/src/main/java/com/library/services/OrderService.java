package com.library.services;

import com.library.models.Book;
import com.library.models.Order;
import com.library.models.Stock;
import com.library.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(Order order){

        orderRepository.save(order);
    }
    public Order findById(Long orderId){

        return orderRepository.findById(orderId).orElseThrow();
    }
    public void addOrderBook(Long orderId, Book book){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.addOrderBook(book);
        orderRepository.save(order);
    }
    public void delete(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        orderRepository.delete(order);
    }
    public List<Order> findByStatus(boolean status){

        return orderRepository.findByStatus(status);
    }
}
