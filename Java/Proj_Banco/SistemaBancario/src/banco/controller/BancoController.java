package banco.controller;


import banco.model.agencia.Agencia;
import banco.model.cliente.Cliente;
import banco.model.cliente.PessoaFisica;
import banco.model.cliente.PessoaJuridica;
import banco.model.conta.Conta;
import banco.model.conta.ContaCorrente;
import banco.model.conta.ContaPoupanca;
import banco.service.BancoService;
import banco.utils.BancoUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class BancoController {
    private BancoService bancoService = new BancoService();

    // => Metodo cria Agencia
    public  static Agencia newAgencia(int number, String name){
        BancoUtils.validaCampoObjeto(number,"NUMERO AGÊNCIA");
        BancoUtils.validaCampoObjeto(name,"NOME AGÊNCIA");
        return BancoService.criarAgencia(number,name);
    }

    // => Metodo executa mapUserInput
    public static Cliente newClienteType(String tipoCliente) {
        final Cliente[] clienteCriado = new Cliente[1];

        Runnable runNewClienteType = switch (tipoCliente.toUpperCase()) {
            case "F" -> () -> {
                var name = BancoUtils.solicitaInput("Nome do Cliente: ");
                BancoController.validaNome(name);
                var cpf = BancoUtils.solicitaInput("CPF do Cliente: ");
                BancoController.validaCPF(cpf);
                var city = BancoUtils.solicitaInput("Cidade do Cliente: ");
                BancoController.validaCity(city);
                var state = BancoUtils.solicitaInput("Estado do Cliente: ");
                BancoController.validaState(state);
                clienteCriado[0] = BancoController.criaClientePessoaFisica(name, cpf, city, state);
            };
            case "J" -> () -> {
                var razaoSocial = BancoUtils.solicitaInput("Razão Social: ");
                BancoController.validaRazaoSocial(razaoSocial);
                var cnpj = BancoUtils.solicitaInput("CNPJ do Cliente: ");
                BancoController.validaCNPJ(cnpj);
                var city = BancoUtils.solicitaInput("Cidade do Cliente: ");
                BancoController.validaCity(city);
                var state = BancoUtils.solicitaInput("Estado do Cliente: ");
                BancoController.validaState(state);
                BancoController.criaClientePessoaJuridica(cnpj, razaoSocial, city, state);
            };
            default -> null;
        };

        if (runNewClienteType == null) {
            BancoUtils.messageView("OPÇÃO INVÁLIDA! Encerrando o programa...");
            return null;
        }
        runNewClienteType.run();
        return null;
    }

    // Map tipo de Conta
    private static final Map<String, Function<Cliente, Conta>> mapTipoConta = new HashMap<>();

    static {
        mapTipoConta.put("C", ContaCorrente::new);
        mapTipoConta.put("P", ContaPoupanca::new);
    }

    // => Metodo executa mapTipoConta
    public static Conta newContaType(String tipoConta, Cliente cliente) {
        Function<Cliente, Conta> funcNewContaType = mapTipoConta.get(tipoConta.toUpperCase());
        if (funcNewContaType == null) {
            BancoUtils.messageView("OPÇÃO INVÁLIDA! Encerrando o programa...");
            return null;
        }
        return funcNewContaType.apply(cliente);
    }


    // => Valida Nome
    public static String validaNome(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        name = name.trim();
        return name;
    }

    // => Valida razaoSocial
    public static String validaRazaoSocial(String razaoSocial) {
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
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
    public static String validaCNPJ(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        cnpj = cnpj.trim();
        return cnpj;
    }

    // => Valida Cidade
    public static String validaCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade inválida");
        }
        city = city.trim();
        return city;
    }

    // => Valida Estado
    public static String validaState(String state) {
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("Estado inválido");
        }
        state = state.trim();
        return state;
    }


    // => Cria Cliente PessoaFisica
    public static Cliente criaClientePessoaFisica(String name, String cpf, String city, String state) {
        BancoUtils.validaCampoObjeto(name, "NOME");
        BancoUtils.validaCampoObjeto(cpf, "CPF");
        BancoUtils.validaCampoObjeto(city, "CIDADE");
        BancoUtils.validaCampoObjeto(state, "ESTADO");
        PessoaFisica cliente = BancoService.criarClientePessoaFisica(name, cpf, city, state);
        BancoUtils.messageView("Pessoa Física Cadastrada com sucesso!\n" + cliente.listDados());
        return cliente;
    }

    // => Cria Cliente PessoaJuridica
    public static Cliente criaClientePessoaJuridica(String razaoSocial, String cnpj, String city, String state) {
        BancoUtils.validaCampoObjeto(razaoSocial, "Razao Social");
        BancoUtils.validaCampoObjeto(cnpj, "CNPJ");
        BancoUtils.validaCampoObjeto(city, "CIDADE");
        BancoUtils.validaCampoObjeto(state, "ESTADO");
        PessoaJuridica cliente = BancoService.criarClientePessoaJuridica(razaoSocial, cnpj, city, state);
        BancoUtils.messageView("Pessoa Jurídica Cadastrada com sucesso!\n" + cliente.listDados());
        return cliente;
    }
}