import java.sql.Connection;
import java.sql.PreparedStatement;

public class ContaDAO extends BancoDBManager {
    private Connection conexao = ObterConexao();
    private PreparedStatement preStmt;

    public static void InserirNoBanco(Conta conta){
        
    }

    public static void DeletarNoBanco(int numeroDaConta){

    }

    public static void ModificarSenhaNoBanco(int numeroDaConta, String novaSenha){

    }

    public static void ModificarSaldoNoBanco(int numeroDaConta, double novoSaldo){

    }

    public static void LerTudoDoBanco(){

    }

    public static void LerContaDoBanco(int numeroDaConta){

    }


}
