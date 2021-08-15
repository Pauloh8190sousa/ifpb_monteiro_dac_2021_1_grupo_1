package com.library.repositories;

import com.library.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//CLASSE REPOSITORY DE USER(USUÁRIO)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //MÉTODO PARA LISTAR USERS PELO EMAIL
    User findByEmail(String emailUser);

    //MÉTODO PARA LISTAR USERS PELO NOME
    User findByName(String nameUser);



}
