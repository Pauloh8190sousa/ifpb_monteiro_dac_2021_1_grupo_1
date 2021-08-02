package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String email;

    private String name;

    private String address;

    private String city;

    private String estate;

    private Long CEP;

    private String deliveryAddress;

    private String paymentCard;

    private Long numberCard;

    private String expirationDate;

    public Payment(String email, String name, String address, String city, String estate, Long CEP, String deliveryAddress,
                   String paymentCard, Long numberCard, String expirationDate){

        this.email = email;
        this.name = name;
        this.address = address;
        this.city = city;
        this. estate = estate;
        this.CEP = CEP;
        this.deliveryAddress = deliveryAddress;
        this.paymentCard = paymentCard;
        this.numberCard = numberCard;
        this.expirationDate = expirationDate;

    }

    public Payment(){
        //Default
        //Constructor
    }

}
