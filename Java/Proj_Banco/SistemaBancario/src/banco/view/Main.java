package banco.view;

import javax.swing.JOptionPane;
import banco.model.Cliente;
import banco.model.Conta;


public class Main {
    public static void main(String[] args){

    Cliente cliente_01 = new Cliente("Lucas_01");
    Cliente cliente_02 = new Cliente("Lucas_02", " Cidade_02", " Distrito-Federal");
    Cliente cliente_03 = new Cliente("", " Cidade_03", "Distrito-Federal");
    Cliente cliente_04 = new Cliente("Lucas_04", "", "Distrito-Federal");
    Cliente cliente_05 = new Cliente("Lucas_05", " Cidade_05", "");

    JOptionPane.showMessageDialog(null,cliente_01.listDados());
    JOptionPane.showMessageDialog(null,cliente_02.listDados());
    JOptionPane.showMessageDialog(null,cliente_03.listDados());
    JOptionPane.showMessageDialog(null,cliente_04.listDados());
    JOptionPane.showMessageDialog(null,cliente_05.listDados());
    JOptionPane.showMessageDialog(null,"Possuimos " + Cliente.qtdClientes() + " cliente(s) cadastrados");

    Conta conta_01 = new Conta(cliente_01);
//    Conta conta_02 = new Conta(cliente_02);
//    Conta conta_03 = new Conta(cliente_03);
//    Conta conta_04 = new Conta(cliente_04);
//    Conta conta_05 = new Conta(cliente_05);

    conta_01.deposit(1000.0);

    int option = 0; // => Variável input user (p/ opção do menu)
    String ret;  // => Variável temporária para armazenar o input user (valor)
    do {
        String message = " SALDO EM CONTA: R$" + conta_01.getFormattedBalance() + "\n\n" +
                "OPÇÕES:\n " +
                "1 - Depositar valor\n" +
                "2 - Sacar valor\n" +
                "3 - Finalizar";

        try {
            option = Integer.parseInt(JOptionPane.showInputDialog(null, message));
            switch (option) {
                // => Depositar
                case 1:
                    ret = JOptionPane.showInputDialog(null, "Valor do depósito: ");

                    // Verifica se o valor é nulo ou vazio
                    if (ret == null || ret.trim().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Operação cancelada ou valor vazio!");
                        break;
                    }

                    try{
                        double value = Double.parseDouble(ret);
                        conta_01.deposit(value);
                        JOptionPane.showMessageDialog(null, "Depósito realizado!");
                        break;
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,"Valor inválido!");
                        break;
                    }


                // => Sacar
                case 2:
                    ret = JOptionPane.showInputDialog("Valor do saque: ");

                    // Verifica se o valor é nulo ou vazio
                    if (ret == null || ret.trim().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Operação cancelada ou valor vazio!");
                        break;
                    }

                    try{
                        double value = Double.parseDouble(ret);
                        if (conta_01.withdraw(value)){
                            JOptionPane.showMessageDialog(null,"Saque realizado!");
                        } else {
                            JOptionPane.showMessageDialog(null, "FALHA NO SAQUE!");
                        }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null,"Valor inválido!");
                        }
                        break;

                        case 3:
                            JOptionPane.showMessageDialog(null,"Ate logo!\n Encerrando...");
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
                            break;
                    }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "OPÇÃO INVÁLIDA! Informe um número de 1 a 3.");
        }
    } while ((option == 1) || (option == 2));
    }
}
