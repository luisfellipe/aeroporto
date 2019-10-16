package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


/*
 * Demonstrando a troca de mensagens entre processos
 * Classe Cliente, que envia mensagens a um Servidor
 * 
 */
public class Cliente {

    public static void main(String[] args) {
        /*
	 * Tentando se conectar ao servidor, que está rodando
	 * no localhost, porta 8090
         */
        try {
            Socket cliente = new Socket("127.0.0.1", 8090);
            System.out.println("Cliente conectado!");

            /* scanner para leitura da mensagem digitada pelo usuario. 
             * Usuario digita uma mensagem no console e essa mensagem é enviada ao servidor.
             */
            Scanner teclado = new Scanner(System.in);
            //buffer de saida
            PrintStream saida = new PrintStream(
                    cliente.getOutputStream());
            
            while(teclado.hasNextLine()){
                //lendo as informacoes que o servidor enviar
                Scanner s = new Scanner(cliente.getInputStream());
                
                //lendo a linha e "jogando" (ou escrevendo) no buffer de saida
                saida.println(teclado.nextLine());

              
                //imprimindo mensagem enviada pelo cliente
                if (s.hasNextLine()) {
                    System.out.println("Servidor: " + s.nextLine());
                }
            }

            //fechando as conexoes
            saida.close();
            teclado.close();
            cliente.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
