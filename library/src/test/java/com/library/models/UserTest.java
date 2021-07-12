//package com.library.models;
//
//import com.library.services.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class UserTest {
//
//    @Autowired
//    private UserService userService;
//
//    @TestConfiguration
//    static class UserTestConfig {
//
//        @Bean
//        public UserService userService() {
//            return new UserService();
//        }
//    }
//
//    @Test
//    public void saveUser() {
//        User user = userService.save(new User("Inathan", "inathan@gmail.com"));
//        assertNotNull(user);
//    }
//
//    @Test
//    public void findById() throws Exception {
//        User user = userService.findById(2L);
//        assertEquals("Inathan", user.getName());
//        assertEquals("inathan@gmail.com", user.getEmail());
//    }
//
//    @Test
//    public void findByEmail() {
//        User users = userService.findByEmail("inathan@gmail.com");
//        assertEquals("Inathan", users.getName());
//        assertEquals("inathan@gmail.com", users.getEmail());
//        assertEquals(2L, users.getId());
//    }
//
//    @Test
//    public void findAllUsers() {
//        assertNotNull(userService.findAll());
//    }
//
//    //<---------- VALIDAÇÃO ---------->
//
//
//    @Test
//    public void validationUserName() {
//        assertTrue(Validation.validationUserName("Inathan"));
//        assertFalse(Validation.validationUserName("ad"));
//        assertFalse(Validation.validationUserName("Joaquim Ferreira de souza santos Bezerra da silva"));
//    }
//
//    @Test
//    public void duplicateEmail() {
//        User user = userService.findByEmail("inathan@gmail.com");
//        assertFalse(Validation.duplicateEmail(user, "inathan@gmail.com"));
//    }
//
//    @Test
//    void validationEmail() {
//        assertTrue(Validation.validationEmail("ph@gmail.com"));
//        assertFalse(Validation.validationEmail("phsousa"));
//    }
//
//    @Test
//    public void validationPassword() {
//        assertFalse(Validation.validationPassword("paulo"));
//    }
//}
