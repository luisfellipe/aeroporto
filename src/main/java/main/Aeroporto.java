/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import aero.Aviao;
import aero.Reserva;
import aero.Voo;
import dao.AviaoDAO;
import exception.NotPossibleReserveException;
import java.io.NotActiveException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luis
 */
public class Aeroporto {

    public static void main(String[] args) {
        Aviao aviao;
        Voo voo;

        Scanner leitor = new Scanner(System.in);

        int escolha;
        while (true) {
            escolha = menu(leitor);
            if (escolha > 0) {
                System.out.println("Listar Avioẽs 0");
                System.out.println("Adicionar Avião 1");
                System.out.println("Remover Avião 2");
                System.out.println("Voltar (0 < ou > 2)");
                escolha = leitor.nextInt();

            } else if (escolha < 0) {
                System.out.println("Listar Voos 0");
                System.out.println("Adicionar Voos 1");
                System.out.println("Remover Voos 2");
                System.out.println("Voltar (0 < ou > 2)");
                escolha = leitor.nextInt();
            } else {
                sair();
            }

            System.exit(0);

        }

    }

    private void listarAvioes(){
        AviaoDAO aviaoDAO = new AviaoDAO();
        List<Aviao> lista =  aviaoDAO.findAll();
        for(Aviao av : lista){
            av.toString();
        }
    }
    
    private void listarVoos(){
        
    }
    private void addAviao(Aviao aviao){
        
    }
    private void addVoo(Voo voo){
        
    }
    
    private void removeVoo(int cod){
        
    }
    private void removeAviao(int cod){
        
    }
    
    private static void sair(){
        System.out.print("encerrando ");
        for(int x = 0; x < 4;x++){
            for(int y = 0; y < 1000; y++){
                if(y == 999){
                    System.out.println(". ");
                }
                if(x == 3){
                    System.out.println("bye!");
                    for(y = 0; y < 500; y++);
                }
            }
        }
    }
    
    private static int menu(Scanner leitor){
        int escolha = 0; 
        System.out.println("::: Server :::");
        System.out.println("Avião:(> 0)");
        System.out.println("Voo: (0 <)");
        System.out.println("Encerrar: 0");
        System.out.print("Digite sua escolha: ");
        escolha = leitor.nextInt();
        return escolha;
    }
    
    public static synchronized void reservar(Reserva reserva ) throws NotPossibleReserveException{
        
    }
}
