package com.library.mockito;

import com.library.models.User;
import com.library.repositories.UserRepository;
import com.library.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class UserTestMockito {

    @Spy
    private User user;

    @TestConfiguration
    static class UserTestMockitoConfig {

        @Bean
        public UserService userService() {
            return new UserService();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void initial() {
        user.setName("Inathan");
        user.setEmail("inathan@gmail.com");

        when(userRepository.findByEmail("inathan@gmail.com")).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    public void findByEmail() {
        when(userRepository.findByEmail("inathan@gmail.com")).thenReturn(user);

        User user = userService.findByEmail("inathan@gmail.com");
        assertThat(user.getEmail()).isEqualTo("inathan@gmail.com");
        verify(userRepository, times(1)).findByEmail("inathan@gmail.com");
    }

    @Test
    public void findByName() {
        when(userRepository.findByName("Inathan")).thenReturn(user);

        User user = userService.findByName("Inathan");
        assertThat(user.getName()).isEqualTo("Inathan");
        verify(userRepository, times(1)).findByName("Inathan");
    }

    @Test
    public void saveUser() {
        assertNotNull(userService.save(user));

        verify(userRepository, times(1)).save(user);
    }

}
