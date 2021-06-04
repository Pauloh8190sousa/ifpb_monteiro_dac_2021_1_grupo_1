package com.library.facades;

import com.library.models.Book;
import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    public User saveUser(String name, String email) {
        User user = new User(name, email);
        return userService.save(user);
    }

    public List<User> findByEmail(String emailUser) {
        return userService.findByEmail(emailUser);
    }

    public User selectUser() {
        Scanner read = new Scanner(System.in);

        List<User> users = userService.findAll();

        for (User user: users) {
            System.out.println("Id: "+ user.getId()+ "   Título: "+ user.getName() );
        }

        System.out.println("Selecione um usuário pelo Id: ");
        Long userId = Long.parseLong(read.nextLine());

        return userService.findById(userId);
    }

}
