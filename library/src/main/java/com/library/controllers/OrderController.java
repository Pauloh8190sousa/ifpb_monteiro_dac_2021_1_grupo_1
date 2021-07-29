package com.library.controllers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

//
////CLASSE CONTROLLER DE ORDER(PEDIDO)
@Slf4j //Faz o log da classe para poder tratar erros
@Controller

public class OrderController {

//    @Autowired
//    OrderService orderService;
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
