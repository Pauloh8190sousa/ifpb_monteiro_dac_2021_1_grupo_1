package com.library.mockito;

import com.library.models.Book;
import com.library.models.Validation;
import com.library.repositories.BookRepository;
import com.library.services.BookService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class BookTestMockito {

    @Autowired
    private BookService bookService;
//
    @Spy
    private Book book;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private Validation validation;

    @TestConfiguration
    static class BookTestConfig {

        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }

    @BeforeEach
    public void initial() {
        book.setId(1L);
        book.setIllustration(true);
        book.setDescription("livro de Harry Potter");
        book.setPrice(BigDecimal.valueOf(20));
        book.setTitle("A pedra filosofal");
        book.setNbOfPages(500);
        book.setStock(50);
        book.setPublicationDate("10/07/2005");
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
        assertTrue(validation.validationISBN("9858746623875"));
        assertFalse(validation.validationISBN("98587465"));
    }

    @Test
    void validationPrice() {
        BigDecimal price = BigDecimal.valueOf(150.20);
        assertTrue(validation.validationPrice(price));

        price = BigDecimal.valueOf(-20);
        assertFalse(validation.validationPrice(price));
    }

    @Test
    public void validationDateOfBook() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("30/07/2007");

        assertTrue(validation.validationDateOfBook(date));

        date = sdf.parse("05/09/2050");
        assertFalse(validation.validationDateOfBook(date));
    }

    @Test
    public void stockBook() {
        assertTrue(validation.validationStock(3));
        assertFalse(validation.validationStock(-10));
        assertFalse(validation.validationStock(1001));
    }

    @Test
    public void pageLimit() {
        assertTrue(validation.pageLimit(327));
        assertFalse(validation.pageLimit(0));
    }

    @Test
    public void validationTitle() {
        assertTrue(validation.validationTitle("Harry Potter"));
        assertFalse(validation.validationTitle(""));
        assertFalse(validation.validationTitle("kmdsk sdnksdn ndsnkdnks ndksndksn nksndknd knsdks" +
                "dmskdmks ndskndks ndksndk sndksnd cnjdnj fbdjbf jbfdjbfjd jbdfjbdjf jdsdknd"));
    }

    @Test
    public void validationDescription() {
        assertTrue(validation.validationDescriptionBook("Um mundo de magia e mistério"));
        assertFalse(validation.validationDescriptionBook(""));
    }
}
