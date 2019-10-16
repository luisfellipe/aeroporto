package dao;

import aero.Aviao;
import connect.DataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class AviaoDAO {

    public void insert(Aviao aviao) {
        String sql = "INSERT INTO aviao(codaviao, marca. modelo, qtd_assentos) VALUES(?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, aviao.getCod());
            stmt.setString(2, aviao.getMarca());
            stmt.setString(3, aviao.getModelo());
            stmt.setInt(4, aviao.qtdAssentos());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void update(Aviao aviao){
        this.delete(aviao.getCod());
        this.insert(aviao);
    }
    public void delete(int cod) {
        String sql = "DELETE * FROM aviao WHERE codaviao=?";
        PreparedStatement stmt = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, cod);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Aviao findById(int cod) {
        String sql = "SELECT * FROM voo WHERE codaviao=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Aviao aviao = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, cod);
            rs = stmt.executeQuery();
            aviao = new Aviao(rs.getInt("codaviao"), rs.getString("marca"), rs.getString("modelo"),rs.getInt("qtd_assentos") );
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aviao;
    }

    public List<Aviao> findAll() {
        List<Aviao> aviaoLista = new ArrayList<>(10);
        String sql = "SELECT * FROM Aviao";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                aviaoLista.add(new Aviao(rs.getInt("codaviao"), rs.getString("marca"), rs.getString("modelo"),rs.getInt("qtd_assentos")));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aviaoLista;
    }
}
