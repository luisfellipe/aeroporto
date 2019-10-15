package main;

import aero.Reserva;
import aero.Voo;
import dao.VooDAO;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luis
 */
public class ReservarPassagem {

	public static void menu(InputStream cliente) {
            Scanner leitor = new Scanner(System.in);
            int escolha = 0;
            while(true){
                StringBuffer sb = new StringBuffer("Listar Voos < 0");
                sb.append("\nreservar Voo >= 0");
                escolha = leitor.nextInt();
                if(escolha >= 0){
                    int codVoo = 0;
                    System.out.println("Voltar: 0");
                    System.out.println("Codigo do Voo: "); 
                    codVoo = leitor.nextInt();
                    if(codVoo == 0) continue; 
                    Voo voo = new VooDAO().findById(codVoo);
                    System.out.println("Quantos assentos deseja reservar?");
                    if(codVoo == 0) continue;
                    escolha = leitor.nextInt();
                    if(voo.qtdAssLivres() < escolha){
                        System.err.println("Erro: Falta de assento, Existem " + voo.qtdAssLivres() + " Assentos Disponiveis");
                    }
                    String cpf = null;
                    System.out.println("Digite seu CPF: ");
                    cpf = leitor.nextLine();
                    Reserva r = new Reserva(codVoo, voo.getAviao().getAssentoLivre().getcodAssento(), cpf);
                }else if(escolha < 0){
                    listarVoos();//lista voos existentes
                }
            }

	}
        
        public static void listarVoos(){
            VooDAO vdao = new VooDAO();
            List<Voo> voos = vdao.findAll();
            for(Voo voo: voos){
                System.out.println(voo.toString());
            }
        }
        
        public static synchronized void reservar(int codVoo, Reserva reserva){
            
            VooDAO vdao = new VooDAO();
            Voo voo = vdao.findById(codVoo);
            voo.setReserva(reserva);
            System.out.println("Reserva confirmada!/n----------------------------------------------\n");
            System.out.println(reserva.toString());
            System.out.println("----------------------------------------------");
        }

}
