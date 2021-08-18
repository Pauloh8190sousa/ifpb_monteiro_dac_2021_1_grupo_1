package com.library.controllers;

import com.library.models.Author;
import com.library.models.Category;
import com.library.services.CategoryService;
import com.library.services.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

        return "redirect:/listCategory";
    }

    @PostMapping("/listCategory/{id}")
    public ModelAndView editCategory(Category category) {
        categoryService.save(category);

        return new ModelAndView("Admin/CategoryConfig");
    }

    @GetMapping("/listCategory/{id}")
    public ModelAndView editCategory(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Admin/CategoryEdit");
        Category category = categoryService.findById(id);

        modelAndView.addObject("category", category);

        return modelAndView;
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteAuthor(@PathVariable("id") long id) {

        categoryService.delete(id);

        return "redirect:/listCategory";
    }


    @RequestMapping("/listCategory/page/{action}")
    public ModelAndView AuthorList(@PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Admin/CategoryConfig");
        List<Category> categories;
        if(action != null) {
            categories = categoryService.listAllCategories(action);

        }else{
            categories = categoryService.listAllCategories(0);
        }

        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    @GetMapping("/listCategory")
    public ModelAndView listAuthorPageable() {
        ModelAndView modelAndView = new ModelAndView("Admin/CategoryConfig");
        List<Category> categories = categoryService.listAllCategories(0);

        modelAndView.addObject("categories", categories);

        return modelAndView;
    }

    @GetMapping("/listSearchCategory")
    public ModelAndView listCategorySearch(@RequestParam(defaultValue = "") String type) {

        List<Category> categories = categoryService.findByTypeContaining(type);

        ModelAndView mv = new ModelAndView("Admin/CategoryConfig");

        mv.addObject("categories",categories);

        return mv;
    }
}
