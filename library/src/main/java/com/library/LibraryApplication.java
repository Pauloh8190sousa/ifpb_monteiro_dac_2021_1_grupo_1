package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author paulo 
 * @author Inathan
 */

@SpringBootApplication
public  class LibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}

//Classe para execução do projeto

//@SpringBootApplication
//public class LibraryApplication implements CommandLineRunner {
//
//	//private UserService userService;
//	private UserFacade userFacade;
//	private AuthorFacade authorFacade;
//	private BookFacade bookFacade;
//	private OrderFacade orderFacade;
//
//	public static void main(String[] args) {
//		SpringApplication.run(LibraryApplication.class, args);
//
//	}
//
//	public LibraryApplication(UserFacade userFacade, AuthorFacade authorFacade, BookFacade bookFacade, OrderFacade orderFacade) {
//	//	this.userService = userService;
//		this.userFacade = userFacade;
//		this.authorFacade = authorFacade;
//		this.bookFacade = bookFacade;
//		this.orderFacade = orderFacade;
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Scanner read = new Scanner(System.in);
//
//		int option = 100;
//
//
//		while (option != 0) {
//			System.out.println("Escolha uma opção(número) abaixo:");
//
//			System.out.println("\n0 - Sair");
//
//			System.out.println("\n---------- Usuário ----------");
//
//			System.out.println("\n1 - Registrar novo usuário");
//
//			System.out.println("2 - Consultar usuário pelo e-mail");
//
//			System.out.println("\n---------- Autor ----------");
//
//			System.out.println("\n3 - Cadastrar autor");
//
//			System.out.println("4 - Alterar autor");
//
//			System.out.println("\n---------- Livro ----------");
//
//			System.out.println("\n5 - Cadastrar livro");
//
//			System.out.println("6 - Alterar livro");
//
//			System.out.println("7 - Excluir livro");
//
//			System.out.println("8 - Cadastrar um livro do catálogo ao estoque");
//
//			System.out.println("9 - Adicionar um livro a um pedido (carrinho de compras) do cliente");
//
//			System.out.println("10 - Consultar os 5 livros mais baratos disponíveis no estoque;");
//
//			System.out.println("11 - Consultar todos os livros;");
//
//			System.out.println("\n---------- Pedido ----------");
//
//			System.out.println("12 - Criar novo pedido");
//
//			option = Integer.parseInt(read.nextLine());
//		try {
//			if (option == 1) {
//				System.out.println("Nome: ");
//				String userName = read.nextLine();
//
//				System.out.println("E-mail: ");
//				String userEmail = read.nextLine();
//
//				userFacade.saveUser(userName, userEmail);
//				System.out.println("Usuário cadastrado com sucesso!");
//
//			} else if (option == 2) {
//				System.out.println("E-mail: ");
//				String userEmail = read.nextLine();
//
//				System.out.println(userFacade.findByEmail(userEmail));
//			} else if (option == 3) {
//				System.out.println("Nome do autor: ");
//				String nameAuthor = read.nextLine();
//
//				System.out.println("Referência Bibliográfica do autor: ");
//				String reference = read.nextLine();
//
//				authorFacade.saveAuthor(nameAuthor, reference);
//				System.out.println("Cadastrado com sucesso!\n-----------");
//
//			} else if (option == 4) {
//				System.out.println("Digite o ID do autor: ");
//				Long id = Long.parseLong(read.nextLine());
//
//				System.out.println("Novo nome: ");
//				String nameAuthor = read.nextLine();
//
//				System.out.println("Nova referência bibliográfica: ");
//				String reference = read.nextLine();
//
//				authorFacade.changeAuthor(id, nameAuthor, reference);
//				System.out.println("Alterado com sucesso!\n------------");
//
//			} else if (option == 5) {
//				System.out.println("Titulo: ");
//				String titulo = read.nextLine();
//
//				System.out.println("Preço: ");
//				BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(read.nextLine()));
//
//				System.out.println("Descrição: ");
//				String descricao = read.nextLine();
//
//				System.out.println("ISBN: ");
//				String ISBN = read.nextLine();
//
//				System.out.println("Número de páginas: ");
//				int nbOfPages = Integer.parseInt(read.nextLine());
//
//				System.out.println("Ilustração (s/n): ");
//				boolean illustration = false;
//				if (read.nextLine().equals("s")) {
//					illustration = true;
//				}
//
//				Book bookSaved = bookFacade.saveBook(titulo, preco, descricao, nbOfPages, ISBN, illustration);
//
//				int exit = 100;
//
//				ArrayList<Author> authors = new ArrayList<>();
//
//				while (exit != 0) {
//					Author authorSelected = authorFacade.selectAuthor();
//					System.out.println("Autor Selecionado: " + authorSelected.getName());
//
//					authors.add(authorSelected);
//
//					System.out.println("Adicionar mais autores?(1 para sim/0 para não)");
//					exit = Integer.parseInt(read.nextLine());
//				}
//
//				bookFacade.addAuthorToBook(bookSaved.getId(), authors);
//
//				System.out.println("Cadastrado com sucesso!\n-----------");
//
//			} else if (option == 6) {
//				System.out.println("Digite o ID do Livro: ");
//				Long id = Long.parseLong(read.nextLine());
//
//				System.out.println("Novo Titulo: ");
//				String titulo = read.nextLine();
//
//				System.out.println("Novo Preço: ");
//				BigDecimal preco = BigDecimal.valueOf(Double.parseDouble(read.nextLine()));
//
//				System.out.println("Nova Descrição: ");
//				String descricao = read.nextLine();
//
//				System.out.println("Novo ISBN: ");
//				String ISBN = read.nextLine();
//
//				System.out.println("Novo Número de páginas: ");
//				int nbOfPages = Integer.parseInt(read.nextLine());
//
//				System.out.println("Ilustração (s/n): ");
//				boolean illustration = false;
//				if (read.nextLine().equals("s")) {
//					illustration = true;
//				}
//				bookFacade.changeBook(id, titulo, preco, descricao, nbOfPages, ISBN, illustration);
//				System.out.println("Alterado com sucesso!\n------------");
//			} else if (option == 7) {
//				System.out.println("Digite o ID do Livro: ");
//				Long id = Long.parseLong(read.nextLine());
//
//				System.out.println("Dedeja excluir o livro (s/n): ");
//				if (read.nextLine().equals("s")) {
//					bookFacade.deleteBook(id);
//					System.out.println("Livro deletado com sucesso!\n----------");
//				}
//			} else if (option == 8) {
//
//				Book bookSelected = bookFacade.selectBook();
//				System.out.println("Livro Selecionado: " + bookSelected.getTitle());
//				bookFacade.addBookToStock(bookSelected.getId());
//
//				System.out.println("Livro adicionado ao Estoque!");
//
//			} else if (option == 9) {
//
//				int exit = 100;
//
//				ArrayList<Book> books = new ArrayList<>();
//
//				while (exit != 0) {
//					Book bookSelected = bookFacade.selectBook();
//					System.out.println("Livro Selecionado: " + bookSelected.getTitle());
//					books.add(bookSelected);
//
//					System.out.println("Deseja adicionar mais livros ao pedido?(1 para sim/0 para não)");
//					exit = Integer.parseInt(read.nextLine());
//
//				}
//
//				Order orderSelected = orderFacade.selectOrder();
//				System.out.println("Pedido Selecionado: " + orderSelected.getId());
//
//				orderFacade.addOrderBook(orderSelected.getId(), books);
//
//				System.out.println("Livros adicionados ao pedido!");
//
//			} else if (option == 10) {
//				System.out.println("Deseja consultar os 5 livros mais baratos?(s/n): ");
//				String opcao = read.nextLine();
//				List<Book> books = null;
//				if (opcao.equals("s")) {
//					books = bookFacade.listCheapBook();
//					for (Book b : books) {
//						System.out.println("Livro: " + b.getTitle());
//					}
//				}
//			} else if (option == 11) {
//				System.out.println("Deseja consultar todos os livros?(s/n): ");
//				String res = read.nextLine();
//				List<Book> books = null;
//				if (res.equals("s")) {
//					System.out.println("Número de páginas: ");
//					int pages = Integer.parseInt(read.nextLine());
//					books = bookFacade.findAllBooks(pages);
//					for (Book b : books) {
//						System.out.println("Livro: " + b.getTitle());
//					}
//				}
//			} else if (option == 12) {
//				User userSelected = userFacade.selectUser();
//				System.out.println("Usuário Selecionado: " + userSelected.getName());
//				orderFacade.saveOrder(true, userSelected, BigDecimal.valueOf(0));
//
//				System.out.println("Novo pedido para o usuário " + userSelected.getName() + " Criado!");
//
//			}
//
//			}catch(Exception ex){
//				System.out.println("Entrada inválida!");
//			}
//		}
//	}
//}
