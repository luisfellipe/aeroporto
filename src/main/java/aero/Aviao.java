/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aero;

/**
 *
 * @author luis
 */
import dao.AssentoDAO;
import java.util.ArrayList;

public class Aviao {
	private int codigo, qtdAssentos;
	private String marca, modelo;
	private ArrayList<Assento> assentos;
	
	
	public Aviao(int codigo, String marca, String modelo) {
		this.codigo = codigo;
                this.marca = marca;
                this.modelo = modelo;
                AssentoDAO assdao = new AssentoDAO();
	}
	
	public int getCod() {
		return codigo;
	}
	public String getMarca() {
		return marca;
	}
	public String getModelo() {
		return modelo;
	}
        
        public int qtdAssentos(){
            return qtdAssentos;
        }
        public Assento getAssentoLivre(){
            for(Assento a : assentos){
                if(!a.estaReservado()) return a;
            }
            return null;
        }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(codigo)
                .append(" Marca: ").append(marca)
                .append("Modelo: ").append(modelo)
                .append("Assentos(qtd): ").append(qtdAssentos);
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
        
        
	
}
