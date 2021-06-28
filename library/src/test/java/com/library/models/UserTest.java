package com.library.models;

import com.library.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserTest {

    @Autowired
    private UserService userService;

    @TestConfiguration
    static class UserTestConfig {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Test
    public void saveUser() {
        User user = userService.save(new User("Inathan", "inathan@gmail.com"));
        assertNotNull(user);
    }

    @Test
    public void findById() {
        User user = userService.findById(1L);
        assertEquals("Inathan", user.getName());
        assertEquals("inathan@gmail.com", user.getEmail());
    }

    @Test
    public void findByEmail() {
        List<User> users = userService.findByEmail("inathan@gmail.com");
        assertEquals("Inathan", users.get(0).getName());
        assertEquals("inathan@gmail.com", users.get(0).getEmail());
        assertEquals(1L, users.get(0).getId());
    }

    @Test
    public void findAllUsers() {
        assertNotNull(userService.findAll());
    }

}
