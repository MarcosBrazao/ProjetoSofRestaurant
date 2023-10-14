
package controle;

import dao.ConectaBanco;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.ModeloRecuperarSenha;


/**
 *
 * @author marco
 */
public class ControleRecuperarSenha{
    
    ConectaBanco conexao = new ConectaBanco();
    ModeloRecuperarSenha mod = new ModeloRecuperarSenha();
    
    
    //pesquisa de perguntas
    public String retornaPergunta(ModeloRecuperarSenha nomeusuario){
        conexao.conexao();
        String pergunta;
        //JOptionPane.showMessageDialog(null, cadastrousuario.getUsuario());
        conexao.executaSQL("Select * from logins where nick_login = '" + nomeusuario.getUsuario()+ "'");
        try {
            conexao.rs.last();
            pergunta = conexao.rs.getString("pergunta_login"); //obsservar nome da coluna na tabela//
            return pergunta;
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showConfirmDialog(null, "Erro ao buscar o usuário!");
        }
        return null;
    }
    
    //validar resposta
    public String validaResposta(ModeloRecuperarSenha modsenha){
        String senha;
        conexao.conexao();
        //JOptionPane.showMessageDialog(null, nomeusuario.getUsuario());
        conexao.executaSQL("Select * from logins where nick_login = '" + modsenha.getUsuario() + "'");
        
            try {
                conexao.rs.last();
                if (conexao.rs.getString("resposta_login").equals(modsenha.getResposta())){
                    senha = conexao.rs.getString("senha_login");
                    return senha;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Resposta Incorreta!");
            }
                return "Resposta incorreta!!!!!";
            }
            
                
}   
        
    
    

