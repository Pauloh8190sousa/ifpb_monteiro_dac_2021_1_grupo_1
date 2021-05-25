package com.library.services;
import com.library.models.Book;
import com.library.models.Stock;
import com.library.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public void save(Stock stock){

        stockRepository.save(stock);
    }
    public Stock findById(Long stockId){

        return stockRepository.findById(stockId).orElseThrow();
    }
    public Book findBookId(Long stockId, Long bookId){
        Stock stock = stockRepository.findById(stockId).orElseThrow();
        return stock.findBookId(bookId);
    }
    public void addBook(Long stockId, Book book){
        Stock stock = stockRepository.findById(stockId).orElseThrow();
        stock.addBook(book);
        stockRepository.save(stock);
    }
    public void delete(Long stockId){
        Stock s = stockRepository.findById(stockId).orElseThrow();
        stockRepository.delete(s);
    }
    public List<Book> findByStockBook(Long bookId){

        return stockRepository.findByStockBook(bookId);
    }

}
