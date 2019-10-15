package dao;

import aero.Reserva;
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
public class ReservaDAO {

    public void insert(Reserva reserva) {
        try {
            String sql = "INSERT INTO reserva(cpf, cod_assento,cod_voo ) values (?, ?, ?)";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setString(1, reserva.getCpf());
            stmt.setInt(2, reserva.getCodAssento());
            stmt.setInt(3, reserva.getCodVoo());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int codVoo, int codAssento) {

        try {
            String sql = "DELETE FROM reserva where codvoo=? and cod_assento=?";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
            stmt.setInt(2, codAssento);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reserva findById(int codReserva) {
        try {
            String sql = "SELECT cod_voo, cod_assento, cpf FROM reserva WHERE cod_reserva=?";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codReserva);
            ResultSet rs = stmt.executeQuery();
            Reserva reserva = new Reserva(
                    rs.getInt(1), rs.getInt(2), rs.getString(3));

            return reserva;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Reserva> findAll() {

        String sql = "SELECT * FROM reserva ";
        PreparedStatement stmt = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Reserva> reservas = new ArrayList<>(10);
        try {
            while (rs.next()) {
                Reserva reserva = null;
                try {
                    reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                reservas.add(reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return reservas;
    }
}
