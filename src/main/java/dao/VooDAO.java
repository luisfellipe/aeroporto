package dao;

import aero.Reserva;
import aero.Voo;
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
public class VooDAO {

    public void insert(Voo voo) {
        String sql = "INSERT INTO voo(codvoo, codaviao, origem, destino, datasaida, datachegada) VALUES(?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, voo.getCodVoo());
            stmt.setInt(2, voo.getAviao().getCod());
            stmt.setString(3, voo.getOrigem());
            stmt.setString(4, voo.getDestino());
            stmt.setString(5, voo.getDataSaida());
            stmt.setString(6, voo.getDataChegada());
            stmt.execute();
            int qtdAssentos, codaviao = voo.getCodAviao();
            qtdAssentos = new AviaoDAO().findById(codaviao).qtdAssentos();
            sql = "INSERT INTO assentos(codassento, codaviao, codvoo,reservado) VALUES(?,?,?,?)";
            while (qtdAssentos > 0) {
                stmt.setInt(1, qtdAssentos);
                stmt.setInt(2, codaviao);
                stmt.setInt(3, voo.getCodVoo());
                stmt.setBoolean(4, false);
                qtdAssentos--;
            }

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
     * encontra voo pelo codvoo
     * retorna null se não existe voo com codigo passsado
     */
    public Voo findById(int codvoo) {
        ReservaDAO reservaDAO = new ReservaDAO();
        ArrayList<Reserva> reservas = reservaDAO.reservasVoo(codvoo);

        String sql = "SELECT * FROM voo WHERE codvoo=?";
        PreparedStatement stmt;
        ResultSet rs;
        Voo voo = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codvoo);
            rs = stmt.executeQuery();
            if (rs.first()) {
                voo = new Voo(
                        rs.getInt("codvoo"),
                        rs.getInt("codaviao"),
                        rs.getString("origem"),
                        rs.getString("destino"),
                        rs.getString("datasaida"),
                        rs.getString("datachegada"));
            }
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
        List<Voo> voosLista = new ArrayList<>();
        ArrayList<Reserva> reservas;
        String sql = "SELECT * FROM voo";
        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                int codVoo = rs.getInt("codvoo");
                voosLista.add(new Voo(
                        codVoo,
                        rs.getInt("codaviao"),
                        rs.getString("origem"),
                        rs.getString("destino"),
                        rs.getString("datasaida"),
                        rs.getString("datachegada")));
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
        this.delete(voo.getCodVoo());
        this.insert(voo);
    }

    /*
     * Deleta um voo do banco de Dados
     */
    public void delete(int cod) {
        String sql = "DELETE FROM voo WHERE codvoo=?";
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
    
    public int qtdAssentosLivres(int codVoo){
        String sql = "SELECT codvoo FROM assentos WHERE codvoo=? AND reservado=?";
        PreparedStatement stmt;
        ResultSet rs = null;
        int total = 0;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
            stmt.setBoolean(2, false);
            rs = stmt.executeQuery();
            while(rs.next()) total++;
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VooDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
