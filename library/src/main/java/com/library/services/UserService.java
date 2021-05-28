package com.library.services;

import com.library.models.User;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {

        return userRepository.save(user);
    }

    public void delete(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow();
        userRepository.delete(user);
    }

    public List<User> findByEmail(String emailUser) {

        return userRepository.findByEmail(emailUser);
    }

    public User findById(Long idUser) {

        return userRepository.findById(idUser).orElseThrow();
    }
}
