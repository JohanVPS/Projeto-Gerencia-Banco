import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class BancoDBManager {
    public static Connection ObterConexao(){
        Connection conexao = null;

        final String driver = "com.mysql.cj.jdbc.Driver";
        final String conn = "jdbc:mysql://localhost:3306/bancogerencia";
        final String user = "root";
        final String password = "";

        try {
            Class.forName(driver);

            conexao = DriverManager.getConnection(conn, user, password);

            //JOptionPane.showMessageDialog(null, "Conectado!", "Conexão - status", 1);
            return conexao;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Nao foi possível se conectar ao Banco de Dados!", "Conexão - status", 1);
            erro.printStackTrace();
        } catch (ClassNotFoundException erro){
            JOptionPane.showMessageDialog(null, "O Driver JDBC não foi encontrado!", "Conexão - status", 1);
            erro.printStackTrace();
        }

        return conexao;
    }
}
