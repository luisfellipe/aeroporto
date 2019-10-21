package aero;

import dao.AssentoDAO;

/**
 *
 * @author luis
 */ 
public class Assento {
	private int codAssento, codAviao;
        private boolean reservado = false;
	
	public Assento(int codAssento, int codAviao, boolean reservado) {
            this.codAssento = codAssento;
            this.codAviao = codAviao;
            this.reservado = reservado;     
	}
        
        public Assento(int codAviao, boolean reservado) {
            this.codAviao = codAviao;
            this.reservado = reservado;     
	}
	
	public int getCodAssento() {
            return codAssento;
	}
	
	public int getCodAviao() {
            return codAviao;
	}
        
        public boolean isReservado(){
            return reservado;
        }
        
        public void setReservado(boolean valor, int codAssento){
            reservado = valor;
        } 
}
