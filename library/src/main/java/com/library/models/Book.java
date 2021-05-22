package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private BigDecimal price;

    private String description;

    private int nbOfPage;

    private int ISBN;

    private boolean illustration;

    @ManyToMany
    @JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "author_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Author> authors;


}
