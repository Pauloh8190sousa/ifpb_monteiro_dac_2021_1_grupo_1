package com.library.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String description;

    private int numberPages;

    private int ISBN;

    private boolean illustration;


}
