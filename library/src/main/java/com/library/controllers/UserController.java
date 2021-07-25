package com.library.controllers;

import com.library.models.User;
import com.library.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CLASSE CONTROLLER DE USER(USUARIO)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser() {
        return "User/UserForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(User user) {

        userService.save(user);

        return "redirect:/Home";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String sign_inUser() {
        return "User/UserSign_In";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String sign_inUser(User user) {

        userService.save(user);

        return "redirect:/Home";
    }

//   //MÉTODO PARA LISTAR USERS
//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userService.findAll();
//    }
//
//    //MÉTODO PARA LISTAR USERS PELO EMAIL
//    @GetMapping("/byEmail")
//    public List<User> getUserByEmail(String email) {
//        return userService.findByEmail(email);
//    }
//
//    //MÉTODO PARA CRIAR UM USER
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User registerUser(@RequestBody User user) {
//        return userService.save(user);
//    }

}
