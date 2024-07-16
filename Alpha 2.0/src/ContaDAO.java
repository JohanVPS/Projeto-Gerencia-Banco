import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

import javax.swing.JOptionPane;

public class ContaDAO extends BancoDBManager {
    private static Connection conexao = ObterConexao();
    private static PreparedStatement preStmt;

    public static void InserirNoBanco(Conta conta) throws SQLException{
        
        try{
            preStmt = conexao.prepareStatement("INSERT INTO tab_cliente(num_conta, titular, cpf, senha, saldo, data_criacao, status) VALUES (?, ?, ?, ?, ?, ?, ?)");
            preStmt.setInt(1, conta.getNumeroDaConta());
            preStmt.setString(2, conta.getTitular());
            preStmt.setString(3, conta.getCpf());
            preStmt.setString(4, conta.getSenha());
            preStmt.setDouble(5, conta.getSaldo());
            preStmt.setString(6, conta.getDataCriacao());
            preStmt.setString(7, conta.getStatus());

            preStmt.executeUpdate();

        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", 1);
            e.printStackTrace();
        }
        
    }

    public static void ModificarStatus(int numeroDaConta, String senha) throws SQLException{
        try{
            preStmt = conexao.prepareStatement("UPDATE tab_cliente SET status = ? WHERE num_conta = ? AND saldo = ? AND senha = ?");
            preStmt.setString(1, "Encerrada");
            preStmt.setInt(2, numeroDaConta);
            preStmt.setDouble(3, 0);
            preStmt.setString(4, senha);

            preStmt.executeUpdate(); 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", 1);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Algum dos dados esta equivocado e portanto, é impossivel excluir a conta", "Falha", 1);
        }
        
    }

    public static void DeletarContaDoBanco(int numeroDaConta) throws SQLException{
        try{
            preStmt = conexao.prepareStatement("DELETE FROM tab_cliente WHERE num_conta = ? AND saldo = ?");
            preStmt.setInt(1, numeroDaConta);
            preStmt.setDouble(2, 0);

            preStmt.executeUpdate(); 
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", 1);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Algum dos dados esta equivocado e portanto, é impossivel excluir a conta", "Falha", 1);
        }
    }

    public static void ModificarSenhaNoBanco(int numeroDaConta, String novaSenha){

    }

    public static void ModificarSaldoNoBanco(int numeroDaConta, double novoSaldo){

    }

    public static void LerTudoDoBanco(){
        Conta conta = new Conta();
        ResultSet result;
        String str = "Contas Salvas no Banco de Dados:\n\n";
        try{
            preStmt = conexao.prepareStatement("SELECT * FROM tab_cliente");

            result = preStmt.executeQuery();

            while(result.next()){
                conta.setNumeroDaConta(result.getInt("num_conta"));
                conta.setTitular(result.getString("titular"));
                conta.setCpf(result.getString("cpf"));
                conta.setSenha(result.getString("senha"));
                conta.setSaldo(result.getDouble("saldo"));
                conta.setDataCriacao(result.getString("data_criacao"));
                conta.setStatus(result.getString("status"));
                str += "Numero da conta: " + conta.getNumeroDaConta() + "\n"
                + "Titular: " + conta.getTitular() + "\n"
                + "CPF: " + conta.getCpf() + "\n"
                + "Senha: " + conta.getSenha() + "\n"
                + "Saldo: " + conta.getSaldo() + "\n"
                + "Data de criacao: " + conta.getDataCriacao() + "\n"
                + "Status: " + conta.getStatus() + "\n\n"; 
            }
            
            JOptionPane.showMessageDialog(null, str, "Contas Salvas no BD", 1);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Conta LerContaDoBanco(int numeroDaConta) throws SQLException{
        Conta conta = new Conta();
        ResultSet result;
        try{
            preStmt = conexao.prepareStatement("SELECT FROM tab_cliente WHERE num_conta = ?");
            preStmt.setInt(1, numeroDaConta);

            result = preStmt.executeQuery();

            while(result.next()){
                conta.setNumeroDaConta(result.getInt("num_conta"));
                conta.setTitular(result.getString("titular"));
                conta.setCpf(result.getString("cpf"));
                conta.setSenha(result.getString("senha"));
                conta.setSaldo(result.getDouble("senha"));
                conta.setDataCriacao(result.getString("data_criacao"));
                conta.setStatus(result.getString("status"));
            }

        } catch (SQLSyntaxErrorException e){
            //e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return conta;
    }


}
