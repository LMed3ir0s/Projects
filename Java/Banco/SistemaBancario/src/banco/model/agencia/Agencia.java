package banco.model.agencia;

import banco.model.conta.Conta;

import java.util.ArrayList;
import java.util.List;

public class Agencia {

    private int number;
    private String name;
    private List<Conta> accounts;

    // => Construtor

    public Agencia(int number, String name){
        this.number = number;
        this.name = name;
        accounts = new ArrayList<>();
    }

    // => Getters

    public int getNumber(){
        return number;
    }

    public  String getName(){
        return name;
    }

    public List<Conta> getAccounts(){
        return accounts;
    }

    // => Metodos

    public void addAccount(Conta conta){
        accounts.add(conta);
    }

    public void delAccount(Conta conta){
        accounts.remove(conta);
    }
}
