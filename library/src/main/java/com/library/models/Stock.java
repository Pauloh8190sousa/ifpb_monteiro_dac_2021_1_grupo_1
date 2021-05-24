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
public class Stock {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String catalog;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.MERGE)
    private List<Book> booksList;

    public void addBook(Book book){

        booksList.add(book);
    }

    public Book findBookId(Long bookId){
        for(Book book : booksList){
            if(book.getId().longValue() == bookId.longValue()){
                return book;
            }
        }
        return null;
    }
}
