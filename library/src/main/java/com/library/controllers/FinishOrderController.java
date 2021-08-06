package com.library.controllers;

import com.library.models.Order;
import com.library.models.Payment;
import com.library.services.OrderService;
import com.library.services.PaymentService;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FinishOrderController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @ModelAttribute("allPayments")
    public List<Payment> authors() {
        return paymentService.findAll();
    }

    @RequestMapping(value = "/FinishOrder", method = RequestMethod.GET)
    public String createAddress() {
        return "FinishOrder/SelectPayment";
    }

    @PostMapping("/FinishOrder")
    public String registerPayment(Payment payment) {
        Order order = orderService.findAll().get(orderService.findAll().size() - 1);

        order.setPayment(payment);
        order.setOpen(false);
        orderService.save(order);

        return "redirect:/Home";
    }
}
