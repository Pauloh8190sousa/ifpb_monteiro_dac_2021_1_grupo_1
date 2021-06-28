package com.library.services;

import com.library.unidade.User;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//CLASSE DE SERVIÇOS PARA USER(USUARIO)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //MÉTODO PARA SALVAR UM USER
    public User save(User user) {

        return userRepository.save(user);
    }

    //MÉTODO PARA DELETAR UM USER PELO ID
    public void delete(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow();
        userRepository.delete(user);
    }

    //MÉTODO PARA LISTAR USERS PELO EMAIL
    public List<User> findByEmail(String emailUser) {

        return userRepository.findByEmail(emailUser);
    }

    //MÉTODO PARA CONSULTAR UM USER PELO ID
    public User findById(Long idUser) {

        return userRepository.findById(idUser).orElseThrow();
    }

    //MÉTODO PARA LISTAR TODOS OS USERS
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
