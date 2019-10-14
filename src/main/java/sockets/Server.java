package sockets;

/**
 *
 * @author luis
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/*
 * Demonstrando a comunicação entre processos através de sockets 
 * multithreading
 * Classe Servidor, que aguarda conexão de clientes. Quando uma conexão é 
 * estabelecida, o cliente é encaminhado e tratado por uma thread (socket)
 */
public class Server {
	private int porta;
	private ArrayList<PrintStream> clientes;
	
	public Server(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
	} 
	
	public void executa() throws IOException {
		/*
		 * Iniciando o servidor, na porta 8090
		 */
		ServerSocket servidor = new ServerSocket(this.porta);
		System.out.println("Servidor aguardando requisições "+
				"na porta 8090...");
		
		/*
		 * Agora temos um while(true), pois queremos que o servidor
		 * fique sempre aguardando por uma nova conexao
		 */
		while(true) {
			//esperando por uma conexao
			Socket cliente = servidor.accept();
			/*
			 * O próximo comando só será executado quando uma conexão 
			 * for estabelecida
			 */
			System.out.println("Nova conexão com o cliente " +
				cliente.getInetAddress().getHostAddress());
			
			//adicionando saida do cliente a lista de clientes do servidor
			PrintStream ps = new PrintStream(
					cliente.getOutputStream());
			this.clientes.add(ps);
			
			/* Criando um objeto (que trata-se de uma thread) para o 
			 * tratamento do Cliente
			 */
			TrataCliente tc = new TrataCliente(
					cliente.getInputStream(), this);
			//iniciando a thread
			tc.start();
		}
	}
	
	public static void main(String[] args) {
		try {
			/*
			 * Criando uma instancia do Servidor e o executando
			 */
			new Server(8090).executa();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}