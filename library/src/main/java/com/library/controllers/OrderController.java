package com.library.controllers;
import com.library.models.Author;
import com.library.models.Order;
import com.library.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//
////CLASSE CONTROLLER DE ORDER(PEDIDO)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/listOrder")
    public ModelAndView listOrderPageable() {
        ModelAndView modelAndView = new ModelAndView("Order/OrderList");
        List<Order> orders = orderService.findAll();

        modelAndView.addObject("orders", orders);

        return modelAndView;
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteAuthor(@PathVariable("id") long id) {

        orderService.delete(orderService.findById(id));

        return "redirect:/listOrder";
    }



//    @RequestMapping(value = "/Cart", method = RequestMethod.GET)
//    public String createOrder(){
//        return "Order/Cart";
//    }
//    @RequestMapping(value = "/Cart", method = RequestMethod.POST)
//    public String createOrder(Order order){
//
//        orderService.save(order);
//
//        return "redirect:/Home";
//    }




//
//    //MÉTODO PARA LISTAR ORDERS
//    @GetMapping
//    public List<Order> getOrders() {
//        return orderService.findAll();
//    }
//
//    //MÉTODO PARA LISTAR BOOKS_ORDERS
//    @GetMapping("/books_order/{id}")
//    public List<Book> listAllBooks(@PathVariable(value = "id") Long orderId){
//        return orderService.listAllBooks(orderId);
//    }
//
//    //MÉTODO CRIAR UM ORDER
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Order postOrders(@RequestBody Order order) {
//        return orderService.save(order);
//    }
//
//    @PostMapping("/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addOrderBook(@PathVariable(value = "id") Long id, @RequestBody Book book){
//        orderService.addOrderBook(id,book);
//    }
}
