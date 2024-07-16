import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Senhas extends Conta{
    private static ArrayList<String> listaDeSenhas = new ArrayList<String>();
    private static int contAbrirConta = 0, contEncerrarConta = 0, contTransferencia = 0, contSaque = 0, contDeposito = 0;

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
            op = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o tipo de serviço que deseja:\n1. Abrir conta\n2. Encerrar Conta\n3. Efetuar Transferencia\n4. Efetuar Saque\n5. Efetuar Depósito", "Tipo de serviço", 1));
    
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
    
                default:
                    JOptionPane.showMessageDialog(null, "Insira um serviço válido!", "Opção inválida", 1);
                break;
            }
        }while(op < 1 && op > 5);

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
    }

    public static void EfetuarAtendimento(){

        if(listaDeSenhas.size() != 0){
            String auxiliar = listaDeSenhas.get(0);
            if(auxiliar.contentEquals("AC")){
    
            }
            else if(auxiliar.contentEquals("EC")){
    
            }
            else if(auxiliar.contentEquals("ET")){
    
            }
            else if(auxiliar.contentEquals("ES")){
    
            }
            else if(auxiliar.contentEquals("ED")){
    
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
            for(String senha : listaDeSenhas){
                str+=senha +"\n";
            }
            JOptionPane.showMessageDialog(null, str, "Lista de senhas", 1);
        }
        else{
            JOptionPane.showMessageDialog(null, "Não há senhas para listar", "Lista de senhas", 1);
        }
    }

    public static void ExcluirSenha(){
        	
    }

}
