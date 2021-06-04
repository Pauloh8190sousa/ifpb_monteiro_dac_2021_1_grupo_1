package com.library.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "order_tb")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue (strategy = IDENTITY)
    private Long id;
    private BigDecimal totalValue;
    private boolean status;

    @OneToOne
    private User user;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_books_tb", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Book> books = new ArrayList<Book>();

    public Order(boolean status, User user, BigDecimal totalValue){
        this.status = status;
        this.user = user;
        this.totalValue = totalValue;
    }
    public Order(){

    }

}
