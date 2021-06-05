package com.library.controllers;

import com.library.models.User;
import com.library.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//CLASSE CONTROLLER DE USER(USUARIO)
@Slf4j //Faz o log da classe para poder tratar erros
@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    UserService userService;
//
    //MÉTODO PARA LISTAR USERS
//    @GetMapping("/users")
//    public List<User> getUsers() {
//        return userService.findAll();
//    }
//
    //MÉTODO PARA LISTAR USERS PELO EMAIL
//    @GetMapping("/byEmail")
//    public List<User> getUserByEmail(String email) {
//        return userService.findByEmail(email);
//    }
//
    //MÉTODO PARA CRIAR UM USER
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public User registerUser(@RequestBody User user) {
//        return userService.save(user);
//    }

}
