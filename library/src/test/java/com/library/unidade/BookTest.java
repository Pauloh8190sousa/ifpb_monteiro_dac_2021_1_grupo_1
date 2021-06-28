package com.library.unidade;

import com.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookTest {

    @Autowired
    private BookService bookService;

    @TestConfiguration
    static class BookTestConfig {

        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }

    @Test
    public void saveBook() {
        Book book = bookService.save(new Book("Harry Potter", BigDecimal.valueOf(Double.parseDouble("37")),
                "Um mundo de mágia e mistério", 327, "1234567891023", true));
        assertNotNull(book);
    }

    @Test
    public void findById() {
        Book book = bookService.findById(1L);
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("Um mundo de mágia e mistério", book.getDescription());
        assertEquals(327, book.getNbOfPages());
        assertEquals("1234567891023", book.getIsbn());
        assertTrue(book.isIllustration());
    }

    @Test
    public void findByTitle() {
        List<Book> books = bookService.findByTitle("Harry Potter");
        assertEquals(1, books.get(0).getId());
        assertEquals("Um mundo de mágia e mistério", books.get(0).getDescription());
        assertEquals(327, books.get(0).getNbOfPages());
        assertEquals("1234567891023", books.get(0).getIsbn());
        assertTrue(books.get(0).isIllustration());
    }

    @Test
    public void findAllBook() {
        assertNotNull(bookService.findAll());
    }

    @Test
    public void listCheapBooksAvailable() {
        assertNotNull(bookService.listCheapBooksAvailable());
    }

    @Test
    public void listAllBooks() {
        assertNotNull(bookService.listAllBooks(0));
    }
}
