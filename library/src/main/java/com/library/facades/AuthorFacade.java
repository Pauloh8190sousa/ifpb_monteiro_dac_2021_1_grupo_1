package com.library.facades;

import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Validation;
import com.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AuthorFacade {

    @Autowired
    private AuthorService authorService;

    public void saveAuthor(String name, String bibliographicReference){
        if(Validation.validationBibliographicReference(bibliographicReference)){
            Author author = new Author(name,bibliographicReference);
            authorService.save(author);
        }else{
            System.out.println("Referência Bibliografica inválida\nTente novamente!");
        }
    }
    public void changeAuthor(Long idAuthor, String name, String reference){

        Author author = findById(idAuthor);
        author.setName(name);
        if(Validation.validationBibliographicReference(reference)){
            author.setBibliographicReference(reference);
        }else{
            System.out.println("Não foi possível mudar a referência bibliografica por que está inválida!");
        }
        authorService.save(author);
    }
    public List<Author> findByName(String name){
        return authorService.findByName(name);
    }
    public Author findById(Long authorId){
        return authorService.findById(authorId);
    }

    public Author selectAuthor() {
        Scanner read = new Scanner(System.in);

        List<Author> authors = authorService.findAll();

        for (Author author: authors) {
            System.out.println("Id: "+ author.getId()+ "   Nome: "+ author.getName() );
        }

        System.out.println("Selecione um author pelo Id: ");
        Long authorId = Long.parseLong(read.nextLine());

        return authorService.findById(authorId);
    }

}
