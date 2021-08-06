package com.library.services;
import com.library.models.Category;
import com.library.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category save(Category category) {

        return categoryRepository.save(category);
    }

    //MÉTODO PARA DELETAR UMA CATEGORY PELO ID
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }

    //MÉTODO PARA LISTAR CATEGORIES PELO NOME
    public List<Category> findByType(String type) {

        return categoryRepository.findByType(type);
    }

    //MÉTODO PARA CONSULTAR UMA CATEGORY PELO ID
    public Category findById(Long id) {

        return categoryRepository.findById(id).orElseThrow();
    }

    public List<Category> listAllCategories(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber,5, Sort.Direction.ASC, "type");
        return categoryRepository.findAll(pageRequest).getContent();
    }

    //MÉTODO PARA LISTAR TODOS OS USERS
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
