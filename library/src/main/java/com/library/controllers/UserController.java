package com.library.controllers;

import com.library.models.User;
import com.library.services.Validation;
import com.library.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//CLASSE CONTROLLER DE USER(USUARIO)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser() {
        return "User/UserForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@Validated User user, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            System.out.println("Erro no campo");
            return "redirect:/register";
        }else{
            userService.save(user);
        }

        return "redirect:/Home";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String sign_inUser() {
        return "User/UserSign_In";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String sign_inUser(User user) {
        Validation validation = new Validation();

        try{
            if(validation.validationEmail(user.getEmail()) &&
                validation.validationPassword(user.getPassword()) &&
                validation.validationUserName(user.getName())){


                userService.save(user);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return "redirect:/Home";
    }

}
