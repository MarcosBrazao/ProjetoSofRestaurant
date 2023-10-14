/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package controle;

import dao.ConectaBanco;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloLogin;

public class ControleLogin {

    ConectaBanco conn = new ConectaBanco();

    public boolean validaLogin(ModeloLogin modLog) {
        conn.conexao();
        if ((modLog.getLogin() != null) && (modLog.getSenha() != null)) {
            conn.executaSQL("select * from logins where nick_login = '" + modLog.getLogin() + "'");// faz pesquisa se o login existe

            try {
                if (conn.rs.next()) { // Verifica se há um registro retornado
                    String nick_login = conn.rs.getString("nick_login");
                    String senha_login = conn.rs.getString("senha_login");

                    if (nick_login.equals(modLog.getLogin()) && senha_login.equals(modLog.getSenha())) {
                        return true;
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                // Trate o erro apropriadamente, por exemplo, com uma mensagem de erro.
            } finally {
                conn.desconecta(); // Certifique-se de fechar a conexão quando terminar.
            }
        }

        return false;
    }
}
