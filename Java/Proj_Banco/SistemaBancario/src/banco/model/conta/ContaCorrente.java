package banco.model.conta;

import banco.model.cliente.Cliente;
import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;

import java.text.DecimalFormat;

public class ContaCorrente extends Conta {

    private double limit;
    private double juros;

    // => Construtores

    // => casting para instanciar obj cliente como parametro
    public ContaCorrente(Cliente titular){
        super(titular);
        limit = 1000;
        juros = 5.9;
    }

    public ContaCorrente(Cliente cliente, double limit, double juros){
        super(cliente);
        this.limit = limit;
        this.juros = juros;
    }

    // => Getters

    @Override
    public String getFormattedBalance(){
        return DecimalFormat.getCurrencyInstance().format(balance + limit);
    }

    // => Métodos
    @Override
    public boolean withdraw(double value){
        if (balance + limit >= value){
            balance -= value;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String listDados(){
        String name;
        if (titular instanceof PessoaFisica){
            name = ((PessoaFisica)titular).getName();
        } else {
            name = ((PessoaJuridica)titular).getRazaoSocial();
        }
        String dados = "NUMERO: " + number + "\n" +
                "CORRENTISTA: " + name + "\n" +
                "SALDO: " + getFormattedBalance();
        return dados;
    }

    public double applicationJuros(){
        if(balance<0){
            double jurosAplicado = balance * juros / 100;
            balance -= jurosAplicado;
            return jurosAplicado;
        }
        return 0.0;
    }

}
