package com.library.models;

import com.library.services.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
                "Um mundo de magia e mistério", 327, "1234567891023", true));
        assertNotNull(book);
    }

    @Test
    public void findById() {
        Book book = bookService.findById(2L);
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("Um mundo de magia e mistério", book.getDescription());
        assertEquals(327, book.getNbOfPages());
        assertEquals("1234567891023", book.getIsbn());
        assertTrue(book.isIllustration());
    }

    @Test
    public void findByTitle() {
        List<Book> books = bookService.findByTitle("Harry Potter");
        assertEquals(2, books.get(0).getId());
        assertEquals("Um mundo de magia e mistério", books.get(0).getDescription());
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


    //<---------- VALIDAÇÃO ---------->


    @Test
    void validationISBN() {
        assertTrue(Validation.validationISBN("9858746623875"));
        assertFalse(Validation.validationISBN("98587465"));
    }

    @Test
    void validationPrice() {
        BigDecimal price = BigDecimal.valueOf(150.20);
        assertTrue(Validation.validationPrice(price));

        price = BigDecimal.valueOf(-20);
        assertFalse(Validation.validationPrice(price));
    }

    @Test
    public void validationDateOfBook() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("30/07/2007");

        assertTrue(Validation.validationDateOfBook(date));

        date = sdf.parse("05/09/2050");
        assertFalse(Validation.validationDateOfBook(date));
    }

    @Test
    public void stockBook() {
        assertTrue(Validation.validationStock(3));
        assertFalse(Validation.validationStock(-10));
        assertFalse(Validation.validationStock(1001));
    }

    @Test
    public void pageLimit() {
        assertTrue(Validation.pageLimit(327));
        assertFalse(Validation.pageLimit(0));
    }

    @Test
    public void validationTitle() {
        assertTrue(Validation.validationTitle("Harry Potter"));
        assertFalse(Validation.validationTitle(""));
        assertFalse(Validation.validationTitle("kmdsk sdnksdn ndsnkdnks ndksndksn nksndknd knsdks" +
                "dmskdmks ndskndks ndksndk sndksnd cnjdnj fbdjbf jbfdjbfjd jbdfjbdjf jdsdknd"));
    }

    @Test
    public void validationDescription() {
        assertTrue(Validation.validationDescriptionBook("Um mundo de magia e mistério"));
        assertFalse(Validation.validationDescriptionBook(""));
    }

}
