package dao;

import aero.Reserva;
import java.util.List;

/**
 *
 * @author luis
 */
public class ReservaDAO {
    public void insert(Reserva reserva){
        
    }
     
    public void delete(int cod){
        
    }
   
    
    public Reserva findById(int cod){
        Reserva reserva = new Reserva(4, 0, "");
        return reserva;
    }
    
    public List<Reserva> findAll(){
        return null;
    }
}
