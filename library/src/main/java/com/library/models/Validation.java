package com.library.models;

import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class Validation {

//    private Validation validation;
//
//    private Validation(){
//
//    }

//    public synchronized Validation getInstance(){
//        if(validation == null){
//            validation = new Validation();
//        }
//        return validation;
//    }

    public boolean validationEmail(String email) throws Exception {
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

    public boolean validationISBN(String number) throws Exception {
        String numero = String.valueOf(number);
        if(numero.length()!=13){
            throw new Exception("ISBN inválido");
        }
        return true;
    }
    public boolean validationPrice(BigDecimal price) throws Exception {
        if(price.doubleValue() < 0){
            throw new Exception("Preço inválido");
        }
        return true;
    }
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

    public boolean validationDateOfBook(Date date) {

        Date currentDate = new Date(System.currentTimeMillis());

        return !date.after(currentDate);
    }

    public boolean validationPassword(String password) throws Exception {
        if(password.length()<=4 || !password.matches("[A-Za-z0-9]")){
            throw new Exception("Senha inválida");
        }
        return true;
    }

    public boolean validationUserName(String userName) throws Exception {
        if(userName.length() < 3 || userName.length() > 20){
            throw new Exception("Nome inválido");
        }
        return true;
    }

    public boolean duplicateEmail(User user, String userEmail) {
        if(user.getEmail().equals(userEmail)) {
            return false;
        }
        return true;
    }

    public boolean validationStock(int stock) throws Exception {
        if(stock <= 0 || stock > 1000) {
            return false;
        }
        return true;
    }

    public boolean pageLimit(int nbOfPages) throws Exception {
        if(nbOfPages <= 0 || nbOfPages > 10000) {
            return false;
        }
        return true;
    }

    public boolean validationTitle(String bookTitle) {
       if(bookTitle.length() <= 0 || bookTitle.length() > 100) {
           return false;
       }
       return true;
    }

    public boolean validationDescriptionBook(String bookDescription) {
       if(bookDescription.length() <= 0 || bookDescription.length() > 300) {
           return false;
       }
       return true;
    }

    public String redefinePassword(String newPassword) {
        return newPassword;
    }

    public Book changeBookIfSaved(Book book) {
        return book;
    }

}
