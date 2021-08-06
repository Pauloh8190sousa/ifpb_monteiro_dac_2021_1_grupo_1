package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "payment_tb")
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String typeCard;

    private String nameCard;

    private Long numberCard;

    private Long CVV;


    public Payment(String typeCard, Long numberCard, Long CVV){

        this.typeCard = typeCard;
        this.numberCard = numberCard;
        this.CVV = CVV;

    }

    public Payment(){
        //Default
        //Constructor
    }

}
