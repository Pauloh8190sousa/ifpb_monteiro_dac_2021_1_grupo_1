package com.library.unidade;

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

    public User(String name, String email) {
        this.setName(name);
        this.setEmail(email);
    }

    public User() {
    }

}
