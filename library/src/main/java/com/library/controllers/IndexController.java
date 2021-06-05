package com.library.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//CLASSE CONTROLLER DO INDEX DA APLICACAO
@Slf4j //Faz o log da classe para poder tratar erros
@Controller
@RequestMapping("/")
public class IndexController {

    //MÉTODO PARA RETORNAR PAGINA
    @RequestMapping
    public String index() {
        return "index";
    }
}
