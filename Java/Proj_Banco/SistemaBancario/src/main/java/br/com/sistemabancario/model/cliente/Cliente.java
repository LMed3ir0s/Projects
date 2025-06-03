package main.java.br.com.sistemabancario.model.cliente;

public class Cliente {

    protected int codigo;
    protected String city;
    protected String state;

    private static int numb_cliente = 0;

    // => Construtores:

    // => Construtor cliente com valores pré-definidos para evitar valor null"
    public Cliente(){
        numb_cliente++;
        codigo = numb_cliente;
        city = " INDEFINIDO";
        state = " DF";
    }

    // => Construtor cliente com todos atributos à serem definidos
    public Cliente(String city, String state){
        numb_cliente++;
        codigo = numb_cliente;
        setCity(city);
        setState(state);
    }

    // => Getters:

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    // => Setters:
//    public void setName(String name){
//        if (name.isEmpty())
//            this.name = " NOME NÃO FORNECIDO";
//        else
//            this.name = (name.contains(" ") ? name.substring(0,name.indexOf(' ')): name); // => operador ternário: condição ? 1º nome (true) : nome completo (false)
//    }

    // => Métodos:

    public void setCity(String city){
        if (city.isEmpty())
            this.city = " CIDADE NÃO FORNECIDA";
        else
            this.city = city;
    }

    public void setState(String state){
        if (state.isEmpty())
            this.state = " ESTADO NÃO FORNECIDO";
        else
            this.state = state;
    }

    public String listDados(){
        String dados = "CODIGO:" + codigo + "\n" +
                "CIDADE:" + city + "\n" +
                "ESTADO:" + state + "\n";
        return dados;
    }

    public static int qtdClientes(){
        return numb_cliente;
    }
}
