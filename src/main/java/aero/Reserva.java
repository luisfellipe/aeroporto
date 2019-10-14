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

public class Reserva {
	private String cpf;
	private int codVoo, codAssento;
	
	public Reserva( int codVoo, int codAssento, String cpf) {
		this.codAssento = codAssento;
		this.codVoo = codVoo;
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public int getCodVoo() {
		return codVoo;
	}
	public int getCodAssento() {
		return codAssento;
	}
	
}
