package com.library.controllers;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Payment;
import com.library.services.PaymentService;
import com.library.services.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;


@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class PaymentController {
    //falta implementar

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/paymentList", method = RequestMethod.GET)
    public String listPayment() {
        return "Order/BuyOrder";
    }

    @GetMapping("/listPayment")
    public ModelAndView editAuthor() {
        ModelAndView modelAndView = new ModelAndView("Admin/BuyOrderConfig");
        List<Payment> payments = paymentService.findAll();

        modelAndView.addObject("payments", payments);

        return modelAndView;
    }
}
