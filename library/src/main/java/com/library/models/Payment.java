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

    private String paymentCard;

    private Long numberCard;

    private Long CVV;


    public Payment(String paymentCard, Long numberCard, Long CVV, User user){

        this.paymentCard = paymentCard;
        this.numberCard = numberCard;
        this.CVV = CVV;

    }

    public Payment(){
        //Default
        //Constructor
    }

}
