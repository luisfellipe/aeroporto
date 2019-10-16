package dao;

import aero.Voo;
import connect.DataBase;
import java.sql.Connection;
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
public class VooDAO {

    public void insert(Voo voo){
        String sql = "INSERT INTO voo VALUES(codvoo,codaviao, origem, destino, datasaida, datachegada)"
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, voo.getCodigo());
            stmt.setInt(2, voo.getAviao().getCod());
            stmt.setString(3, voo.getOrigem());
            stmt.setString(4, voo.getDestino());
            stmt.setString(5, voo.getDataSaida());
            stmt.setString(6, voo.getDataChegada());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /*
     * encontra voo pelo cod
     * retorna null se não existe voo com codigo passsado
     */
    public Voo findById(int cod) {
        String sql = "SELECT * FROM voo WHERE codvoo=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Voo voo = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, cod);
            rs = stmt.executeQuery();
            voo = new Voo(rs.getInt("codigo"), rs.getInt("codaviao"), rs.getString("origem"),
                    rs.getString("destino"), rs.getString("datasaida"), rs.getString("datachegada"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return voo;
    }

    /*
     * Retorna todos os voos existentes
     */
    public List<Voo> findAll() {
        List<Voo> voosLista = new ArrayList<Voo>(10);
        String sql = "SELECT * FROM voo";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                voosLista.add(new Voo(rs.getInt("codigo"), rs.getInt("codaviao"), rs.getString("origem"),
                        rs.getString("destino"), rs.getString("datasaida"), rs.getString("datachegada")));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return voosLista;
    }

    /*
     * Atualiza um voo no Banco de Dados
     */
    public void update(Voo voo) {
        this.delete(voo.getCodigo());
        this.insert(voo);
    }

    /*
     * Deleta um voo do banco de Dados
     */
    public void delete(int cod) {
        String sql = "DELETE * FROM voo WHERE codvoo=?";
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

}
