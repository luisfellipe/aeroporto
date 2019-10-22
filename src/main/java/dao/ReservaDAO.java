package dao;

import aero.Reserva;
import connect.DataBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class ReservaDAO {

    public void insert(Reserva reserva) {
        try {
            String sql = "INSERT INTO reserva(cpf, codassento, codvoo) values (?, ?, ?)";
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
    public void update(Reserva reserva){
        this.delete(reserva.getCodReserva());
        this.insert(reserva);
    }

    public void delete(int codReserva) {

        try {
            String sql = "DELETE FROM reserva where codreserva=?";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codReserva);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Reserva findById(int codReserva) {
        Reserva reserva = null;
        try {
            String sql = "SELECT codvoo, codassento, cpf FROM reserva WHERE codreserva=?";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codReserva);
            ResultSet rs = stmt.executeQuery();
           reserva = new Reserva(
                    rs.getInt(1), rs.getInt(2), rs.getString(3));
           stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reserva;
    }

    public ArrayList<Reserva> findAll(int codVoo) {
        ArrayList<Reserva> reservas = null;
        String sql = "SELECT * FROM reserva WHERE codvoo=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
            rs = stmt.executeQuery();
            reservas = new ArrayList<>(10);

            while (rs.next()) {
                Reserva reserva = null;
                reservas.add(new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }
    
    public ArrayList<Reserva> reservasVoo(int codVoo) {
        ArrayList<Reserva> reservas = null;
        String sql = "SELECT * FROM reserva WHERE codvoo=?";
        PreparedStatement stmt;
        ResultSet rs;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
            rs = stmt.executeQuery();
            reservas = new ArrayList<>();

            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getInt("codreserva"),
                        rs.getInt("codvoo"),
                        rs.getInt("codassento"),
                        rs.getString("cpf")));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reservas;
    }
}
