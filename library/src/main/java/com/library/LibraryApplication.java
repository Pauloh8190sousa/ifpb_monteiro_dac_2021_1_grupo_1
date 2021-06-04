package com.library;

import com.library.facades.AuthorFacade;
import com.library.facades.BookFacade;
import com.library.facades.OrderFacade;
import com.library.facades.UserFacade;
import com.library.models.Author;
import com.library.models.Book;
import com.library.models.Order;
import com.library.models.User;
import com.library.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
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
	private BookFacade bookFacade;
	private OrderFacade orderFacade;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);

	}

	public LibraryApplication(UserFacade userFacade, AuthorFacade authorFacade, BookFacade bookFacade, OrderFacade orderFacade) {
	//	this.userService = userService;
		this.userFacade = userFacade;
		this.authorFacade = authorFacade;
		this.bookFacade = bookFacade;
		this.orderFacade = orderFacade;
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
				System.out.println("Nome do autor: ");
				String nameAuthor = read.nextLine();

				System.out.println("Referência Bibliográfica do autor: ");
				String reference = read.nextLine();

				authorFacade.saveAuthor(nameAuthor,reference);
				System.out.println("Cadastrado com sucesso!\n-----------");

			}else if(option == 4){
				System.out.println("Digite o ID do autor: ");
				Long id = Long.parseLong(read.nextLine());

				System.out.println("Novo nome: ");
				String nameAuthor = read.nextLine();

				System.out.println("Nova referência bibliográfica: ");
				String reference = read.nextLine();

				authorFacade.changeAuthor(id,nameAuthor,reference);
				System.out.println("Alterado com sucesso!\n------------");

			}else if(option==5){
				System.out.println("Titulo: ");
				String titulo = read.nextLine();

				System.out.println("Preço: ");
				BigDecimal preco =  BigDecimal.valueOf(Double.parseDouble(read.nextLine()));

				System.out.println("Descrição: ");
				String descricao = read.nextLine();

				System.out.println("ISBN: ");
				int ISBN = Integer.parseInt(read.nextLine());

				System.out.println("Número de páginas: ");
				int nbOfPages = Integer.parseInt(read.nextLine());

				System.out.println("Ilustração (s/n): ");
				boolean illustration = false;
				if(read.nextLine().equals("s")){
					illustration = true;
				}

				Book bookSaved = bookFacade.saveBook(titulo, preco, descricao, nbOfPages, ISBN, illustration);

				Author authorSelected = authorFacade.selectAuthor();

				System.out.println("Autor Selecionado: "+ authorSelected.getName());

				bookFacade.addAuthorToBook(authorSelected.getId(), bookSaved);

				System.out.println("Cadastrado com sucesso!\n-----------");

			}else if(option==6){
				System.out.println("Digite o ID do Livro: ");
				Long id = Long.parseLong(read.nextLine());

				System.out.println("Novo Titulo: ");
				String titulo = read.nextLine();

				System.out.println("Novo Preço: ");
				BigDecimal preco =  BigDecimal.valueOf(Double.parseDouble(read.nextLine()));

				System.out.println("Nova Descrição: ");
				String descricao = read.nextLine();

				System.out.println("Novo ISBN: ");
				int ISBN = Integer.parseInt(read.nextLine());

				System.out.println("Novo Número de páginas: ");
				int nbOfPages = Integer.parseInt(read.nextLine());

				System.out.println("Ilustração (s/n): ");
				boolean illustration = false;
				if(read.nextLine().equals("s")){
					illustration = true;
				}
				bookFacade.changeBook(id, titulo, preco, descricao, nbOfPages, ISBN, illustration);
				System.out.println("Alterado com sucesso!\n------------");
			}else if(option==7){
				System.out.println("Digite o ID do Livro: ");
				Long id = Long.parseLong(read.nextLine());

				System.out.println("Dedeja excluir o livro (s/n): ");
				if(read.nextLine().equals("s")){
					bookFacade.deleteBook(id);
					System.out.println("Livro deletado com sucesso!\n----------");
				}
			}else if(option==8){

				Book bookSelected = bookFacade.selectBook();
				System.out.println("Livro Selecionado: "+ bookSelected.getTitle());
				bookFacade.addBookToStock(bookSelected);

				System.out.println("Livro adicionado ao Estoque!");

			}else if(option==9){
//				System.out.println("Digite seu nome: ");
//				String nameUser = read.nextLine();
//				System.out.println("Digite seu E-mail: ");
//				String emailUser = read.nextLine();
//				User user = userFacade.saveUser(nameUser,emailUser);
//				boolean stop = false;
//				Order order = orderFacade.saveOrder(false,user);
//				while(stop==false){
//					Book bookSelected = bookFacade.selectBook();
//					System.out.println("Livro Selecionado: "+bookSelected.getTitle());
//					order.addOrderBook(bookSelected);
//					System.out.println("Digite -1 para parar de adicionar livro: ");
//					if(read.nextLine().equals("-1")){
//						stop = true;
//					}
//				}
//				orderFacade.orderTotalValue(order.getId(), order.getBooks());
//				System.out.println("Livro adicionado no pedido!\n-------------");
			}else if(option==10){
				System.out.println("Deseja consultar os 5 livros mais baratos?(s/n): ");
				String opcao = read.nextLine();
				List<Book> books = null;
				if(opcao.equals("s")) {
                    books = bookFacade.listCheapBook();
                    for(Book b: books){
                        System.out.println("Livro: "+b.getTitle());
                    }
                }
			}else if(option==11){
				System.out.println("Deseja consultar todos os livros?(s/n): ");
				String res = read.nextLine();
				List<Book> books = null;
				if(res.equals("s")){
					System.out.println("Número de páginas: ");
					int pages = Integer.parseInt(read.nextLine());
					books = bookFacade.findAllBooks(pages);
					for(Book b: books){
						System.out.println("Livro: "+b.getTitle());
					}
				}
			}

		}
	}
}
