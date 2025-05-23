package banco.service;

import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;
import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;
import banco.model.cliente.Cliente;
import banco.utils.Utils;

public class BancoService {

    // => Cria cliente Pessoa Fisica
    public PessoaFisica criarClientePessoaFisica(String name, String cpf, String city, String state){
        Utils.validaCampoObjeto(name,"NOME");
        Utils.validaCampoObjeto(cpf,"CPF");
        Utils.validaCampoObjeto(city,"CIDADE");
        Utils.validaCampoObjeto(state,"ESTADO");
        var clientePessoaFisica = new PessoaFisica(name, cpf, city, state);
        return clientePessoaFisica;
    }

    // => Cria cliente Pessoa Juridica
    public PessoaJuridica criarClientePessoaJuridica(String cnpj, String razaoSocial, String city, String state){
        Utils.validaCampoObjeto(cnpj, "CNPJ");
        Utils.validaCampoObjeto(razaoSocial,"Razao Social");
        Utils.validaCampoObjeto(city, "CIDADE");
        Utils.validaCampoObjeto(state, "ESTADO");
        var clientePessoaJuridica = new PessoaJuridica(cnpj, razaoSocial, city, state);
        return clientePessoaJuridica;
    }

    // => Solicita Lista dados do Cliente/Conta se nao nulo
    public String listDados(Cliente cliente, Conta conta){
        Utils.validaCampoObjeto(cliente,"Cliente");
        Utils.validaCampoObjeto(conta,"Conta");
        var cliente_var = cliente.listDados();
        var conta_var = conta.listDados();
        return cliente_var + "\n" + conta_var;
    }

    // => Requisita saque se conta nao nula e valor valido
    public boolean withdraw(Conta conta, double value) {
        Utils.validaCampoObjeto(conta,"Conta");
        Utils.validaWihdraw(value);
        return conta.withdraw(value);
    }

    // => Aplica rendimento se ContaPoupanca
    public double applicationYield(Conta conta, double value) {
        Utils.validaCampoObjeto(conta,"Conta");
        Utils.validaContaPoupanca(conta);
        Utils.validaValorAplicacaoRendimentoContaPoupanca(value);

        ContaPoupanca poupanca = (ContaPoupanca) conta; // => Casting do obj conta da classe Conta para ContaPoupanca
        var application = poupanca.applicationYield(value);
        return application;
    }

    // => Aplica juros se ContaCorrente
    public double applicationJuros(Conta conta){
        Utils.validaContaCorrente(conta);

        ContaCorrente corrente = (ContaCorrente) conta; // => Casting do obj conta da classe Conta para ContaCorrente
        var application = corrente.applicationJuros();
        return application;
    }

    // => Deposita na ContaPoupanca
    public Conta depositContaPoupanca(Conta conta, double value){
        Utils.validaContaPoupanca(conta);
        Utils.validaDeposit(value);
        conta.deposit(value);
        return conta;
    }

    // => Deposita na ContaCorrente
    public Conta depositContaCorrente(Conta conta, double value){
        Utils.validaContaCorrente(conta);
        Utils.validaDeposit(value);
        conta.deposit(value);
        return conta;
    }
}
