package com.library.facades;

import com.library.models.User;
import com.library.services.UserService;

public class UserFacade {

    UserService userService = new UserService();
    User user;

    public void userRegister(String name, String email) {
        user = new User();
        user.setName(name);
        user.setEmail(email);

        userService.save(user);
    }

}
