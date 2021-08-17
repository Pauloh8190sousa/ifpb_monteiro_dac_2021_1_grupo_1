package com.library.services;

import com.library.models.Author;
import com.library.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//CLASSE DE SERVIÇOS PARA AUTHOR(AUTOR)
@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    //MÉTODO PARA SALVAR UM AUTHOR
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> listAllAuthors(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber,5, Sort.Direction.ASC, "name");
        return authorRepository.findAll(pageRequest).getContent();
    }

    //MÉTODO PARA DELETAR UM AUTHOR PELO ID
    public void deleteById(Long idAuthor) {
        Author author = authorRepository.findById(idAuthor).orElseThrow();
        authorRepository.delete(author);
    }

    //MÉTODO PARA LISTAR AUTHORS PELO NOME
    public List<Author> findByName(String nameAuthor) {
        return authorRepository.findByName(nameAuthor);
    }

    //MÉTODO PARA CONSULTAR UM AUTHOR PELO ID
    public Author findById(Long idAuthor) {
        return authorRepository.findById(idAuthor).orElseThrow();
    }

    /**
     * MÉTODO PARA ALTERAR UM AUTHOR
     * @param author > O author que será setado para atualizar o author antigo
     * @param idAuthor > id do author antigo para ser atualizado.
     * @return > retorna o author atualizado
     */
    public Author changeAuthor(Author author, Long idAuthor) {
        //erro corrigido - Paulo
        Author updatedAuthor = authorRepository.findById(idAuthor).orElseThrow();
        //não estava setando o author no updateAuthor.
        updatedAuthor.setName(author.getName());
        updatedAuthor.setBibliographicReference(author.getBibliographicReference());

        return save(updatedAuthor);
    }

    //MÉTODO PARA LISTAR TODOS OS AUTHORS
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

}
