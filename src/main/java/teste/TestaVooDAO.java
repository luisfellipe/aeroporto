package teste;

import aero.Voo;
import dao.VooDAO;

/**
 *
 * @author luis
 */
public class TestaVooDAO {

    public static void main(String[] args) {
        VooDAO vdao = new VooDAO();
        /*vdao.insert(new Voo(455, 1, "São Paulo", "Rio deas pedras", "10/08/2019 23:56", "11/08/2019 2:00"));
        vdao.insert(new Voo(456, 1, "São Paulo", "Rio de Jane", "10/08/2019 23:56", "11/08/2019 2:00"));
        vdao.insert(new Voo(457, 1, "São Paulo", "Rio de neiro", "10/08/2019 23:56", "11/08/2019 2:00"));
        vdao.insert(new Voo(458, 1, "São Paulo", "Rio de eiro", "10/08/2019 23:56", "11/08/2019 2:00"));
        vdao.insert(new Voo(568, 1, "São Paulo", "Rio de Jairo", "10/08/2019 23:56", "11/08/2019 2:00"));
        vdao.insert(new Voo(569, 1, "São Paulo", "Rio de Jaro", "10/08/2019 23:56", "11/08/2019 2:00"));
        vdao.insert(new Voo(896, 1, "São Paulo", "Rio de Jeiro", "10/08/2019 23:56", "11/08/2019 2:00"));*/

        vdao.findAll().forEach(a -> System.out.println(a.toString()));
        System.out.println("");
        System.out.println(vdao.findById(455));
        
        vdao.delete(455);
         System.out.println("");
        System.out.println(vdao.findById(455));
    }
}
