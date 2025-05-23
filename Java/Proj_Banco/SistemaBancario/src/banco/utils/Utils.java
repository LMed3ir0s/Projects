package banco.utils;

import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;

import java.util.Objects;

public class Utils {


    // => Valida Campo
    public static Object validaCampoObjeto(Object object, String nomeCampo) {
        var campoObjeto = Objects.requireNonNull(object, nomeCampo + " não pode ser Nulo");
        return campoObjeto;
    }

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

    // => Valida Valor Aplicacao Rendimento ContaPoupanca
    public static double validaValorAplicacaoRendimentoContaPoupanca(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Valor para aplicação deve ser maior que zero.");
        }
        return value;
    }
}