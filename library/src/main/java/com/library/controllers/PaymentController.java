package com.library.controllers;

import com.library.models.*;
import com.library.services.OrderService;
import com.library.services.PaymentService;
import com.library.services.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;


@Slf4j //Faz o log da classe para poder tratar erros
@Controller
public class PaymentController {
    //falta implementar

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/createPayment", method = RequestMethod.GET)
    public String finishOrderCart() {
        return "Payment/PaymentForm";
    }

    @PostMapping("/createPayment")
    public String finishOrderCart(@Validated Payment payment, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            System.out.println("Erro campo");
            return "redirect:/createPayment";

        }else{
            paymentService.save(payment);
        }

        return "redirect:/listPayment";
    }

    @PostMapping("/listPayment/{id}")
    public ModelAndView editPayment(Payment payment) {
        paymentService.save(payment);

        return new ModelAndView("Admin/PaymentConfig");
    }

    @GetMapping("/listPayment/{id}")
    public ModelAndView editPayment(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("Admin/PaymentEdit");
        Payment payment = paymentService.findById(id);

        modelAndView.addObject("payment", payment);

        return modelAndView;
    }

    @GetMapping("/deletePayment/{id}")
    public String deletePayment(@PathVariable("id") long id) {

        paymentService.deleteById(id);

        return "redirect:/listPayment";
    }

    @RequestMapping("/listPayment/{action}")
    public ModelAndView PaymentList(@PathVariable Integer action) {
        ModelAndView modelAndView = new ModelAndView("Admin/PaymentConfig");
        List<Payment> payments;
        if(action != null) {
            payments = paymentService.listAllPayments(action);

        }else{
            payments = paymentService.listAllPayments(0);
        }

        modelAndView.addObject("payments", payments);

        return modelAndView;
    }

    @GetMapping("/listPayment")
    public ModelAndView listPaymentPageable() {
        ModelAndView modelAndView = new ModelAndView("Admin/PaymentConfig");
        List<Payment> payments = paymentService.listAllPayments(0);

        modelAndView.addObject("payments", payments);

        return modelAndView;
    }

}
