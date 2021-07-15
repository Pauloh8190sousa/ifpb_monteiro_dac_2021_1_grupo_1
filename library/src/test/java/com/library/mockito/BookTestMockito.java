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
import static org.assertj.core.api.Assertions.assertThat;
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

        Date trueDate = sdf.parse("30/07/2007");
        when(validation.validationDateOfBook(trueDate)).thenReturn(true);
        assertTrue(validation.validationDateOfBook(trueDate));

        Date falseDate = sdf.parse("05/09/2050");
        when(validation.validationDateOfBook(falseDate)).thenReturn(false);
        assertFalse(validation.validationDateOfBook(falseDate));

        verify(validation, times(1)).validationDateOfBook(trueDate);
        verify(validation, times(1)).validationDateOfBook(falseDate);
    }

    @Test
    public void stockBook() throws Exception {
        when(validation.validationStock(3)).thenReturn(true);
        when(validation.validationStock(-10)).thenThrow(new Exception("Número negativo não permitido"));
        when(validation.validationStock(1001)).thenThrow(new Exception("Estoque maior que mil não permitido"));

        assertThrows(Exception.class, () -> validation.validationStock(-10));
        assertThrows(Exception.class, () -> validation.validationStock(1001));
        assertTrue(validation.validationStock(3));

        String errorMessage = "";
        try {
            validation.validationStock(-10);
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Número negativo não permitido", errorMessage);

        try {
            validation.validationStock(1001);
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Estoque maior que mil não permitido", errorMessage);

        verify(validation, times(1)).validationStock(3);
        verify(validation, times(2)).validationStock(-10);
        verify(validation, times(2)).validationStock(1001);
    }

    @Test
    public void pageLimit() throws Exception {
        when(validation.pageLimit(327)).thenReturn(true);
        when(validation.pageLimit(0)).thenThrow(new Exception("Número de páginas tem que ser maior que 0"));
        when(validation.pageLimit(-15)).thenThrow(new Exception("Número de páginas tem que ser positivo"));

        assertThrows(Exception.class, () -> validation.pageLimit(0));
        assertThrows(Exception.class, () -> validation.pageLimit(-15));
        assertTrue(validation.pageLimit(327));

        String errorMessage = "";
        try {
            validation.pageLimit(0);
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Número de páginas tem que ser maior que 0", errorMessage);

        try {
            validation.pageLimit(-15);
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Número de páginas tem que ser positivo", errorMessage);

        verify(validation, times(2)).pageLimit(0);
        verify(validation, times(2)).pageLimit(-15);
        verify(validation, times(1)).pageLimit(327);

    }

    @Test
    public void validationTitle() {
        when(validation.validationTitle("Harry Potter")).thenReturn(true);
        when(validation.validationTitle("")).thenReturn(false);
        when(validation.validationTitle("kmdsk sdnksdn ndsnkdnks ndksndksn nksndknd knsdks dmskdmks ndskndks ndksndk sndksnd cnjdnj fbdjbf jbfdjbfjd jbdfjbdjf jdsdknd"))
                .thenReturn(false);

        assertTrue(validation.validationTitle("Harry Potter"));
        assertFalse(validation.validationTitle(""));
        assertFalse(validation.validationTitle("kmdsk sdnksdn ndsnkdnks ndksndksn nksndknd knsdks" +
                "dmskdmks ndskndks ndksndk sndksnd cnjdnj fbdjbf jbfdjbfjd jbdfjbdjf jdsdknd"));

        verify(validation, times(1)).validationTitle("Harry Potter");
        verify(validation, times(1)).validationTitle("");
        verify(validation, times(1)).validationTitle("kmdsk sdnksdn ndsnkdnks ndksndksn nksndknd knsdks" +
                "dmskdmks ndskndks ndksndk sndksnd cnjdnj fbdjbf jbfdjbfjd jbdfjbdjf jdsdknd");
    }

    @Test
    public void validationDescription() {
        when(validation.validationDescriptionBook("Um mundo de magia e mistério")).thenReturn(true);
        when(validation.validationDescriptionBook("")).thenReturn(false);

        assertThat(validation.validationDescriptionBook("Um mundo de magia e mistério")).isEqualTo(true);
        assertThat(validation.validationDescriptionBook("")).isEqualTo(false);

        verify(validation, times(1)).validationDescriptionBook("Um mundo de magia e mistério");
        verify(validation, times(1)).validationDescriptionBook("");
    }

    //<---------- FUTURAS IMPLEMENTAÇÕES NO SISTEMA ---------->


}
