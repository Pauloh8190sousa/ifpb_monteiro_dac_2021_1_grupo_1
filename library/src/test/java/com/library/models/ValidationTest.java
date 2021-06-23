package com.library.models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

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


//    @Test
//    void validationBibliographicReference() {
//        String reference = "Paulo #enrique sousa";
//        boolean saida = Validation.validationBibliographicReference(reference);
//        assertEquals(true,saida);
//    }
}