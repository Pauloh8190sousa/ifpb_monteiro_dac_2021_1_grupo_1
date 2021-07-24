package com.library.services;

import com.library.models.Book;
import com.library.models.Order;
import com.library.models.OrderBook;
import com.library.repositories.BookRepository;
import com.library.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//CLASSE DE SERVIÇOS PARA ORDER(PEDIDO)
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    //MÉTODO PARA SALVAR UM ORDER
    public Order save(Order order){

        return orderRepository.save(order);
    }

    //MÉTODO PARA CONSULTAR UM ORDER PELO ID
    public Order findById(Long orderId){

        return orderRepository.findById(orderId).orElseThrow();
    }

    //MÉTODO PARA ADICIONAR BOOKS AO ORDER
    public void addOrderBook(Long orderId, List<OrderBook> orderBooks) {
        Order order = orderRepository.findById(orderId).orElseThrow();

        order.setOrderBooks(orderBooks);

        orderRepository.save(order);
    }

    public List<OrderBook> listAllBooks(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow();
        return order.getOrderBooks();
    }

    //MÉTODO PARA DELETAR UM ORDER PELO ID
    public void delete(Order order){
        orderRepository.delete(order);
    }

    //MÉTODO PARA LISTAR ORDERS PELO STATUS
    public List<Order> findByStatus(boolean status){

        return orderRepository.findByStatus(status);
    }

    //MÉTODO PARA LISTAR TODOS OS ORDERS
    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
