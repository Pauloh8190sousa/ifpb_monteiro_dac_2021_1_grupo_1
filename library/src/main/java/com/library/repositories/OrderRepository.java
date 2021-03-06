package com.library.repositories;

import com.library.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//CLASSE REPOSITORY DE ORDER(PEDIDO)
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    //MÉTODO PARA LISTAR ORDERS PELO STATUS
    public List<Order> findByOpen(boolean status);
}
