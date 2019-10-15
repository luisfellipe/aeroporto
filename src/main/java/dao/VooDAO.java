package dao;

import aero.Voo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class VooDAO {
    
    /*
     * encontra voo pelo cod
     * retorna null se não existe voo com codigo passsado
    */
    public Voo findById(int cod){
        return null;
    }
    /*
     * Retorna todos os voos existentes
    */
    public List<Voo> findAll(){
        List<Voo> voosLista = new ArrayList<Voo>(10);
        return voosLista;
    }
    /*
     * Atualiza um voo no Banco de Dados
    */
    public void update(Voo voo){
        
    }
    /*
     * Deleta um voo do banco de Dados
    */
    public void delete(int cod){
        
    }
    
}
