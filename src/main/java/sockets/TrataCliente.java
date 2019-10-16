package sockets;

/**
 *
 * @author luis
 */
import aero.Reserva;
import aero.Voo;
import dao.VooDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Demonstrando a troca de mensagens entre processos através de sockets 
 * multithreading
 * Classe que implementa uma Thread para tratamento de um Cliente
 */
public class TrataCliente extends Thread {

    private static long NEXT_ID = 1;

    private Socket cliente;
    private ServerSocket servidor;
    private long id;

    public TrataCliente(Socket cliente,
            ServerSocket servidor) {
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
        BufferedReader entrada = null;
        PrintStream saida = null;
        try {
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            saida = new PrintStream(cliente.getOutputStream());
            //
            menu(saida, entrada);
            //
        } catch (IOException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                entrada.close();
            } catch (IOException ex) {
                Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void menu(PrintStream saida, BufferedReader entrada) throws IOException {

        int escolha = 0;
        while (true) {
            saida.println("Listar Voos < 0\nreservar Voo >= 0");
            escolha = Integer.parseInt(entrada.readLine());

            if (escolha >= 0) {
                int codVoo = 0;
                saida.print("Voltar: 0\nCodigo do Voo: ");
                codVoo = Integer.parseInt(entrada.readLine());
                if (codVoo == 0) {
                    continue;
                }
                Voo voo = new VooDAO().findById(codVoo);
                if (voo == null) {
                    saida.println("Não existe esse voo no sistema!");
                }

                saida.println("Quantos assentos deseja reservar?");
                escolha = Integer.parseInt(entrada.readLine());
                if (codVoo == 0) {
                    continue;
                }
                escolha = Integer.parseInt(entrada.readLine());
                if (voo.qtdAssLivres() < escolha) {
                    saida.print("Erro: Falta de assento, Existem " + voo.qtdAssLivres() + " Assentos Disponiveis");
                }
                String cpf = null;
                saida.print("Digite seu CPF: ");
                cpf = entrada.readLine();
                Reserva r = new Reserva(codVoo, voo.getAviao().getAssentoLivre().getcodAssento(), cpf);
                reservar(r, saida);
            } else if (escolha < 0) {
                listarVoos(saida);//lista voos existentes
            }
        }

    }

    public static void listarVoos(PrintStream saida) {
        VooDAO vdao = new VooDAO();
        List<Voo> voos = vdao.findAll();
        for (Voo voo : voos) {
           saida.println(voo.toString());
        }
    }

    public static synchronized void reservar( Reserva reserva, PrintStream saida) {
        VooDAO vdao = new VooDAO();
        Voo voo = vdao.findById(reserva.getCodVoo());
        voo.setReserva(reserva);
        saida.println("Reserva confirmada!/n----------------------------------------------\n");
        saida.println(reserva.toString());
        saida.println("----------------------------------------------");
    }
}
