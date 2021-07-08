package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

//CLASSE MODEL PARA BOOK(LIVRO)
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

    private String isbn;

    private boolean illustration;

    private String imageLink;

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
//    @Temporal(TemporalType.DATE)
//    private Date publicationDate;

    private String publicationDate;

    @ManyToMany
    @JoinTable(name = "author_book_tb", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "category_book_tb", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Book(String title, BigDecimal price, String description, int nbOfPages, String isbn, boolean illustration){
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
