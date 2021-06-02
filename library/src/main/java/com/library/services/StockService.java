//package com.library.services;
//import com.library.models.Book;
//import com.library.models.Stock;
//import com.library.repositories.StockRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class StockService {
//
//    @Autowired
//    private StockRepository stockRepository;
//
//    public Stock save(Stock stock){
//
//        return stockRepository.save(stock);
//    }
//    public Stock findById(Long stockId){
//
//        return stockRepository.findById(stockId).orElseThrow();
//    }
//    public Book findBookId(Long stockId, Long bookId){
//        Stock stock = stockRepository.findById(stockId).orElseThrow();
//        return stock.findBookId(bookId);
//    }
//    public Stock addBookToStock(Stock stock, Book book) {
//        Stock usedStock  = stockRepository.findById(stock.getId()).orElseThrow();
//        usedStock.addBook(book);
//
//        return stockRepository.save(stock);
//    }
//    public List<Book> listAllBooks(Long stockId){
//        Stock stock = stockRepository.findById(stockId).orElseThrow();
//        return stock.getBooks();
//    }
//    public void delete(Long stockId){
//        Stock s = stockRepository.findById(stockId).orElseThrow();
//        stockRepository.delete(s);
//    }
//    public List<Stock> findAll(){
//        return stockRepository.findAll();
//    }
////    public List<Book> findByStockBook(Long bookId){
////
////        return stockRepository.findByBook(bookId);
////    }
//
//}
