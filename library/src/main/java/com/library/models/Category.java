package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "category_tb")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String type;

    @ManyToMany(mappedBy = "categories", cascade = CascadeType.MERGE)
    private List<Book> books;
}
