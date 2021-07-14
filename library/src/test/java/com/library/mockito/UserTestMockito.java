package com.library.mockito;

import com.library.models.User;
import com.library.models.Validation;
import com.library.repositories.UserRepository;
import com.library.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.naming.InvalidNameException;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @MockBean
    private Validation validation;

    @BeforeEach
    public void initial() {
        user.setId(1L);
        user.setName("Inathan");
        user.setEmail("inathan@gmail.com");
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
    public void findById() throws Exception {
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(user));

        User user = userService.findById(1L);
        assertThat(user.getId()).isEqualTo(1L);

        assertThrows(Exception.class, () -> userService.findById(7L));

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void saveUser() {
        when(userRepository.save(user)).thenReturn(user);

        assertNotNull(userService.save(user));

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void validationUserName() throws Exception {

        when(validation.validationUserName("Inathan")).thenReturn(true);
        when(validation.validationUserName("ad")).thenThrow(new Exception());
        when(validation.validationUserName("Joaquim Ferreira de souza santos Bezerra da silva")).thenThrow(new Exception());

        assertTrue(validation.validationUserName("Inathan"));
        assertThrows(Exception.class, () -> validation.validationUserName("ad"));
        assertThrows(Exception.class, () -> validation.validationUserName("Joaquim Ferreira de souza santos Bezerra da silva"));

        verify(validation, times(1)).validationUserName("Inathan");
        verify(validation, times(1)).validationUserName("ad");
        verify(validation, times(1)).validationUserName("Joaquim Ferreira de souza santos Bezerra da silva");
    }

    @Test
    public void duplicateEmail() {
        List<String> mockedList = mock(List.class);

        mockedList.add("inathan@gmail.com");
        mockedList.add("inathan@gmail.com");

        verify(mockedList, times(2)).add("inathan@gmail.com");

    }

    @Test
    void validationEmail() {
        when(validation.validationEmail("inathan@gmail.com")).thenReturn(true);
        when(validation.validationEmail("inathangmail")).thenReturn(false);

        assertTrue(validation.validationEmail("inathan@gmail.com"));
        assertFalse(validation.validationEmail("inathangmail"));

        verify(validation, times(1)).validationEmail("inathan@gmail.com");
        verify(validation, times(1)).validationEmail("inathangmail");
    }

    @Test
    public void validationPassword() throws Exception {
        when(validation.validationPassword("a@3#")).thenThrow(new Exception());
        when(validation.validationPassword("user123")).thenReturn(true);

        assertTrue(validation.validationPassword("user123"));
        assertThrows(Exception.class, () -> validation.validationPassword("a@3#"));

        verify(validation, times(1)).validationPassword("user123");
        verify(validation, times(1)).validationPassword("a@3#");
    }

    //<---------- FUTURAS IMPLEMENTAÇÕES NO SISTEMA ---------->

    @Test
    public void emailAvailable() {
        List<String> mockedList = mock(List.class);

        mockedList.add("inathan@gmail.com");

        String newEmail = "killmor@gmail.com";

        verify(mockedList, never()).add(newEmail);
    }

    @Test
    public void redefinePassword() {
        when(validation.redefinePassword("novaSenha")).thenReturn("Senha atualizada");

        assertEquals("Senha atualizada", validation.redefinePassword("novaSenha"));

        verify(validation, timeout(100000).times(1)).redefinePassword("novaSenha");
    }

}
