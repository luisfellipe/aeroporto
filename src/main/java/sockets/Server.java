package sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Demonstrando a troca de mensagens entre processos
 * Classe Servidor, que aguarda a conexão de um Cliente
 * e imprime as mensagens enviadas pelo Cliente
 * Este trata-se do primeiro exemplo, em que o servidor
 * consegue receber mensagens de apenas um cliente
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket servidor;
        Socket cliente;
        try {
            /*
             * Iniciando o servidor, na porta 8090
             */
            servidor = new ServerSocket(8090);
            System.out.println("Servidor aguardando requisições "
                    + "na porta 8090...");
            while (true) {
                //esperando por uma conexao
                cliente = servidor.accept();

                /*
                 * O próximo comando só será executado quando uma conexão 
                 * for estabelecida
                 */
                System.out.println("Nova conexão com o cliente "
                        + cliente.getInetAddress().getHostAddress());
                TrataCliente tc = new TrataCliente(cliente, servidor);
                tc.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
