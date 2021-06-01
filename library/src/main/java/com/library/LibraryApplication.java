package com.library;

import com.library.facades.AuthorFacade;
import com.library.facades.UserFacade;
import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author paulo 
 * @author Inathan
 */


//Classe para execução do projeto

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	//private UserService userService;
	private UserFacade userFacade;
	private AuthorFacade authorFacade;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);

	}

	public LibraryApplication(UserFacade userFacade, AuthorFacade authorFacade) {
	//	this.userService = userService;
		this.userFacade = userFacade;
		this.authorFacade = authorFacade;
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner read = new Scanner(System.in);

		int option = 100;


		while (option != 0) {
			System.out.println("Escolha uma opção(número) abaixo:");

			System.out.println("\n0 - Sair");

			System.out.println("\n---------- Usuário ----------");

			System.out.println("\n1 - Registrar novo usuário");

			System.out.println("2 - Consultar usuário pelo e-mail");

			System.out.println("\n---------- Autor ----------");

			System.out.println("\n3 - Cadastrar autor");

			System.out.println("4 - Alterar autor");

			System.out.println("\n---------- Livro ----------");

			System.out.println("\n5 - Cadastrar livro");

			System.out.println("6 - Alterar livro");

			System.out.println("7 - Excluir livro");

			System.out.println("8 - Cadastrar um livro do catálogo ao estoque");

			System.out.println("9 - Adicionar um livro a um pedido (carrinho de compras) do cliente");

			System.out.println("10 - Consultar os 5 livros mais baratos disponíveis no estoque;");

			System.out.println("11 - Consultar todos os livros;");

			option = Integer.parseInt(read.nextLine());

			if(option == 1) {
				System.out.println("Nome: ");
				String userName = read.nextLine();

				System.out.println("E-mail: ");
				String userEmail = read.nextLine();

				userFacade.saveUser(userName, userEmail);
				System.out.println("Usuário cadastrado com sucesso!");

			} else if(option == 2) {
				System.out.println("E-mail: ");
				String userEmail = read.nextLine();

				System.out.println(userFacade.findByEmail(userEmail));
			}else if(option == 3){
				System.out.println("Nome: ");
				String nameAuthor = read.nextLine();

				System.out.println("Referência Bibliográfica: ");
				String reference = read.nextLine();

				authorFacade.saveAuthor(nameAuthor,reference);
			}

		}
	}
}
