package main;

import aero.Aviao;
import aero.Voo;
import dao.AviaoDAO;
import dao.VooDAO;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class Aeroporto {

    public static void main(String[] args) {
        Aeroporto aeroporto = new Aeroporto();

        Scanner leitor = new Scanner(System.in);

        int escolha;
        while (true) {
            escolha = menu();
            if (escolha > 0) {
                System.out.println("[0]\t\tListar Avioes");
                System.out.println("[1]\t\tAdicionar Aviao");
                System.out.println("[2]\t\tRemover Aviao");
                System.out.println("[< 0 ou > 2]\tVoltar");

                escolha = leitor.nextInt();

                switch (escolha) {
                    case 0:
                        aeroporto.listarAvioes();
                        break;
                    case 1:
                        aeroporto.addAviao();
                        break;
                    case 2:
                        aeroporto.removeAviao();
                        break;
                    default:
                        break;
                }
            } else if (escolha < 0) {
                System.out.println("[0]\t\tListar Voos");
                System.out.println("[1]\t\tAdicionar Voos");
                System.out.println("[2]\t\tRemover Voos");
                System.out.println("[< 0 ou > 2]\tVoltar");
                escolha = leitor.nextInt();

                switch (escolha) {
                    case 0:
                        aeroporto.listarVoos();
                        break;
                    case 1:
                        aeroporto.addVoo();
                        break;
                    case 2:
                        aeroporto.removeVoo();
                        break;
                    default:
                        break;
                }
            } else {
                break;
            }
        }
        System.exit(0);
    }

    private void listarAvioes() {
        AviaoDAO aviaoDAO = new AviaoDAO();
        List<Aviao> lista = aviaoDAO.findAll();
        lista.forEach((av) -> {
            System.out.println(av.toString());
        });
    }

    private void listarVoos() {
        VooDAO vooDAO = new VooDAO();
        List<Voo> lista = vooDAO.findAll();
        lista.forEach((voo) -> {
            System.out.println(voo.toString());
        });
    }

    private void addAviao() {
        Scanner leitor = new Scanner(System.in);
        int qtdAssentos, codigo;
        String marca, modelo;

        System.out.println("===== Novo avião =====");
        System.out.println("Informe o codigo do Avião: ");
        codigo = leitor.nextInt();
        System.out.println("Informe a quantidade de assentos: ");
        qtdAssentos = leitor.nextInt();
        System.out.println("Informe a marca: ");
        marca = leitor.next();
        System.out.println("Informe o modelo: ");
        modelo = leitor.next();
        try {
            Aviao newAviao = new Aviao(codigo, marca, modelo, qtdAssentos);
            new AviaoDAO().insert(newAviao);
        } catch (Exception ex) {
            Logger.getLogger(Aeroporto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addVoo() {
        Scanner leitor = new Scanner(System.in);
        int codAviao = 0, codVoo = 0;
        String origem, destino, dataSaida, dataChegada;

        System.out.println("===== Novo voo =====");
        System.out.println("Informe um codigo para o voo: ");
        codVoo = leitor.nextInt();
        System.out.println("Informe o codigo do aviao: ");
        codAviao = leitor.nextInt();
        System.out.println("Informe a origem: ");
        origem = leitor.next();
        System.out.println("Informe o destino: ");
        destino = leitor.next();
        System.out.println("Informe a data de saida: ");
        dataSaida = leitor.next();
        System.out.println("Informe a data de chegada: ");
        dataChegada = leitor.next();

        try {
            Voo newVoo = new Voo(codVoo, codAviao, origem, destino, dataSaida, dataChegada);
            new VooDAO().insert(newVoo);
        } catch (Exception ex) {
            Logger.getLogger(Aeroporto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeVoo() {
        Scanner leitor = new Scanner(System.in);
        int codVoo;

        System.out.println("===== Remove voo =====");
        System.out.println("Informe o codigo do voo: ");
        codVoo = leitor.nextInt();
        try {
            VooDAO vooDAO = new VooDAO();
            vooDAO.delete(codVoo);
        } catch (Exception ex) {
            Logger.getLogger(Aeroporto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeAviao() {
        Scanner leitor = new Scanner(System.in);
        int codAviao;

        System.out.println("===== Remove avião =====");
        System.out.println("Informe o código do avião a ser removido: ");
        codAviao = leitor.nextInt();
        System.out.println(codAviao);

        try {
            new AviaoDAO().delete(codAviao);
        } catch (Exception ex) {
            Logger.getLogger(Aeroporto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static int menu() {
        Scanner leitor = new Scanner(System.in);
        System.out.println("::: Server :::");
        System.out.println("Avião:(> 0)");
        System.out.println("Voo: (0 <)");
        System.out.println("Encerrar: 0");
        System.out.print("Digite sua escolha: ");
        return leitor.nextInt();
    }

}
