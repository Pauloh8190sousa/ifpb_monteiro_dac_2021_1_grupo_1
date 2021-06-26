package com.library.models;

import com.library.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorTest {

    @Autowired
    private AuthorService authorService;

    @TestConfiguration
    static class AuthorTestConfig {

        @Bean
        public AuthorService authorService() {
            return new AuthorService();
        }
    }

    @Test  //DESCOBRIMOS UM ERRO NO SISTEMA
    public void changeAuthor() {
        Author author = authorService.changeAuthor(authorService.findById(4L));
        author.setName("Joane Rowling");
        author.setBibliographicReference("J.K Rowling");
        assertEquals(4L, author.getId());
        assertEquals("Joane Rowling", author.getName());
        assertEquals("J.K Rowling", author.getBibliographicReference());
        assertNotNull(author);
    }

    @Test
    public void saveAuthor() {
        Author author = authorService.save(new Author("Joaneee Rowling", "J.K Rowlingg"));
        assertNotNull(author);
    }

    @Test
    public void findById() {
        Author author = authorService.findById(4L);
        assertEquals("Joaneee Rowling", author.getName());
        assertEquals("J.K Rowlingg", author.getBibliographicReference());
    }

    @Test
    public void findByName() {
        List<Author> authors = authorService.findByName("Joaneee Rowling");
        assertEquals("J.K Rowlingg", authors.get(0).getBibliographicReference());
        assertEquals(4L, authors.get(0).getId());
    }

    @Test
    public void findAllAuthors() {
        assertNotNull(authorService.findAll());
    }
}
