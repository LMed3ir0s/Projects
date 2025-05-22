package banco.service;

import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;
import banco.model.cliente.Cliente;

import java.util.Objects;

public class BancoService {

    // => Solicita Lista dados do Cliente/Conta se nao nulo
    public String listDados(Cliente cliente, Conta conta){
        Objects.requireNonNull(cliente,"Cliente não pode ser nulo.");
        Objects.requireNonNull(conta,"Conta não pode ser nula.");
        var cliente_var = cliente.listDados();
        var conta_var = conta.listDados();
        return cliente_var + "\n" + conta_var;
    }

    // => Requisita saque se conta nao nula e valor valido
    public boolean withdraw(Conta conta, double value) {
        Objects.requireNonNull(conta, "Conta não pode ser nula.");
        if (value <= 0) {
            throw new IllegalArgumentException("Valor para saque deve ser maior que zero.");
        }
        return conta.withdraw(value);
    }

    // => Aplica rendimento se ContaPoupanca
    public double applicationYield(Conta conta, double value) {
        Objects.requireNonNull(conta, "Conta não pode ser nula.");
        if (!(conta instanceof ContaPoupanca)) {
            throw new IllegalArgumentException("Aplicação de rendimento disponível apenas para Conta Poupança.");
        }
        if (value <= 0) {
            throw new IllegalArgumentException("Valor para aplicação deve ser maior que zero.");
        }

        ContaPoupanca poupanca = (ContaPoupanca) conta; // => Casting do obj conta da classe Conta para ContaPoupanca
        var application = poupanca.applicationYield(value);
        return application;
    }

    // => Aplica juros se ContaCorrente
    public double applicationJuros(Conta conta){
        if (!(conta instanceof ContaCorrente)){
            throw new IllegalArgumentException("Aplicação de juros disponível apenas para Conta Corrente.");
        }

        ContaCorrente corrente = (ContaCorrente) conta; // => Casting do obj conta da classe Conta para ContaCorrente
        var application = corrente.applicationJuros();
        return application;
    }

}
