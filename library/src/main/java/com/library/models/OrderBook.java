package com.library.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

//CLASSE MODEL PARA ORDER(PEDIDO)
@Data
@Entity
@Table(name = "order_book_tb")
@Getter
@Setter
public class OrderBook {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long id;

    private BigDecimal totalValue;

    private BigDecimal amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id")
    private Book book;


}
