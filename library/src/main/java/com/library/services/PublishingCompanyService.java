package com.library.services;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.PublishingCompany;
import com.library.repositories.AuthorRepository;
import com.library.repositories.PublishingCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublishingCompanyService {

    @Autowired
    private PublishingCompanyRepository publishingCompanyRepository;

    public PublishingCompany save(PublishingCompany publishingCompany) {
        return publishingCompanyRepository.save(publishingCompany);
    }

    public List<PublishingCompany> listAllPublishingCompanies(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber,5, Sort.Direction.ASC, "name");
        return publishingCompanyRepository.findAll(pageRequest).getContent();
    }

    public void deleteById(Long idPublishingCompany) {
        PublishingCompany publishingCompany = publishingCompanyRepository.findById(idPublishingCompany).orElseThrow();
        publishingCompanyRepository.delete(publishingCompany);
    }

    public List<PublishingCompany> findByName(String namePublishingCompany) {
        return publishingCompanyRepository.findByName(namePublishingCompany);
    }

    public PublishingCompany findById(Long idPublishingCompany) {
        return publishingCompanyRepository.findById(idPublishingCompany).orElseThrow();
    }

    //MÉTODO PARA LISTAR TODOS OS AUTHORS
    public List<PublishingCompany> findAll() {
        return publishingCompanyRepository.findAll();
    }

    public List<PublishingCompany> findByNameContaining(String name) {
        return publishingCompanyRepository.findByNameContaining(name);
    }

}
