package com.library.services;

import com.library.models.Book;
import com.library.models.Order;
import com.library.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(Order order){

        return orderRepository.save(order);
    }
    public Order findById(Long orderId){

        return orderRepository.findById(orderId).orElseThrow();
    }
    public void addOrderBook(Long orderId, Book book){
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.addOrderBook(book);
        orderRepository.save(order);
    }
    public List<Book> listAllBooks(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        return order.getBooks();
    }
    public void delete(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        orderRepository.delete(order);
    }
    public List<Order> findByStatus(boolean status){

        return orderRepository.findByStatus(status);
    }
    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
