package com.library.repositories;

import com.library.unidade.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//CLASSE REPOSITORY DE USER(USUÁRIO)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //MÉTODO PARA LISTAR USERS PELO EMAIL
    public List<User> findByEmail(String emailUser);

}
