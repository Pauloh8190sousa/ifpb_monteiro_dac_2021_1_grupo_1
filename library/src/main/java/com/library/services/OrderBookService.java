package com.library.services;

import com.library.models.Book;
import com.library.models.Order;
import com.library.models.OrderBook;
import com.library.repositories.BookRepository;
import com.library.repositories.OrderBookRepository;
import com.library.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//CLASSE DE SERVIÇOS PARA ORDER(PEDIDO)
@Service
public class OrderBookService {

    @Autowired
    private OrderBookRepository orderBookRepository;

    //MÉTODO PARA SALVAR UM ORDERBOOK
    public OrderBook save(OrderBook orderBook){

        return orderBookRepository.save(orderBook);
    }



//    //MÉTODO PARA LISTAR TODOS OS BOOKS EM UM ORDER
//    public List<Book> listAllBooks(Long orderId){
//        Order order = orderRepository.findById(orderId).orElseThrow();
//        return order.getBooks();
//    }

    //MÉTODO PARA DELETAR UM ORDER PELO ID
//    public void delete(Long orderId){
//        Order order = orderRepository.findById(orderId).orElseThrow();
//        orderRepository.delete(order);
//    }


//    //MÉTODO PARA LISTAR TODOS OS ORDERS
//    public List<Order> findAll(){
//        return orderRepository.findAll();
//    }
}
