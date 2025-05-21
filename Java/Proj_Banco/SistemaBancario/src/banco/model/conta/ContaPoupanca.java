package banco.model.conta;

import banco.model.cliente.Cliente;
import java.util.Calendar;


public class ContaPoupanca extends Conta{

    private int bday;
    private double yield;

    // => Construtores

    // => casting para instanciar obj cliente como parametro
    public ContaPoupanca(Cliente titular){
        super(titular);
        bday = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        yield = 0.52;
    }

    public ContaPoupanca(Cliente titular, int bday, double yield){
        super(titular);
        this.bday = bday;
        this.yield = yield;
    }

    // Setters

    public void setYield(double yield){
        this.yield = yield;
    }

    // => Métodos

    @Override
    public boolean withdraw(double value){
        int today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if ((today > bday) && (balance >= value)){
            balance -= value;
            return true;
        }else{
            return false;
        }
    }

 public void applicationYield(double value){
        if (bday == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)){
            balance = balance + (balance * yield / 100);
        }
    }

 }

