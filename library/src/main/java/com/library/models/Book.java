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
@Table(name = "book_tb")
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private int stock;

    private BigDecimal price;

    private String description;

    private int nbOfPages;

    private int isbn;

    private boolean illustration;

    @ManyToMany
    @JoinTable(name = "author_book_tb", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Author> authors;

    public Book(String title, BigDecimal price, String description, int nbOfPages, int isbn, boolean illustration){
        this.title = title;
        this.price = price;
        this.description = description;
        this.nbOfPages = nbOfPages;
        this.isbn = isbn;
        this.illustration = illustration;
    }
    public Book(){

    }

}
