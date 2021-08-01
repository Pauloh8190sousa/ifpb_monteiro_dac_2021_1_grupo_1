package com.library.repositories;

import com.library.models.Author;
import com.library.models.Order;
import com.library.models.OrderBook;
import com.library.models.PublishingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublishingCompanyRepository extends JpaRepository<PublishingCompany, Long> {

    public List<PublishingCompany> findByName(String namePublishingCompany);

}
