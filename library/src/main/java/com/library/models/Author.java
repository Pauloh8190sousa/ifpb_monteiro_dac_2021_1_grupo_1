package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String bibliographicReference;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.MERGE)
    private List<Book> books;

}
