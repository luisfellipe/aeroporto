package teste;

import aero.Aviao;
import dao.AviaoDAO;
import java.util.List;

/**
 *
 * @author luis
 */
public class TestaAviaoDAO {
    public static void main(String[] args) {
        AviaoDAO avdao = new AviaoDAO();
       List<Aviao> lista = avdao.findAll();
        lista.get(0).toString();
        lista.forEach(av -> System.out.println(av.toString()));
       Aviao aviao = avdao.findById(1);
        System.out.println(aviao.toString());
        
       aviao = new Aviao(5464, "Italia", "Markov", 240);
       avdao.insert(aviao);
       aviao = avdao.findById(5464);
       if(aviao != null){
        System.out.println(aviao.toString());
       }else{
           System.out.println("Aviao não inserido");
       }
    }
}
