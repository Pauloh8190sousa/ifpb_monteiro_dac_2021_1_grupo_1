package com.library.mockito;

import com.library.models.User;
import com.library.services.Validation;
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
        when(validation.validationUserName("ad")).thenThrow(new Exception("Nome precisa ter ao menos 3 caracteres"));
        when(validation.validationUserName("Joaquim Ferreira de souza santos Bezerra da silva")).
                thenThrow(new Exception("Nome pode ter no máximo 20 caracteres"));

        assertTrue(validation.validationUserName("Inathan"));
        assertThrows(Exception.class, () -> validation.validationUserName("ad"));
        assertThrows(Exception.class, () -> validation.validationUserName("Joaquim Ferreira de souza santos Bezerra da silva"));

        String errorMessage = "";
        try {
            validation.validationUserName("ad");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Nome precisa ter ao menos 3 caracteres", errorMessage);

        try {
            validation.validationUserName("Joaquim Ferreira de souza santos Bezerra da silva");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Nome pode ter no máximo 20 caracteres", errorMessage);

        verify(validation, times(1)).validationUserName("Inathan");
        verify(validation, times(2)).validationUserName("ad");
        verify(validation, times(2)).validationUserName("Joaquim Ferreira de souza santos Bezerra da silva");
    }

    @Test
    public void duplicateEmail() {
        List<String> mockedList = mock(List.class);

        mockedList.add("inathan@gmail.com");
        mockedList.add("inathan@gmail.com");

        verify(mockedList, times(2)).add("inathan@gmail.com");

    }

    @Test
    void validationEmail() throws Exception {
        when(validation.validationEmail("inathan@gmail.com")).thenReturn(true);
        when(validation.validationEmail("inathangmail")).thenThrow(new Exception("email inválido"));

        assertTrue(validation.validationEmail("inathan@gmail.com"));
        assertThrows(Exception.class, () -> validation.validationEmail("inathangmail"));

        String errorMessage = "";
        try {
            validation.validationEmail("inathangmail");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("email inválido", errorMessage);

        verify(validation, times(1)).validationEmail("inathan@gmail.com");
        verify(validation, times(2)).validationEmail("inathangmail");
    }

    @Test
    public void validationPassword() throws Exception {
        when(validation.validationPassword("a@3#")).thenThrow(new Exception("Caracteres inválidos"));
        when(validation.validationPassword("432545")).thenThrow(new Exception("A senha deve conter letras"));
        when(validation.validationPassword("user")).thenThrow(new Exception("A senha deve conter números"));
        when(validation.validationPassword("user123")).thenReturn(true);

        assertTrue(validation.validationPassword("user123"));
        assertThrows(Exception.class, () -> validation.validationPassword("a@3#"));
        assertThrows(Exception.class, () -> validation.validationPassword("432545"));
        assertThrows(Exception.class, () -> validation.validationPassword("user"));

        String errorMessage = "";
        try {
            validation.validationPassword("a@3#");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("Caracteres inválidos", errorMessage);

        try {
            validation.validationPassword("432545");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("A senha deve conter letras", errorMessage);

        try {
            validation.validationPassword("user");
        } catch (Exception error) {
            errorMessage = error.getMessage();
        }
        assertEquals("A senha deve conter números", errorMessage);

        verify(validation, times(1)).validationPassword("user123");
        verify(validation, times(2)).validationPassword("a@3#");
        verify(validation, times(2)).validationPassword("432545");
        verify(validation, times(2)).validationPassword("user");
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

        verify(validation, timeout(100000).atLeast(1)).redefinePassword("novaSenha");
    }

    @Test
    public void registrationLimit() {
        when(userRepository.save(user)).thenReturn(user);

        assertNotNull(userRepository.save(user));

        verify(userRepository, atMost(1)).save(user);
    }

}
