import javax.swing.JOptionPane;

public class App extends Senhas{
    public static void main(String[] args) throws Exception {
        int op = -1;
            do{
                try{
                    op = Integer.parseInt(JOptionPane.showInputDialog("1. Gerar Senha\n2. Efetuar Atendimento\n3. Controle de Senha\n\nFUNCOES RESTRITAS\n\n4. Exclusao de Senha\n5. Controle de Contas\n6. Excluir Conta\n\n7. Sair"));
                    switch(op) {
                        case 1:
                            GerarSenha();
                        break;
        
                        case 2:
                            EfetuarAtendimento();
                        break;
        
                        case 3:
                            ControleDeSenha();
                        break;
        
                        case 4:
                            ExcluirSenha();
                        break;
        
                        case 5:
                            ControleDeContas();
                        break;

                        case 6:
                            ExcluirConta();
                        break;
        
                        case 7:
                            op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação de saída", 1, 1);
                            if(op == JOptionPane.YES_OPTION){
                                JOptionPane.showMessageDialog(null, "Saindo...", "Saída confirmada", 1);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Retornando ao menu!", "Saída cancelada", 1);
                            }
                        break;
                        
                        default:
                            JOptionPane.showMessageDialog(null, "Insira uma opcao válida!", "Opcao inválida", 1);
                            op = -1;
                        break;
                    }
                } catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(null, "Para sair, utilize a opção 6 do menu!", e.getMessage(), 1);
                }  

            }while(op != JOptionPane.YES_OPTION);
    }
}