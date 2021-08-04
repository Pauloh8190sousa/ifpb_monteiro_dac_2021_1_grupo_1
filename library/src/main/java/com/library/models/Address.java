package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "address_tb")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String nation;
    private String state;
    private String city;
    private String ZipCode;
    private String Neighborhood;
    private String Road;

}
