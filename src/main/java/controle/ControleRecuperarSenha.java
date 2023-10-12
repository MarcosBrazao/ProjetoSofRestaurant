
package controle;

import dao.ConectaBanco;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.ModeloRecuperarSenha;


/**
 *
 * @author marco
 */
public class ControleRecuperarSenha{
    ConectaBanco conexao = new ConectaBanco();
    ModeloRecuperarSenha mod = new ModeloRecuperarSenha();
    public String RetornaPergunta(String nomeusuario){
        conexao.conexao();
        String pergunta;
        conexao.executaSQL("Select * from logins where nicl_login = '"+nomeusuario+"'");
        try {
            pergunta = conexao.rs.getString("pergunta_login"); //obsservar nome da coluna na tabela//
            return pergunta;
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "Erro ao buscar o usuário!\nErro: "+ex);
        }
        return null;
    }
    
    
}
