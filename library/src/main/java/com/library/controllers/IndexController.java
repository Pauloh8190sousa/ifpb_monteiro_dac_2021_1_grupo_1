package com.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//CLASSE CONTROLLER DO INDEX DA APLICACAO
@Controller
@RequestMapping("/")
public class IndexController {

    //MÉTODO PARA RETORNAR PAGINA
    @RequestMapping
    public String index() {
        return "index";
    }
}
