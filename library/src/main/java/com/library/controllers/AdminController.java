package com.library.controllers;

import com.library.models.Category;
import com.library.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/AdminPanel", method = RequestMethod.GET)
    public String createCategory() {
        return "Admin/AdminPanel";
    }

}
