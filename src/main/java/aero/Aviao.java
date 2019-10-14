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
import java.util.ArrayList;

public class Aviao {
	private int codigo, qtdAssentos;
	private String marca, modelo;
	private ArrayList<Assento> assentos;
	
	
	public Aviao(int codigo, String marca, String modelo) {
		
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
	
}
