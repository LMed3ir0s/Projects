package main.java.br.com.sistemabancario.utils;

import main.java.br.com.sistemabancario.model.conta.Conta;
import main.java.br.com.sistemabancario.model.conta.ContaCorrente;
import main.java.br.com.sistemabancario.model.conta.ContaPoupanca;

public class ContaUtils {
    // => Valida ContaPoupanca
    public static Object validaContaPoupanca(Conta conta){
        if (!(conta instanceof ContaPoupanca)){
            throw new IllegalArgumentException("Aplicação de rendimento disponível apenas para Conta Poupança.");
        }
        return conta;
    }

    // => Valida ContaCorrente
    public static Object validaContaCorrente(Conta conta){
        if (!(conta instanceof ContaCorrente)){
            throw new IllegalArgumentException("Aplicação de juros disponível apenas para Conta Corrente.");
        }
        return conta;
    }

    // => Valida saque
    public static double validaWihdraw(double value){
        if (value <= 0){
            throw new IllegalArgumentException("Valor para saque deve ser maior que zero.");
        }
        return value;
    }

    // => Valida deposito
    public static double validaDeposit(double value){
        if (value <= 0){
            throw new IllegalArgumentException("Valor para deposito deve ser maior que zero.");
        }
        return value;
    }

    // => Valida Aplicacao Rendimento ContaPoupanca
    public static double validaAplicacaoRendimentoContaPoupanca(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Valor para aplicação deve ser maior que zero.");
        }
        return value;
    }
}

