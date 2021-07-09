package com.library.controllers;

import com.library.models.Category;
import com.library.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//CLASSE CONTROLLER DE CATEGORY(CATEGORIA)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/createCategory", method = RequestMethod.GET)
    public String createCategory() {
        return "Category/CategoryForm";
    }

    @RequestMapping(value = "/createCategory", method = RequestMethod.POST)
    public String createCategory(Category category) {

        categoryService.save(category);

        return "redirect:/Home";
    }
}
