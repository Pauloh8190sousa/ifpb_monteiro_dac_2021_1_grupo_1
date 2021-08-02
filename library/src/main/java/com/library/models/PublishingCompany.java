package com.library.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "publishing_company_tb")
@Getter
@Setter
public class PublishingCompany {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String city;

    @ManyToMany(mappedBy = "publishingCompanies", cascade = CascadeType.MERGE)
    private List<Book> books;

}
