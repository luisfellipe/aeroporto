package sockets;

/**
 *
 * @author luis felipe
 */
import aero.Assento;
import aero.Reserva;
import aero.Voo;
import dao.AssentoDAO;
import dao.VooDAO;
import java.io.IOException;
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
        Scanner entrada = null;
        PrintStream saida;
        try {
            //saida do cliente
            saida = new PrintStream(cliente.getOutputStream());
            //lendo as informacoes que o cliente enviar
            entrada = new Scanner(cliente.getInputStream());
            //
            menu(saida, entrada);
            //
        } catch (IOException ex) {
            Logger.getLogger(TrataCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrada.close();
        }
    }

    public static void menu(PrintStream saida, Scanner entrada) throws IOException {

        int escolha;
        while (true) {
            enviarParaCliente("Listar Voos < 0\nReservar Voo >= 0", saida);
            escolha = entrada.nextInt();
            if (escolha >= 0) {
                int codVoo;
                enviarParaCliente("Voltar: 0\nCodigo do Voo: ", saida);
                codVoo = entrada.nextInt();
                if (codVoo == 0) {
                    continue;
                }

                Voo voo = new VooDAO().findById(codVoo);
                if (voo == null) {
                    enviarParaCliente("Não existe esse voo no sistema!", saida);
                    continue;
                }

                String cpf;
                enviarParaCliente("Digite seu CPF: ", saida);
                cpf = entrada.next();

                enviarParaCliente("Quantos assentos deseja reservar?", saida);
                escolha = entrada.nextInt();
                for (int i = 0; i < escolha; i++) {
                    if (voo.qtdAssLivres(codVoo) <= 0) {
                        enviarParaCliente("Erro: Falta de assento disponível, existem " + voo.qtdAssLivres(codVoo) + " assentos disponiveis", saida);
                        break;
                    }
                    reservar(voo, cpf, saida);
                }
            } else if (escolha < 0) {
                listarVoos(saida);//lista voos existentes
            }
        }

    }

    public static void listarVoos(PrintStream saida) {
        VooDAO vdao = new VooDAO();
        List<Voo> voos = vdao.findAll();
        if (voos.isEmpty()) {
            enviarParaCliente("Não há voos cadastrados", saida);
        } else {
            voos.forEach(voo -> enviarParaCliente(voo.toString(), saida));
        }
    }

    /*
     * reserva o uma passagem para aquele voo
     * as reservas são sincronizadas, ou seja essa função só pode ser acessada uma Thread por vez
     */
    public static synchronized void reservar( Voo voo, String cpf, PrintStream saida) {
        Assento assentoLivre = voo.getAssentoLivre();
        if (assentoLivre == null) {
            enviarParaCliente("Assento indisponível", saida);
        } else {
            AssentoDAO assentoDAO = new AssentoDAO();
            assentoDAO.reservaAssento(true, assentoLivre.getCodAssento(), voo.getCodVoo());
            Reserva r = new Reserva(voo.getCodVoo(), assentoLivre.getCodAssento(), cpf);
            voo.addReserva(r);

            StringBuilder sb = new StringBuilder();
            enviarParaCliente(sb
                    .append("Reserva confirmada!")
                    .append("\n----------------------------------------------\n")
                    .append(r.toString())
                    .append("\n----------------------------------------------")
                    .toString(), saida);
        }
    }

    public static void enviarParaCliente(String mensagem, PrintStream saida) {
        saida.println(mensagem);
        saida.println("EOO");
    }
}
