package banco.view;

import javax.swing.JOptionPane;

import banco.controller.BancoController;
import banco.model.cliente.Cliente;
import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;
import banco.utils.BancoUtils;

import static banco.controller.BancoController.newClienteType;


public class Main {
    public static void main(String[] args){

    // => Cria Cliente
    String newCliente = BancoUtils.solicitaInput("Escolha o tipo de cliente:\n" +
            "F - Pessoa Física\n" +
            "J- Pessoa Jurídica");

    Cliente cliente = BancoController.newClienteType(newCliente);

    // => Escolhe Tipo de Conta
    String tipoConta = BancoUtils.solicitaInput("Tipo de conta:\n" +
            "C - Conta Corrente\n" +
            "P - Conta Poupança");

    Conta conta = BancoController.newContaType(tipoConta, cliente);

//    JOptionPane.showMessageDialog(null,"DADOS DA CONTA\n\n" + conta.listDados());

    int option = 0; // => Variável input user (p/ opção do menu)
    String ret;  // => Variável temporária para armazenar o input user (valor)
    do {
        String message = " SALDO EM CONTA: R$" + conta.getFormattedBalance() + "\n\n" +
                "OPÇÕES:\n " +
                "1 - Depositar valor\n" +
                "2 - Sacar valor\n" +
                "3 - Finalizar";

        try {
            option = Integer.parseInt(JOptionPane.showInputDialog(null, message));
            switch (option) {
                // => Depositar
                case 1:
                    ret = BancoUtils.solicitaInput("Valor do depósito: ");

                    // Verifica se o valor é nulo ou vazio
                    if (ret == null || ret.trim().isEmpty()){
                        BancoUtils.messageView("Operação cancelada ou valor vazio!");
                        break;
                    }
                    try{
                        double value = Double.parseDouble(ret);
                        conta.deposit(value);
                        BancoUtils.messageView("Depósito realizado!");
                        break;
                    } catch (NumberFormatException ex) {
                        BancoUtils.messageView("Valor inválido!");
                        break;
                    }


                // => Sacar
                case 2:
                    ret = BancoUtils.solicitaInput("Valor do saque: ");

                    // Verifica se o valor é nulo ou vazio
                    if (ret == null || ret.trim().isEmpty()){
                        BancoUtils.messageView("Operação cancelada ou valor vazio!");
                        break;
                    }
                    try{
                        double value = Double.parseDouble(ret);
                        var saque = conta.withdraw(value);
                        if (!saque) {
                            BancoUtils.messageView("FALHA NO SAQUE!\n" +
                                    "Cancelando Operação...");
                            return;
                        }
                        BancoUtils.messageView("Saque realizado!");
                        } catch (NumberFormatException ex) {
                            BancoUtils.messageView("Valor inválido!");
                        }
                        break;

                        case 3:
                            BancoUtils.messageView("Ate logo!\n Encerrando...");
                            break;

                        default:
                            BancoUtils.messageView("Opção inválida! Tente novamente.");
                            break;
                    }
        } catch (NumberFormatException ex) {
            BancoUtils.messageView("OPÇÃO INVÁLIDA! Informe um número de 1 a 3.");
        }
    } while ((option == 1) || (option == 2));
}
}
