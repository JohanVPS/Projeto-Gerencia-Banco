import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

import javax.swing.JOptionPane;

public class Conta extends ContaDAO{
    private String titular;
    private String cpf;
    private String senha;
    private int numeroDaConta;
    private double saldo;
    //private static LocalDateTime atual;
    //private static Calendar aux;
    private String dataCriacao;
    private String status;
    private static String user = "root", password = "123";


    public Conta() {
    
    }

    public Conta(String titular, String cpf, String senha, int numeroDaConta, double saldo, String dataCriacao, String status) {
        this.titular = titular;
        this.cpf = cpf;
        this.senha = senha;
        this.numeroDaConta = numeroDaConta;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.status = status;
    }

    public String getTitular() {
        return this.titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getNumeroDaConta() {
        return this.numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static  void CadastrarConta(){
        Conta conta = new Conta();

        conta.setTitular(JOptionPane.showInputDialog(null, "Insira o nome do titular da conta:", "Obtendo dados", 1));

        conta.setCpf(JOptionPane.showInputDialog(null, "Insira o cpf do titular da conta:", "Obtendo dados", 1));

        do{
            conta.setSenha(JOptionPane.showInputDialog(null, "Insira uma senha de 6 digitos para o titular da conta:", "Obtendo dados", 1));
            if(conta.getSenha().length() != 6){
                JOptionPane.showMessageDialog(null, "Tamanho da senha incorreto, por favor, insira uma senha de 6 digitos!", "Senha inválida", 1);
            }
        }while (conta.getSenha().length() != 6);

        Random random = new Random();
        int num;
        Conta conta_aux = new Conta();

        do{
           num = random.nextInt(100000, 999999);
           //Integer.toUnsignedLong(num);
           try {
                conta_aux = LerContaDoBanco(num);
            } catch (SQLException e) {
                e.printStackTrace();
            } 
        }while(conta_aux == null);

        conta.setNumeroDaConta(num);
        conta.setSaldo(0);
        
        LocalDateTime dataCriacao = LocalDateTime.now();
        conta.setDataCriacao(dataCriacao.toString());
        conta.setStatus("Em atividade");

        try {
            InserirNoBanco(conta);
            JOptionPane.showMessageDialog(null, "Dados salvos no banco de dados! O numero da sua conta é: " + num, "Sucesso!", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void ExcluirConta(){
        int num_conta = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o numero da conta que deseja encerrar: ", "Obtendo Numero da conta", 1));
        
        try {
            DeletarContaDoBanco(num_conta);
            JOptionPane.showMessageDialog(null, "Conta excluida com sucesso!", "Sucesso!", 1);
    
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void EncerrarConta(){
        int num_conta = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o numero da conta que deseja encerrar: ", "Obtendo Numero da conta", 1));
        
        try {
            Conta conta = LerContaDoBanco(num_conta);
            if(conta.getSaldo() != 0){
                JOptionPane.showMessageDialog(null, "Para encerrar a conta, o saldo precisa estar zerado. Por favor, transfira o saldo restante ou saque antes de tentar encerrar a conta", "Saldo inválido", 1);
            }
            else{
                String senha = JOptionPane.showInputDialog(null, "Insira a senha da conta para encerrar-la", "Obtendo senha da conta", 1);
                ModificarStatus(num_conta, senha);
                JOptionPane.showMessageDialog(null, "Conta encerrada com sucesso!", "Sucesso!", 1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void EfetuarTransferencia(){

    }

    public static void EfetuarSaque(){

    }

    public static void EfetuarDeposito(){

    }

    public static void ConsultarSaldo(){

    }

    public static void ControleDeContas(){
        if(VerificaCredenciaisAdministrativas()){
            LerTudoDoBanco();
        }
    }

    public static boolean VerificaCredenciaisAdministrativas(){
        String user_teste = JOptionPane.showInputDialog(null, "Insira o usuario para acessar a função", "Área restrita", 1);

        if(!user_teste.equals(user)){
            JOptionPane.showMessageDialog(null, "Usuário incorreto", "Falha de autenticação", 1);
            return false;
        }
        else{
            String password_teste = JOptionPane.showInputDialog(null, "Insira a senha para acessar a função", "Área restrita", 1);

            if(!password_teste.equals(password)){
                JOptionPane.showMessageDialog(null, "Senha incorreta", "Falha de autenticação", 1);
                return false;
            }
            else{
                return true;
            }
        }
    }
}