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
            String sql = "INSERT INTO assento(codassento, codaviao, reservado) values (?, ?, ?)";
            PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, assento.getCodAssento());
            stmt.setInt(2, assento.getCodAviao());
            stmt.setBoolean(3, assento.isReservado());
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

    /*
     * retorna uma lista de reservas emm voos a qual aquele assento esta relacionado
     */
    public List<Reserva> findById(int codAssento) {
        ArrayList<Reserva> lista = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT codvoo, codassento, cpf FROM reserva WHERE codassento=?";
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codAssento);
            rs = stmt.executeQuery();
            lista = new ArrayList<>(10);
            while (rs.next()) {
                lista.add(new Reserva(
                        rs.getInt(1), rs.getInt(2), rs.getString(3)));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Assento> findAll(int codVoo) {

        String sql = "SELECT * FROM assento WHERE codvoo=?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Assento> assentos = null;
        try {
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
            rs = stmt.executeQuery();
            assentos = new ArrayList<>(10);
            while (rs.next()) {
                Assento ass = null;
                ass = new Assento(
                        rs.getInt("codassento"),
                        rs.getInt("codaviao"),
                        rs.getBoolean("reservado")
                );

                assentos.add(ass);
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return assentos;
    }

    public void reservaAssento(boolean reserva, int codAssento, int codvoo) {
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

    public ArrayList<Assento> getAssentosAviao(int codVoo) {
        ArrayList<Assento> assentos = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM assento WHERE codvoo=?";
            stmt = DataBase.getConnection().prepareStatement(sql);
            stmt.setInt(1, codVoo);
            rs = stmt.executeQuery();
            assentos = new ArrayList<>(10);
            while (rs.next()) {
                assentos.add(new Assento(
                        rs.getInt("codassento"),
                        rs.getInt("codaviao"),
                        rs.getBoolean("reservado")));
            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return assentos;
    }
}
