package banco.model.conta;

import banco.model.cliente.Cliente;
import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;

import java.text.DecimalFormat;


public class Conta{

    protected int number;
    protected Cliente titular;
    protected double balance;

    private static int contador;

    // => Construtor titular
    public Conta(Cliente titular){
        this.titular = titular;
    }

    // => Getters
    public Cliente getTitular(){
        return titular;
    }

    public double getBalance(){
        return balance;
    }

    public String getFormattedBalance(){
        return DecimalFormat.getCurrencyInstance().format(balance);
    }

    // => Setters
    public void setTitular(Cliente titular){
        this.titular = titular;
    }

    // => Métodos

    public void deposit(double valor){
        balance += valor;
    }

    public boolean withdraw(double value){
        if (balance >= value) {
            balance -= value;
            return true;
        } else {
            return false;
        }
    }

    public String listDados(){
        String name;
        // => Verificando se PF ou PJ
        if (titular instanceof PessoaFisica){
            name = ((PessoaFisica)titular).getName(); // => casting
        } else {
            name = ((PessoaJuridica)titular).getRazaoSocial(); // => casting
        }
        String dados = "NUMERO: " + number + "\n" +
                "CORRENTISTA: " + name + "\n" +
                "SALDO: " + getFormattedBalance();
        return dados;
    }

}



