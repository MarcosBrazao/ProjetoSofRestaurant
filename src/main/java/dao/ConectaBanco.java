package dao;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConectaBanco {

    public Statement stm; // realiza pesquisa no banco de dados
    public ResultSet rs; // armazena o resultado de uma pesquisa realizada no Steatement
    private String driver = "org.postgresql.Driver"; // identifica o serviço do bando de dados
    private String caminho = "jdbc:postgresql://localhost:5432/restaurante"; // local criado do banco de dados 
    private String usuario = "postgres";
    private String senha = "1990";
    public Connection conn; // realiza conexão no banco de dados 

    public void conexao() { // método responsavel por realizar conexão com banco
        try {// tentativa inicial de conexão
            System.setProperty("jdbc.Drivers", "driver");// define a propriedade do driver de conexão
            conn = DriverManager.getConnection(caminho, usuario, senha);// realiza conexão com banco de dados
          //  JOptionPane.showMessageDialog(null, "Conectado com sucesso!!"); // imprime menssagem 
        } catch (SQLException ex) {// tentaiva falhar excessão de resposta
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de Conexão!!\n Erro" + ex.getMessage());// imprime menssagem
        }

    }
    
    public void executaSQL(String sql){
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE,rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro de executa SQL!!\n Erro" + ex.getMessage());
        }
    }

    public void desconecta() { // método para fechar conexão com banco de dados
        try {
            conn.close();// fecha conexão
            JOptionPane.showMessageDialog(null, "Desconectado com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão!!\n Erro" + ex.getMessage());
        }
    }
}
