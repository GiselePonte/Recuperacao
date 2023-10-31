package Rec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class LojaApp {
	private List produtos = new ArrayList<>();
	private List clientes = new ArrayList<>();
	private Map<String, List> comprasPorCliente = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		LojaApp loja = new LojaApp(); 
		Scanner scanner = new Scanner(System.in); 
		int opcao;
		do {
			System.out.println("Opções:"); 
			System.out.println("1. Adicionar produto"); 
			System.out.println("2. Adicionar cliente");
			System.out.println("3. Realizar compra"); 
			System.out.println("4. Mostrar relatório de compra");
			System.out.println("5. Gravar dados em arquivo");
			System.out.println("6. Recuperar dados de arquivo");
			System.out.println("0. Sair");
			System.out.print("Escolha a opção: ");
			opcao = scanner.nextInt();
			
			switch (opcao) { 
			case 1: loja.adicionarProduto(scanner);
			break;
			
			case 2: loja.adicionarCliente(scanner); 
			break; 
			
			case 3: loja.realizarCompra(scanner);
			break; 
			
			case 4: loja.mostrarRelatorioCompra(scanner);
			break;
			
			case 5: loja.gravarDadosEmArquivo("dados.txt"); 
			break;
			
			case 6: loja.recuperarDadosDeArquivo("dados.txt"); 
			break; 
			} 
			} while (opcao != 0); 
		}

	public void mostrarRelatorioCompra(Scanner scanner) {
		System.out.print("Nome do cliente: ");
		String nomeCliente = scanner.next();
		List <Compra> compras = comprasPorCliente.get(nomeCliente);
		
		if (compras != null) {
			System.out.println("Relatório de Compras para " + nomeCliente + ":");
			
			for (Compra compra : compras) {
				System.out.println(compra.getQuantidade() + " " + compra.getProduto());
			} 
			} else {
			System.out.println("Nenhuma compra registrada para " + nomeCliente);
		}
	}
	

	public void produtosrealizarCompra(Scanner scanner) {
		System.out.println("Produtos disponíveis para compra:");
		for (int i = 0; i < produtos.size(); i++) {
			System.out.println((i + 1) + ". " + produtos.get(i));
		}
		System.out.print("Escolha o número do produto que deseja comprar: ");
		int escolhaProduto = scanner.nextInt();
		if (escolhaProduto > 0 && escolhaProduto <= produtos.size()) {
			String produtoEscolhido = (String) produtos.get(escolhaProduto - 1);
			System.out.print("Quantidade desejada: ");
			int quantidade = scanner.nextInt();
			System.out.print("Nome do cliente: ");
			String nomeCliente = scanner.next();
			List compras = comprasPorCliente.get(nomeCliente);
			if (compras == null) {
				compras = new ArrayList<>();
				comprasPorCliente.put(nomeCliente, compras);
			}
			compras.add(new Compra(produtoEscolhido, quantidade));
			System.out.println("Você comprou " + quantidade + " " + produtoEscolhido);
		} else {
			System.out.println("Escolha de produto inválida.");
		}
	}

	public void adicionarProduto(Scanner scanner) {
		System.out.print("Nome do produto: ");
		String nomeProduto = scanner.next();
		produtos.add(nomeProduto);
		System.out.println("Produto adicionado: " + nomeProduto);
	}

	public void adicionarCliente(Scanner scanner) {
		System.out.print("Nome do cliente: ");
		String nomeCliente = scanner.next();
		clientes.add(nomeCliente);
		System.out.println("Cliente adicionado: " + nomeCliente);
	}

	public void gravarDadosEmArquivo(String dados) {
		try (PrintWriter writer = new PrintWriter(new FileWriter("d:\\Dados.txt"))) {
			for (String produto : produtos) {
				writer.println("Produto: " + produto);
			}
			for (String cliente : clientes) {
		writer.println("Cliente: " + cliente);
			}
			System.out.println("Dados gravados com sucesso em d:\\Dados.txt");
		} catch (IOException e) {
			System.err.println("Erro ao gravar dados em arquivo: " + e.getMessage());
		}
	}
	

	public void recuperarDadosDeArquivo(String Dados ) {
		try (BufferedReader reader = new BufferedReader(new FileReader("d:\\Dados.txt"))) {
			String linha;
			produtos.clear();
			clientes.clear();

			while ((linha = reader.readLine()) != null) {
				if (linha.startsWith("Produto: ")) {
					 

					produtos.add(linha.substring("Produto: " .length()));
				
				} else if (linha.startsWith("Cliente: ")) {
					clientes.add(linha.substring("Cliente: ".length()));
				}
			}
			System.out.println("Dados recuperados com sucesso de " + "d:\\Dados.txt");
		} catch (IOException e) {
			System.err.println("Erro ao recuperar dados de arquivo: " + e.getMessage());
		}
	}
}
