package banco.controller;


import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;
import banco.service.BancoService;
import banco.utils.BancoUtils;


public class BancoController {
    private BancoService bancoService = new BancoService();

    // => Valida Nome
    public static String validaNome(String name){
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Nome inválido");
        }
        name = name.trim();
        return name;
    }

    // => Valida razaoSocial
    public static String validaRazaoSocial(String razaoSocial){
    if (razaoSocial == null || razaoSocial.trim().isEmpty()){
        throw new IllegalArgumentException("Razão Social inválida");
    }
    razaoSocial = razaoSocial.trim();
    return razaoSocial;
 }

    // => Valida CPF
    public static String validaCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF inválido");
        }
        cpf = cpf.trim();
        return cpf;
    }

    // => Valida CMPJ
    public static String validaCNPJ(String cnpj){
        if (cnpj == null || cnpj.trim().isEmpty()){
            throw new IllegalArgumentException("CNPJ inválido");
        }
        cnpj = cnpj.trim();
        return cnpj;
    }

    // => Valida Cidade
    public static String validaCity(String city){
        if (city == null || city.trim().isEmpty()){
            throw new IllegalArgumentException("Cidade inválida");
        }
        city = city.trim();
        return city
    }

    // => Valida Estado
    public static String validaState(String state){
        if (state == null || state.trim().isEmpty()){
            throw new IllegalArgumentException("Estado inválido");
        }
        state = state.trim();
        return state;
    }


    // => Cria Cliente PessoaFisica
    public static String criaClientePessoaFisica(String name, String cpf, String city, String state){
        BancoUtils.validaCampoObjeto(name,"NOME");
        BancoUtils.validaCampoObjeto(cpf,"CPF");
        BancoUtils.validaCampoObjeto(city,"CIDADE");
        BancoUtils.validaCampoObjeto(state,"ESTADO");
        PessoaFisica cliente = BancoService.criarClientePessoaFisica(name,cpf,city,state);
        return BancoUtils.messageView("Pessoa Física Cadastrada com sucesso!\n" + cliente.listDados());
    }

    // => Cria Cliente PessoaJuridica
    public static String criaClientePessoaJuridica(String razaoSocial, String cnpj, String city, String state){
        BancoUtils.validaCampoObjeto(razaoSocial,"Razao Social");
        BancoUtils.validaCampoObjeto(cnpj,"CNPJ");
        BancoUtils.validaCampoObjeto(city,"CIDADE");
        BancoUtils.validaCampoObjeto(state,"ESTADO");
        PessoaJuridica cliente = BancoService.criarClientePessoaJuridica(razaoSocial, cnpj, city, state);
        return BancoUtils.messageView("Pessoa Jurídica Cadastrada com sucesso!\n" + cliente.listDados());
    }






//
//
//
//

}


// recebe entrada dos usuarios e encaminha para service (verif tipo de entrada se correta)