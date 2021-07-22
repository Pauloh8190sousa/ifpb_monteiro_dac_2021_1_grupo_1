package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

//CLASSE MODEL PARA USER(USUÁRIO)
@Data
@Entity
@Table(name = "user_tb")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    public User(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
    }

    public User() {

    }

}
