package sockets;

/**
 *
 * @author luis
 */
import java.io.InputStream;
import java.util.Scanner;
import main.ReservarPassagem;

/*
 * Demonstrando a troca de mensagens entre processos através de sockets 
 * multithreading
 * Classe que implementa uma Thread para tratamento de um Cliente
 */
public class TrataCliente extends Thread{
	private static long NEXT_ID = 1;
	
	private InputStream cliente;
	private Server servidor;
	private long id;

	public TrataCliente(InputStream cliente, 
			Server servidor) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id = NEXT_ID;
		NEXT_ID++;
	}

	/*
	 * Ao chamar o metodo start() estamos indicando que a thread está 
	 * pronta para ser executada. Quando a thread for escalonada para
	 * execução, o metodo run() e´ invocado. A tarefa que queremos 
	 * que a thread desempenhe, portanto, deve ser implementada no metodo
	 * run
	 */
	@Override
	public void run() {
	
            /*
                aceita cliente e vai para o menu de reserva de passagens
            */
            ReservarPassagem rp = new ReservarPassagem();
            rp.menu(cliente);
	}
}