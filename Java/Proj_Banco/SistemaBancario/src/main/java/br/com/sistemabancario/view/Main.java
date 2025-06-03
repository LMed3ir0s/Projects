package main.java.br.com.sistemabancario.view;

import javax.swing.JOptionPane;

import main.java.br.com.sistemabancario.controller.BancoController;
import main.java.br.com.sistemabancario.model.agencia.Agencia;
import main.java.br.com.sistemabancario.model.cliente.Cliente;
import main.java.br.com.sistemabancario.model.conta.Conta;
import main.java.br.com.sistemabancario.utils.BancoUtils;


public class Main {
    public static void main(String[] args) {

        //Cria Agencia
        var number = Integer.parseInt(BancoUtils.solicitaInput("Número da Agência: "));
        var name = BancoUtils.solicitaInput("Nome da Agência: ");
        Agencia agencia = BancoController.newAgencia(number, name);

        String mainMenu = "";

        do {
            mainMenu = BancoUtils.solicitaInput(
                    "Cadastro de Contas para a agência " +
                            agencia.getNumber() +
                            " - " +
                            agencia.getName() +
                            "\n" +
                            "\nOPÇÕES:\n" +
                            "1 - Incluir cliente e conta\n" +
                            "2 - Listar contas cadastradas\n" +
                            "3 - Sair do sistema"
            );

            switch (mainMenu) {
                case "1":
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
                    agencia.addAccount(conta);

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

                                    if (ret == null || ret.trim().isEmpty()) {
                                        BancoUtils.messageView("Operação cancelada ou valor vazio!");
                                        break;
                                    }
                                    try {
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

                                    if (ret == null || ret.trim().isEmpty()) {
                                        BancoUtils.messageView("Operação cancelada ou valor vazio!");
                                        break;
                                    }
                                    try {
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
                                    BancoUtils.messageView("\n Encerrando...");
                                    break;

                                default:
                                    BancoUtils.messageView("Opção inválida! Tente novamente.");
                                    break;
                            }
                        } catch (NumberFormatException ex) {
                            BancoUtils.messageView("OPÇÃO INVÁLIDA! Informe um número de 1 a 3.");
                        }
                    } while ((option == 1) || (option == 2)); break;


                case "2":
                    if (agencia.getAccounts().size() == 0) {
                        BancoUtils.messageView("NÃO HÁ CONTAS CADASTRADAS NO MOMENTO. ");
                    }
                    BancoUtils.messageView(
                        "A Agência " +
                        agencia.getNumber() + " - " +
                        agencia.getName() +
                        " possui " +
                        agencia.getAccounts().size() +
                        "conta(s). \n\nVeja quais são nas próximas telas"
                    );

                for (Conta umaConta : agencia.getAccounts()) {
                    BancoUtils.messageView(umaConta.listDados());
                } break;
            }
        } while ((mainMenu.equals("1") || (mainMenu.equals("2"))));
        BancoUtils.messageView("Encerrando Sistema...");
    }
}

