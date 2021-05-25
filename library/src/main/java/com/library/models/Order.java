package com.library.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
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
    private List<Book> orderBooks;

    public void addOrderBook(Book book){

        orderBooks.add(book);
    }


}
