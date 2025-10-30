package main.java.br.com.sistemabancario.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:/sistemaBancario";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            JOptionPane.showMessageDialog(null,"Estabelecendo conexão com o Banco de dados...");
            return connection;
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null,"Erro ao conectar ao Banco de dados: " + error.getMessage());
            JOptionPane.showMessageDialog(null,"Banco de dados não conectado. Algumas funcionalidades podem não funcionar.");
            return null;
        }
    }

    public static void main(String[] args){
        Connection connection_db = getConnection();
        if(connection_db != null){
            JOptionPane.showMessageDialog(null,"Conexão bem-sucedida!");
        }
    }
}
