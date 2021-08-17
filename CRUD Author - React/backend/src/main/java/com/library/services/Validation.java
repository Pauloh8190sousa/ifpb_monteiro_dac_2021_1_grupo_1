package com.library.services;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class Validation {

    //metodo corrigido
    public boolean validationBibliographicReference(String bibliographic){
        String regex = "[A-Za-z].+";
        if(bibliographic.matches(regex)){
            if(bibliographic.contains(" ") && bibliographic.contains(".")) {
                return true;
            }
        }
        return false;
    }

}
