/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author luis
 */
public class DataBase {

    private static Connection connection = null;
    private static String host = "localhost";
    private static String database = "db";
    private static String url = "jdbc:mysql://";
    private static String username = "root"; // nome de usuario do db
    private static String password = "12345"; //senha de acesso
    private static String status = "Não conectado!";//status da conexão

    public static Connection getConnection() {
        try {
            url = url + host + "/" + database;
            connection = DriverManager.getConnection(url, username, password);
            //testa conexão
            if (connection != null) {
                status = "Conectado com sucesso!";
            } else {
                status = " não foi possivel realizar conexão!";
            }
            return connection;
        } catch (SQLException e) {
            //Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;
        }
    }
    
    public static boolean close(){
           try {
            DataBase.getConnection().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Connection reconect(){
        DataBase.close();
        return DataBase.getConnection();
    }

}
