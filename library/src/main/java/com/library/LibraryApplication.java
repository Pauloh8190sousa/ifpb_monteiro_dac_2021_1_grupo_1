package com.library;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.library.facades.UserFacade;
import com.library.services.*;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * @author paulo 
 * @author Inathan
 */


//Classe para execução do projeto

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);

		Scanner read = new Scanner(System.in);

		int option = 100;

		UserFacade userFacade = new UserFacade();


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

			option = read.nextInt();


			String userName = "";
			String userEmail = "";

			if(option == 1) {
				System.out.println("Nome: ");
				userName = read.nextLine();

				System.out.println("E-mail: ");
				userEmail = read.nextLine();

				userFacade.userRegister(userName, userEmail);
			}

		}

	}

}
