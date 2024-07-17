import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Senhas extends Conta{
    private static ArrayList<String> listaDeSenhas = new ArrayList<String>();
    private static int contAbrirConta = 0, contEncerrarConta = 0, contTransferencia = 0, contSaque = 0, contDeposito = 0, contSaldo = 0;

    public static void GerarSenha(){
        String str_senha = "";
        int prioridade, op;

        do{
            prioridade = Integer.parseInt(JOptionPane.showInputDialog(null, "É prioridade?\n\n1. SIM\n2. NÃO", "Prioridade?", 1));
            
            if(prioridade != 1 && prioridade != 2) JOptionPane.showMessageDialog(null, "Insira uma opcao válida!", "Opção inválida", 1);
            
        }while (prioridade != 1 && prioridade != 2);

        if(prioridade == 1) str_senha += 'P';
        else str_senha += 'C';
        
        do{
            op = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o tipo de serviço que deseja:\n1. Abrir conta\n2. Encerrar Conta\n3. Efetuar Transferencia\n4. Efetuar Saque\n5. Efetuar Depósito\n6. Consultar Saldo", "Tipo de serviço", 1));
    
            switch(op){
                case 1:
                    contAbrirConta++;
                    str_senha += "AC" + contAbrirConta;
                break;
    
                case 2:
                    contEncerrarConta++;
                    str_senha += "EC" + contEncerrarConta;
                break;
                
                case 3:
                    contTransferencia++;
                    str_senha += "ET" + contTransferencia;
                break;
    
                case 4:
                    contSaque++;
                    str_senha += "ES" + contSaque;
                break;
    
                case 5:
                    contDeposito++;
                    str_senha += "ED" + contDeposito;
                break;

                case 6:
                    contSaldo++;
                    str_senha += "CS" + contSaldo;
                break;

                default:
                    JOptionPane.showMessageDialog(null, "Insira um serviço válido!", "Opção inválida", 1);
                break;
            }
        }while(op < 1 || op > 6);

        ArrayList<String> auxPrioridade = new ArrayList<String>();
        ArrayList<String> auxComum = new ArrayList<String>();

        if(listaDeSenhas.size() == 0 || str_senha.startsWith("C")){
            listaDeSenhas.add(str_senha);
        }
        else{
            String auxiliar;
            
            for(int i = 0; i <listaDeSenhas.size(); i++){
                auxiliar = listaDeSenhas.get(i);
                if(auxiliar.startsWith("P")) auxPrioridade.add(auxiliar);
                else auxComum.add(auxiliar);
            }
            auxPrioridade.add(str_senha);

            listaDeSenhas.clear();
            listaDeSenhas.addAll(auxPrioridade);
            listaDeSenhas.addAll(auxComum);
        }
        JOptionPane.showMessageDialog(null, "Senha gerada com sucesso!\nSenha gerada: " + str_senha, "Sucesso!", 1);
    }

    public static void EfetuarAtendimento(){

        if(listaDeSenhas.size() != 0){
            String auxiliar = listaDeSenhas.get(0);
            if(auxiliar.startsWith("AC", 1)){
                CadastrarConta();
            }
            else if(auxiliar.startsWith("EC", 1)){
                EncerrarConta();
            }
            else if(auxiliar.startsWith("ET", 1)){
                EfetuarTransferencia();
            }
            else if(auxiliar.startsWith("ES", 1)){
                EfetuarSaque();
            }
            else if(auxiliar.startsWith("ED", 1)){
                EfetuarDeposito();
            }
            else if(auxiliar.startsWith("CS", 1)){
                ConsultarSaldo();
            }
    
            listaDeSenhas.remove(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "Não há senhas para atender", "Atendimento", 1);
        }
    }

    public static void ControleDeSenha(){
        if(listaDeSenhas.size() != 0){
            String str = "Lista de senhas na fila:\n\n";
            int cont = 0;
            for(String senha : listaDeSenhas){
                cont++;
                str+= cont + ". " + senha +"\n";
            }
            JOptionPane.showMessageDialog(null, str, "Lista de senhas", 1);
        }
        else{
            JOptionPane.showMessageDialog(null, "Não há senhas na fila", "Lista de senhas", 1);
        }
    }

    public static void ExcluirSenha(){
        if(listaDeSenhas.size() != 0){
            if(VerificaCredenciaisAdministrativas()){
                String str = "Qual senha deseja excluir?\n\nLista de senhas na fila:\n\n";
                int cont = 0;

                for(String senha : listaDeSenhas){
                    cont++;
                    str+= cont + ". " + senha +"\n";
                }

                int pos;
                do{
                    pos = Integer.parseInt(JOptionPane.showInputDialog(null, str, "Lista de senhas", 1));
                    if (pos < 1 || pos > cont) JOptionPane.showMessageDialog(null, "Insira uma opção válida");
                }while (pos < 1 || pos > cont);

                listaDeSenhas.remove(pos-1);
                JOptionPane.showMessageDialog(null, "Senha excluída com sucesso", "Exclusão bem sucedida!", 1);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Não há senhas na fila", "Lista de senhas", 1);
        }	
    }
}