package banco.service;

import banco.model.agencia.Agencia;
import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;
import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;
import banco.model.cliente.Cliente;
import banco.utils.BancoUtils;
import banco.utils.ContaUtils;

public class BancoService {

    // => Metodo cria Agencia
    public  static Agencia criarAgencia(int number, String name){
        BancoUtils.validaCampoObjeto(number,"NUMERO AGENCIA");
        BancoUtils.validaCampoObjeto(name,"NOME AGENCIA");
        var agencia = new Agencia(number,name);
        return agencia;
    }

    // => Cria cliente Pessoa Fisica
    public static PessoaFisica criarClientePessoaFisica(String name, String cpf, String city, String state){
        BancoUtils.validaCampoObjeto(name,"NOME");
        BancoUtils.validaCampoObjeto(cpf,"CPF");
        BancoUtils.validaCampoObjeto(city,"CIDADE");
        BancoUtils.validaCampoObjeto(state,"ESTADO");
        var clientePessoaFisica = new PessoaFisica(name, cpf, city, state);
        return clientePessoaFisica;
    }

    // => Cria cliente Pessoa Juridica
    public static PessoaJuridica criarClientePessoaJuridica(String cnpj, String razaoSocial, String city, String state){
        BancoUtils.validaCampoObjeto(cnpj, "CNPJ");
        BancoUtils.validaCampoObjeto(razaoSocial,"Razao Social");
        BancoUtils.validaCampoObjeto(city, "CIDADE");
        BancoUtils.validaCampoObjeto(state, "ESTADO");
        var clientePessoaJuridica = new PessoaJuridica(cnpj, razaoSocial, city, state);
        return clientePessoaJuridica;
    }

    // => Solicita Lista dados Cliente/Conta
    public String listDados(Cliente cliente, Conta conta){
        BancoUtils.validaCampoObjeto(cliente,"Cliente");
        BancoUtils.validaCampoObjeto(conta,"Conta");
        var cliente_var = cliente.listDados();
        var conta_var = conta.listDados();
        return cliente_var + "\n" + conta_var;
    }

    // => Requisita saque
    public boolean withdraw(Conta conta, double value) {
        BancoUtils.validaCampoObjeto(conta,"Conta");
        ContaUtils.validaWihdraw(value);
        return conta.withdraw(value);
    }

    // => Aplica rendimento ContaPoupanca
    public double applicationYield(Conta conta, double value) {
        BancoUtils.validaCampoObjeto(conta,"Conta");
        ContaUtils.validaContaPoupanca(conta);
        ContaUtils.validaAplicacaoRendimentoContaPoupanca(value);

        ContaPoupanca poupanca = (ContaPoupanca) conta; // => Casting do obj conta da classe Conta para ContaPoupanca
        var application = poupanca.applicationYield(value);
        return application;
    }

    // => Aplica juros ContaCorrente
    public double applicationJuros(Conta conta){
        ContaUtils.validaContaCorrente(conta);

        ContaCorrente corrente = (ContaCorrente) conta; // => Casting do obj conta da classe Conta para ContaCorrente
        var application = corrente.applicationJuros();
        return application;
    }

    // => Deposita ContaPoupanca
    public Conta depositContaPoupanca(Conta conta, double value){
        ContaUtils.validaContaPoupanca(conta);
        ContaUtils.validaDeposit(value);
        conta.deposit(value);
        return conta;
    }

    // => Deposita ContaCorrente
    public Conta depositContaCorrente(Conta conta, double value){
        ContaUtils.validaContaCorrente(conta);
        ContaUtils.validaDeposit(value);
        conta.deposit(value);
        return conta;
    }
}
