package aero;

/**
 *
 * @author luis
 */ 
public class Assento {
	private int codAssento, codAviao;
        private boolean reservado = false;
	
	public Assento( int codAssento, int codAviao, boolean reservado) {
		this.codAssento = codAssento;
		this.codAviao = codAviao;
                this.reservado = reservado;
                
	}
	
	public int getcodAssento() {
		return codAssento;
	}
	
	public int getCodAviao() {
		return codAviao;
	}
        public boolean estaReservado(){
            
            return reservado;
        } 
        public void setReservado(boolean valor){
            reservado = valor;
        }
        
        
}
