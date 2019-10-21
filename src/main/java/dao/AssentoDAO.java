package dao;

import aero.Assento;
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
public class AssentoDAO {

    public void insert(Assento assento) {
        try {
            String sql = "INSERT INTO assento(codaviao, reservado) values (?, ?)";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, assento.getCodAviao());
            stmt.setBoolean(2, assento.isReservado());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int codVoo, int codAssento) {

        try {
            String sql = "DELETE FROM reserva where codvoo=? and codassento=?";
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
            String sql = "SELECT codvoo, codassento, cpf FROM reserva WHERE codreserva=?";
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

    public List<Assento> findAll(int codVoo) {

        String sql = "SELECT * FROM assento WHERE codvoo=?";
        PreparedStatement stmt = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Assento> assentos = new ArrayList<>(10);
        try {
            while (rs.next()) {
                Assento ass = null;
                try {
                    ass = new Assento(
                            rs.getInt("codassento"),
                            rs.getInt("codaviao"),
                            rs.getBoolean("reservado")
                    );
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                assentos.add(ass);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assentos;
    }
    
    public void reservaAssento(boolean reserva, int codAssento, int codvoo){
         try {
            String sql = "UPDATE assento set reservado=? WHERE codasssento=? AND codvoo=?";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setBoolean(1, reserva);
            stmt.setInt(2, codAssento);
            stmt.setInt(3, codvoo);
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Assento> getAssentosAviao(int codAviao, int codVoo){
        ArrayList<Assento> assentos = new ArrayList<>();
        try {
           String sql = "SELECT * FROM assento WHERE codaviao=? AND codvoo=?";
           PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
           stmt.setInt(1, codAviao);
           stmt.setInt(2, codVoo);
           ResultSet rs;
           rs = stmt.executeQuery();
           Assento assento;
           while (rs.next()) {
                assento = new Assento(
                        rs.getInt("codassento"),
                        rs.getInt("codaviao"),
                        rs.getBoolean("reservado"));
                assentos.add(assento);
           }
           stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assentos;
    }
}

