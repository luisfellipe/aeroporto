package dao;

import aero.Assento;
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
        String sql = "INSERT INTO aviao(marca, modelo, qtdassentos) VALUES(?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setString(1, aviao.getMarca());
            stmt.setString(2, aviao.getModelo());
            stmt.setInt(3, aviao.qtdAssentos());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AviaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = null;
        sql = "SELECT codAviao FROM aviao WHERE marca=? AND modelo=? AND qtdassentos = ?";
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setString(1, aviao.getMarca());
            stmt.setString(2, aviao.getModelo());
            stmt.setInt(3, aviao.qtdAssentos());
            rs = stmt.executeQuery();
            if (rs.first()) {
                AssentoDAO assentoDAO = new AssentoDAO();
                int codAviao = rs.getInt("codaviao");
                for (int i = 0; i < aviao.qtdAssentos(); i++) {
                    assentoDAO.insert(new Assento(codAviao, false));
                }
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AviaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Aviao aviao) {
        this.delete(aviao.getCod());
        this.insert(aviao);
    }

    public void delete(int cod) {
        String sql = "DELETE FROM aviao WHERE codaviao=?";
        PreparedStatement stmt;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, cod);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Aviao findById(int codAviao) {
        AssentoDAO assentoDAO = new AssentoDAO();
        String sql = "SELECT * FROM aviao WHERE codaviao=?";
        PreparedStatement stmt;
        ResultSet rs;
        Aviao aviao = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codAviao);
            rs = stmt.executeQuery();
            if (rs.first()) {
                aviao = new Aviao(
                        rs.getInt("codaviao"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("qtdassentos"));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return aviao;
    }

    public List<Aviao> findAll() {
        List<Aviao> listaAviao = new ArrayList<>(10);
        AssentoDAO assentoDAO = new AssentoDAO();
        String sql = "SELECT * FROM aviao";
        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listaAviao.add(new Aviao(
                        rs.getInt("codaviao"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("qtdassentos")));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaAviao;
    }
}
