package teste;

import connect.DataBase;
import java.sql.Connection;

/**
 *
 * @author luis
 */
public class TestaConexao {
    public static void main(String[] args) {
        Connection db = DataBase.getConnection();
        
       
    }
}
