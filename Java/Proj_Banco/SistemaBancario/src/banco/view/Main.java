package banco.view;

import javax.swing.JOptionPane;

import banco.controller.BancoController;
import banco.model.cliente.Cliente;
import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;
import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;
import banco.utils.BancoUtils;


public class Main {
    public static void main(String[] args){

    String tipoCliente = BancoUtils.solicitaInput("Escolha o tipo de cliente:\n" +
            "F - Pessoa Física\n" +
            "J- Pessoa Jurídica");

    if (tipoCliente.equals("F")){
        var name = BancoUtils.solicitaInput("Nome do Cliente: ");
        BancoController.validaNome(name);
        var cpf = BancoUtils.solicitaInput("CPF do Cliente: ");
        BancoController.validaCPF(cpf);
        var city = BancoUtils.solicitaInput("Cidade do Cliente: ");
        BancoController.validaCity(city);
        var state = BancoUtils.solicitaInput("Estado do Cliente: ");
        BancoController.validaState(state);
        BancoController.criaClientePessoaFisica(name,cpf,city,state);
    } else if (tipoCliente.equals("J")) {
        var razaoSocial = BancoUtils.solicitaInput("Razão Social: ");
        BancoController.validaRazaoSocial(razaoSocial);
        var cnpj = BancoUtils.solicitaInput("CNPJ do Cliente: ");
        BancoController.validaCNPJ(cnpj);
        var city = BancoUtils.solicitaInput("Cidade do Cliente: ");
        BancoController.validaCity(city);
        var state = BancoUtils.solicitaInput("Estado do Cliente: ");
        BancoController.validaState(state);
        BancoController.criaClientePessoaJuridica(razaoSocial,cnpj,city,state);
    }else{
        BancoUtils.messageView("OPÇÃO INVÁLIDA! Encerrando o programa...");
        return;
    }

    Conta conta;

    String tipoConta = JOptionPane.showInputDialog(null,"Tipo de conta:\n" +
            "C - Conta Corrente\n" +
            "P - Conta Poupança");

    if (tipoConta.equals("P")){
        conta = new ContaPoupanca(cliente);
    } else {
        conta = new ContaCorrente(cliente);
    }

    JOptionPane.showMessageDialog(null,"DADOS DA CONTA\n\n" + conta.listDados());

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
                    ret = JOptionPane.showInputDialog(null, "Valor do depósito: ");

                    // Verifica se o valor é nulo ou vazio
                    if (ret == null || ret.trim().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Operação cancelada ou valor vazio!");
                        break;
                    }

                    try{
                        double value = Double.parseDouble(ret);
                        conta.deposit(value);
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
                        if (conta.withdraw(value)){
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
