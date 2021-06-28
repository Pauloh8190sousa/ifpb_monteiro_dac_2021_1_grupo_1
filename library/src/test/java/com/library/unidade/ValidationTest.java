package com.library.unidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void validationEmail() {
        String emailEntrada = "ph@gmail.com";
        boolean saida = Validation.validationEmail(emailEntrada);
        assertEquals(true,saida);
    }

    @Test
    void validationISBN() {
        String number = "9858746623875";
        boolean saida = Validation.validationISBN(number);
        assertEquals(true,saida);
    }

    @Test
    void validationPrice() {
        BigDecimal price = BigDecimal.valueOf(150.20);
        boolean saida = Validation.validationPrice(price);
        assertEquals(true,saida);
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
    public void validationPassword(){
        String password = "paulo";
        boolean saida = Validation.validationPassword(password);
        assertEquals(false,saida);
    }
    @Test
    void validationBibliographicReference() {
        String reference = "Sousa P. H. P";
        boolean saida = Validation.validationBibliographicReference(reference);
        assertEquals(true,saida);
    }
}