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
    private BigDecimal price;
    private boolean status;

    @ManyToMany(mappedBy = "books_order", cascade = CascadeType.MERGE)
    private List<Book> books;

    public void addOrderBook(Book book){

        books.add(book);
    }
}
