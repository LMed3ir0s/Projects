package banco.model.cliente;

public class PessoaFisica extends Cliente {

    private String name;
    private String cpf;


    // => Construtores:

    // => Construtor cliente com valores pré-definidos para evitar valor null"
    public PessoaFisica(){
        super();
        name = "INDEFINIDO";
        cpf = "INDEFINIDO";;
    }

    // => Construtor cliente com todos atributos à serem definidos
    public PessoaFisica(String name, String cpf, String city, String state){
        super(city, state);
        this.name = name;
        this.cpf = cpf;
    }


    // => Getters:

    public String getName(){
        return name;
    }

    public String getCpf(){
        return cpf;
    }

    // => Setters:

    public void setName(String name){
        if (name.isEmpty())
            this.name = " NOME NÃO FORNECIDO";
        else
            this.name = name;
    }

    public void setCpf(String cpf){
        if (cpf.isEmpty())
            this.cpf = " CPF NÃO FORNECIDO";
        else
            this.cpf = cpf;
    }

    // => Métodos:

    public String listDados(){
        String dados = "NOME: " + name + "\n" +
                "CPF: " + cpf + "\n" + super.listDados();
        return dados;
    }


}
