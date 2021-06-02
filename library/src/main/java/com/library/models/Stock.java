//package com.library.models;
//
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.List;
//
//import static javax.persistence.GenerationType.IDENTITY;
//
//@Data
//@Entity
//@Table(name = "stock_tb")
//@Getter
//@Setter
//public class Stock {
//    @Id
//    @GeneratedValue(strategy = IDENTITY)
//    private Long id;
//    private int theAmountBooks;
//
//    @OneToMany
//    private List<Book> books;
//
//    public void addBook(Book book){
//
//        books.add(book);
//    }
//
//    public Book findBookId(Long bookId) {
//        for(Book book : books){
//            if(book.getId().longValue() == bookId.longValue()){
//                return book;
//            }
//        }
//        return null;
//    }
//
//}
