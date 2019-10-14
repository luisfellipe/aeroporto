package sockets;

/**
 *
 * @author luis
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * Demonstrando a comunicação entre processos atraves de sockets 
 * multithreadings (servidor ira criar um thread para cada cliente,
 * de modo a tratar multiplos clientes
 * Classe Cliente, que se conecta a um Servidor e envia mensagens a ele
 */
public class Cliente<Object> {
	private String host;
	private int porta;
	
	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		/*
		 * Tentando se conectar ao servidor, que está rodando
		 * no localhost, porta 8090
		 */
		Socket cliente = new Socket(this.host, this.porta);
		System.out.println("Cliente conectado!");
		
		/*scanner para leitura da mensagem digitada
			pelo usuario no console*/
		Scanner teclado = new Scanner(System.in);
		
		/* buffer de saida. Sera usado para enviar a mensagem
		 * ao servidor */
		PrintStream saida = new PrintStream(
				cliente.getOutputStream());
		
		while(teclado.hasNextLine()) {
			/*lendo a linha digitada no console e "jogando" no buffer
				de saida*/
			saida.println(teclado.nextLine());
		}
		
		//fechando as conexoes
		saida.close();
		teclado.close();
		cliente.close();
	}
	
	public static void main(String[] args) {
		try {
			/*
			 * Criando uma instancia de Cliente e tentando executa-lo
			 */
			new Cliente("127.0.0.1", 8090).executa();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}