package main;

import aero.Voo;
import dao.VooDAO;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author luis
 */
public class ProgramaCliente {

	public static void menu() {
            Scanner leitor = new Scanner(System.in);
            int escolha = 0;
            while(true){
                System.out.println("Listar Voos < 0");
                System.out.println("reservar Voo >= 0");
                escolha = leitor.nextInt();
                if(escolha >= 0){
                    
                }else if(escolha < 0){
                    
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
        
        public static void reservar(int codVoo){
            VooDAO vdao = new VooDAO();
            Voo voo = vdao.findById(codVoo);
        }

}
