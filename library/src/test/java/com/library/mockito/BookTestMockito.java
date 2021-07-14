package com.library.mockito;

import com.library.models.Book;
import com.library.models.User;
import com.library.models.Validation;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
import com.library.repositories.UserRepository;
import com.library.services.BookService;

import com.library.services.UserService;
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

    @Spy
    private Book book;

    @TestConfiguration
    static class BookTestMockitoConfig {

        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private Validation validation;

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
    void validationISBN() throws Exception {
        when(validation.validationISBN("9858746623875")).thenReturn(true);
        when(validation.validationISBN("98587465")).thenThrow(new Exception("ISBN menor que o esperado"));
        when(validation.validationISBN("9858746523569765212")).thenThrow(new Exception("ISBN maior que o esperado"));

        assertTrue(validation.validationISBN("9858746623875"));
        assertThrows(Exception.class, () -> validation.validationISBN("98587465"));
        assertThrows(Exception.class, () -> validation.validationISBN("9858746523569765212"));

        String errorMessage = "";
        try {
            validation.validationISBN("98587465");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("ISBN menor que o esperado", errorMessage);

        try {
            validation.validationISBN("9858746523569765212");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("ISBN maior que o esperado", errorMessage);

        verify(validation, times(1)).validationISBN("9858746623875");
        verify(validation, times(2)).validationISBN("98587465");
        verify(validation, times(2)).validationISBN("9858746523569765212");
    }

    @Test
    void validationPrice() throws Exception {
        when(validation.validationPrice(BigDecimal.valueOf(150.20))).thenReturn(true);
        when(validation.validationPrice(BigDecimal.valueOf(-20))).thenThrow(new Exception());

        assertTrue(validation.validationPrice(BigDecimal.valueOf(150.20)));
        assertThrows(Exception.class, () -> validation.validationPrice(BigDecimal.valueOf(-20)));

        verify(validation, times(1)).validationPrice(BigDecimal.valueOf(150.20));
        verify(validation, times(1)).validationPrice(BigDecimal.valueOf(-20));
    }

    @Test
    public void validationDateOfBook() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("30/07/2007");

        assertTrue(validation.validationDateOfBook(date));

        date = sdf.parse("05/09/2050");
        assertFalse(validation.validationDateOfBook(date));
    }
//
//    @Test
//    public void stockBook() {
//        assertTrue(validation.validationStock(3));
//        assertFalse(validation.validationStock(-10));
//        assertFalse(validation.validationStock(1001));
//    }
//
//    @Test
//    public void pageLimit() {
//        assertTrue(validation.pageLimit(327));
//        assertFalse(validation.pageLimit(0));
//    }
//
//    @Test
//    public void validationTitle() {
//        assertTrue(validation.validationTitle("Harry Potter"));
//        assertFalse(validation.validationTitle(""));
//        assertFalse(validation.validationTitle("kmdsk sdnksdn ndsnkdnks ndksndksn nksndknd knsdks" +
//                "dmskdmks ndskndks ndksndk sndksnd cnjdnj fbdjbf jbfdjbfjd jbdfjbdjf jdsdknd"));
//    }
//
//    @Test
//    public void validationDescription() {
//        assertTrue(validation.validationDescriptionBook("Um mundo de magia e mistério"));
//        assertFalse(validation.validationDescriptionBook(""));
//    }

    //<---------- FUTURAS IMPLEMENTAÇÕES NO SISTEMA ---------->


}
