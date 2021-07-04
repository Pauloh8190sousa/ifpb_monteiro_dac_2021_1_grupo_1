package com.library.repositories;

import com.library.models.Order;
import com.library.models.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//CLASSE REPOSITORY DE ORDERBOOK(LIVRO PEDIDO)
@Repository
public interface OrderBookRepository extends JpaRepository<OrderBook, Long> {

}
