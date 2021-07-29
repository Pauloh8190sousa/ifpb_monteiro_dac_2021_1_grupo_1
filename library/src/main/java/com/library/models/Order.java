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
@Table(name = "order_tb")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long id;
    private boolean open;
    private float totalOrderPrice = 0;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private List<OrderBook> orderBooks = new ArrayList<>();

    @ManyToOne
    private User user;

//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name = "order_books_tb", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private List<Book> books = new ArrayList<Book>();

    public Order(boolean open, User user){
        this.setOpen(open);
        this.user = user;
    }
    public Order(){ }

}
