package com.library.facades;

import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    public void saveUser(String name, String email) {
        User user = new User(name, email);
        userService.save(user);
    }

    public List<User> findByEmail(String emailUser) {
        return userService.findByEmail(emailUser);
    }

}
