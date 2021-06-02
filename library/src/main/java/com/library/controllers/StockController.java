//package com.library.controllers;
//import com.library.models.Book;
//import com.library.models.Stock;
//import com.library.services.StockService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/")
//public class StockController {
//    @Autowired
//    StockService stockService;
//
////    @GetMapping
////    public List<Stock> getStocks() {
////        return stockService.findAll();
////    }
////    @GetMapping("/books_stock/{id}")
////    public List<Book> listAllBooks(@PathVariable(value = "id") Long stockId){
////        return stockService.listAllBooks(stockId);
////    }
////    @PostMapping
////    @ResponseStatus(HttpStatus.CREATED)
////    public Stock postStocks(@RequestBody Stock stock) {
////        return stockService.save(stock);
////    }
////    @PostMapping("/{id}")
////    @ResponseStatus(HttpStatus.CREATED)
////    public void addBook(@PathVariable(value = "id") Long id, @RequestBody Book book){
////            stockService.addBook(id,book);
////    }
//}
