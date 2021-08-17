package com.library.repositories;

import com.library.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//CLASSE REPOSITORY DE CATEGORY(CATEGORIA)
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    //MÉTODO PARA LISTAR CATEGORIES PELO NOME
    public List<Category> findByType(String categoryType);

    List<Category> findByTypeContaining(String type);

}
