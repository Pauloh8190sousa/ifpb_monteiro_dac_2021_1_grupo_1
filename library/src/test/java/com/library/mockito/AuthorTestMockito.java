package com.library.mockito;//package com.library.models;

import com.library.models.Author;
import com.library.models.Validation;
import com.library.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class AuthorTestMockito {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private Validation validation;

    @TestConfiguration
    static class AuthorTestConfig {

        @Bean
        public AuthorService authorService() {
            return new AuthorService();
        }
    }

    @Test  //DESCOBRIMOS UM ERRO NO SISTEMA
    public void changeAuthor() {
        Author author = new Author();
        author.setName("Joane Rowling");
        author.setBibliographicReference("Rowling J.K");
        Author updateAuthor = authorService.changeAuthor(author,5L);
        assertNotNull(updateAuthor);
        assertEquals(5L, updateAuthor.getId());
        assertEquals("Joane Rowling", updateAuthor.getName());
        assertEquals("Rowling J.K", updateAuthor.getBibliographicReference());

    }

    @Test
    public void saveAuthor() {
        Author author = authorService.save(new Author("Joaneee Rowling", "Rowlingg J.K"));
        assertNotNull(author);
    }

    @Test
    public void findById() {
        Author author = authorService.findById(5L);
        assertEquals("Joaneee Rowling", author.getName());
        assertEquals("Rowlingg J.K", author.getBibliographicReference());
    }

    @Test
    public void findByName() {
        List<Author> authors = authorService.findByName("Joaneee Rowling");
        assertEquals("Rowlingg J.K", authors.get(0).getBibliographicReference());
        assertEquals(5L, authors.get(0).getId());
    }
    @Test
    void validationBibliographicReference() {
        Author author = authorService.findById(5L);
        boolean saida = validation.validationBibliographicReference(author.getBibliographicReference());
        assertEquals(true,saida);
    }
    @Test
    public void findAllAuthors() {
        assertNotNull(authorService.findAll());
    }
}
