package com.library.repositories;


import com.library.models.Book;
import com.library.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    public List<Book> findByStockBook(Long bookId);
}
