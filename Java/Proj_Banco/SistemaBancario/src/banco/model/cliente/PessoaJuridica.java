package banco.model.cliente;

public class PessoaJuridica extends Cliente{

    private String cnpj;
    private String razaoSocial;

    // Construtores:

    // => Construtor cliente com valores pré-definidos para evitar valor null"
    public PessoaJuridica(){
        super();
        cnpj = "INDEFINIDO";
        razaoSocial = "INDEFINIDO";
    }

    // => Construtor cliente com todos atributos à serem definidos
    public PessoaJuridica(String city, String state, String cnpj, String razaoSocial){
        super(city, state);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    // => Getters:

    public String getCnpj(){
        return cnpj;
    }

    public String getRazaoSocial(){
        return razaoSocial;
    }

    // => Setters:

    public void setCnpj(String cnpj){
        if (cnpj.isEmpty())
            this.cnpj = " CNPJ NÃO FORNECIDO";
        else
            this.cnpj = cnpj;
    }

    public void setRazaoSocial(String razaoSocial){
        if (razaoSocial.isEmpty())
            this.razaoSocial = " RAZÃO SOCIAL NÃO FORNECIDA";
        else
            this.razaoSocial = razaoSocial;
    }

    // => Métodos:

    public String listDados(){
        String dados = "CNPJ: " + cnpj + "\n" +
                "RAZÃO SOCIAL: " + razaoSocial + "\n" +
                super.listDados();
        return dados;
    }

}
