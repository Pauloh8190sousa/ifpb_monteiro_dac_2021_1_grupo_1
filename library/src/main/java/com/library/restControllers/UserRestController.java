package com.library.restControllers;

import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;

   //MÉTODO PARA LISTAR USERS
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.findAll();
    }

    //MÉTODO PARA LISTAR USERS PELO EMAIL
    @GetMapping("/byEmail")
    public User getUserByEmail(String email) {
        return userService.findByEmail(email);
    }

    //MÉTODO PARA CRIAR UM USER
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User registerUser(@RequestBody User user) {
        return userService.save(user);
    }

}
