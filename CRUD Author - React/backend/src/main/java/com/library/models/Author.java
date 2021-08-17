package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


import static javax.persistence.GenerationType.IDENTITY;

//CLASSE MODEL PARA AUTHOR(AUTOR)
@Data
@Entity
@Table(name = "author_tb")
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String bibliographicReference;

    public Author(String name, String bibliographicReference){
        this.name = name;
        this.bibliographicReference = bibliographicReference;
    }
    public Author(){

    }

}
