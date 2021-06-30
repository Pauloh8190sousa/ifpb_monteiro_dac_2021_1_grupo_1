package com.library.models;

import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class Validation {

    private static Validation validation;

    private Validation(){

    }

    public synchronized static Validation getInstance(){
        if(validation == null){
            validation = new Validation();
        }
        return validation;
    }

    public static boolean validationEmail(String email){
        String regex = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+";
        String regex2 = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+";
        String regex3 = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+";
        String regex4 = "[A-Za-z0-9\\._-]+@[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+\\.[A-Za-z]+";

        if(email.matches(regex) ||
           email.matches(regex2) ||
           email.matches(regex3) ||
           email.matches(regex4)){
            return true;
        }
        return false;
    }

    public static boolean validationISBN(String number){
        String numero = String.valueOf(number);
        if(numero.length()==13){
            return true;
        }
        return false;
    }
    public static boolean validationPrice(BigDecimal price){
        if(price.doubleValue() < 0){
            return false;
        }
        return true;
    }
    //metodo corrigido
    public static boolean validationBibliographicReference(String bibliographic){
        String regex = "[A-Za-z].+";
        if(bibliographic.matches(regex)){
            if(bibliographic.contains(" ") && bibliographic.contains(".")) {
                return true;
            }
        }
        return false;
    }

    public static boolean validationDateOfBook(Date date) {

        Date currentDate = new Date(System.currentTimeMillis());

        return !date.after(currentDate);
    }

    public static boolean validationPassword(String password){
        if(password.length()>4 && password.matches("[A-Za-z0-9]")){
            return true;
        }
        return false;
    }

    public static boolean validationUserName(String userName) {
        if(userName.length() >= 3 && userName.length()<=20){
            return true;
        }
        return false;
    }

    public static boolean duplicateEmail(User user, String userEmail) {
        if(user.getEmail().equals(userEmail)) {
            return false;
        }
        return true;
    }

    public static boolean validationStock(int stock) {
        if(stock <= 0 || stock > 1000) {
            return false;
        }
        return true;
    }

    public static boolean pageLimit(int nbOfPages) {
        if(nbOfPages <= 0 || nbOfPages > 10000) {
            return false;
        }
        return true;
    }

    public static boolean validationTitle(String bookTitle) {
       if(bookTitle.length() <= 0 || bookTitle.length() > 100) {
           return false;
       }
       return true;
    }

    public static boolean validationDescriptionBook(String bookDescription) {
       if(bookDescription.length() <= 0 || bookDescription.length() > 300) {
           return false;
       }
       return true;
    }

}
