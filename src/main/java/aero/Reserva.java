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
	private int codVoo, codAssento, codReserva;
	
	public Reserva( int codVoo, int codAssento, String cpf) {
		this.codAssento = codAssento;
		this.codVoo = codVoo;
		this.cpf = cpf;
                //função que gera codigo da reserva
                codReserva = (codVoo * (1/codAssento)) * (codVoo) + codAssento;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public int getCodVoo() {
		return codVoo;
	}
        /*
            retorna codigo do assento
        */
	public int getCodAssento() {
		return codAssento;
	}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CPF: ").append(cpf)
        .append(" Voo: ").append(codVoo)
             .append(" Assento: ").append(codAssento)
                .append(" Codigo: ").append(codReserva);
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
       
	
}
