//package com.library.models;
//
//import com.library.services.OrderService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class OrderTest {
//
//    @Autowired
//    private OrderService orderService;
//
//    @TestConfiguration
//    static class OrderTestConfig {
//
//        @Bean
//        public OrderService orderService() {
//            return new OrderService();
//        }
//    }
//
//    @Test
//    public void saveOrder() {
//        Order order = orderService.save(new Order(true, new User("Inathan", "inathan@gmail.com"),
//                BigDecimal.valueOf(Double.parseDouble("37"))));
//        assertNotNull(order);
//    }
//
//    @Test
//    public void findById() {
//        Order order = orderService.findById(1L);
//        assertTrue(order.isStatus());
//        assertEquals("Inathan", order.getUser().getName());
//        assertEquals("inathan@gmail.com", order.getUser().getEmail());
//    }
//
//    @Test
//    public void findByStatus() {
//        List<Order> orders = orderService.findByStatus(true);
//        assertEquals(1, orders.get(0).getId());
//        assertEquals("Inathan", orders.get(0).getUser().getName());
//        assertEquals("inathan@gmail.com", orders.get(0).getUser().getEmail());
//    }
//
//    @Test
//    public void findAllOrder() {
//        assertNotNull(orderService.findAll());
//    }
//
//}
