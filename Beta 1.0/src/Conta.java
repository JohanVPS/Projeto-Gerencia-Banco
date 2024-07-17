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
        Conta conta_origem = VerificaCredenciaisClientes();
        if(conta_origem != null){
            double valor;
            Conta conta_destino = new Conta();
            int num_conta_destino = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da conta de destino da transferência:", "Obtendo dados!", 1));

            try {
                conta_destino = LerContaDoBanco(num_conta_destino);
            } catch (SQLException e) {
                
            }

            if(conta_destino != null){
                do{
                    valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o valor que deseja transferir:", "Obtendo dados", 1));
                    if(valor <= 0){
                        JOptionPane.showMessageDialog(null, "Não é possível inserir o valor digitado! Insira um valor maior que 0", "Valor inválido", 1);
                    }
                }while(valor <= 0);
    
                conta_destino.setSaldo(conta_destino.getSaldo() + valor);
                conta_origem.setSaldo(conta_origem.getSaldo() - valor);
                try {
                    ModificarSaldoNoBanco(conta_destino.getNumeroDaConta(), conta_destino.getSaldo());
                    ModificarSaldoNoBanco(conta_origem.getNumeroDaConta(), conta_origem.getSaldo());
                    JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso!", 1);
                } catch (SQLException e) {
    
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Essa conta de destino não existe!", "Conta inválida", 1);
            }
        }
    }

    public static void EfetuarSaque(){
        Conta conta = VerificaCredenciaisClientes();
        if(conta != null){
            double valor;
            do{
                valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o valor que deseja sacar:", "Obtendo dados!", 1));
                if(valor <= 0 || valor > conta.getSaldo()){
                    JOptionPane.showMessageDialog(null, "Não é possível inserir o valor digitado! Insira um valor maior que 0 e menor que o saldo atual.\nSaldo atual: R$" + conta.getSaldo(), "Valor inválido", 1);
                }
            }while(valor <= 0 || valor > conta.getSaldo());

            conta.setSaldo(conta.getSaldo() - valor);
            try {
                ModificarSaldoNoBanco(conta.getNumeroDaConta(), conta.getSaldo());
                JOptionPane.showMessageDialog(null, "Saque realizada com sucesso!", "Sucesso!", 1);
            } catch (SQLException e) {

            }
        }
    }

    public static void EfetuarDeposito(){
        Conta conta = VerificaCredenciaisClientes();
        if(conta != null){
            double valor;
            do{
                valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o valor que deseja depositar:", "Obtendo dados!", 1));
                if(valor <= 0){
                    JOptionPane.showMessageDialog(null, "Não é possível inserir o valor digitado! Insira um valor maior que 0", "Valor inválido", 1);
                }
            }while(valor <= 0);

            conta.setSaldo(conta.getSaldo() + valor);
            try {
                ModificarSaldoNoBanco(conta.getNumeroDaConta(), conta.getSaldo());
                JOptionPane.showMessageDialog(null, "Depósito realizada com sucesso!", "Sucesso!", 1);
            } catch (SQLException e) {

            }
        }
    }

    public static void ConsultarSaldo(){
        Conta conta = VerificaCredenciaisClientes();
        if(conta != null){
            JOptionPane.showMessageDialog(null, "Seu saldo atual é de R$" + conta.getSaldo(), "Saldo", 1);
        }
    }

    public static void ControleDeContas(){
        if(VerificaCredenciaisAdministrativas()){
            LerTudoDoBanco();
        }
    }

    public static Conta VerificaCredenciaisClientes(){
        int num_conta;
        String senha;
        Conta conta = new Conta();
      
        num_conta = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número da conta:", "Obtendo dados!", 1));
        try {
            conta = LerContaDoBanco(num_conta);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", 1);
        }
        if(conta == null){
            JOptionPane.showMessageDialog(null, "Essa conta não existe. Por favor, verifique se escreveu corretamente e tente novamente!", "Conta não encontrada", 1);
            return conta;
        }
        else{
            do{
                senha = JOptionPane.showInputDialog(null, "Insira a senha da conta:", "Obtendo dados!", 1);
                if (senha.length() != 6) JOptionPane.showMessageDialog(null, "Quantidade de dígitos inválida, a senha deve conter 6 dígitos", "Senha inválida", 1);
            }while(senha.length() != 6);

            if(senha.compareTo(conta.getSenha()) != 0){
                JOptionPane.showMessageDialog(null, "Senha incorreta", "Credenciais inválidas", 1);
                return conta;
            }
            else{
                return conta;
            }
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