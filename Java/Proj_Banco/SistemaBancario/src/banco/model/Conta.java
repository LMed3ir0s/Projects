package banco.model;

import java.text.DecimalFormat;


public class Conta{

    //    protected int number;
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

    public String getFormattedBalance(){
        return DecimalFormat.getCurrencyInstance().format(balance);
    }
}



