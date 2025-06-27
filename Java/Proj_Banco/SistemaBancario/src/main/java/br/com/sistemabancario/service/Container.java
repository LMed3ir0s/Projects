package main.java.br.com.sistemabancario.service;

import main.java.br.com.sistemabancario.dao.*;

public class Container {
    public static final AgenciaDAO agenciaDAO = new AgenciaDAO();
    public static final ClienteDAO clienteDAO = new ClienteDAO();
    public static final ContaCorrenteDAO contaCorrenteDAO = new ContaCorrenteDAO();
    public static final ContaDAO contaDAO = new ContaDAO();
    public static final ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
    public static final PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO();
    public static final PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO();

    // Injeção de dependências (DAOs)
    public class AgenciaService {
        private final AgenciaDAO agenciaDAO;

        public AgenciaService(AgenciaDAO agenciaDAO){
            this.agenciaDAO = agenciaDAO;
        }
    }

    public class ClienteService {
        private final ClienteDAO clienteDAO;

        public ClienteService(ClienteDAO clienteDAO){
            this.clienteDAO = clienteDAO;
        }
    }

    public class ContaCorrenteService{
        private final ContaCorrenteDAO contaCorrenteDAO;

        public ContaCorrenteService(ContaCorrenteDAO contaCorrenteDAO){
            this.contaCorrenteDAO = contaCorrenteDAO;
        }
    }

    public class ContaService{
        private final ContaDAO contaDAO;

        public ContaService(ContaDAO contaDAO){
            this.contaDAO = contaDAO;
        }
    }

    public class ContaPoupancaService{
        private final ContaPoupancaDAO contaPoupancaDAO;

        public ContaPoupancaService(ContaPoupancaDAO contaPoupancaDAO){
            this.contaPoupancaDAO = contaPoupancaDAO;
        }
    }

    public class PessoaFisicaService{
        private final PessoaFisicaDAO pessoaFisicaDAO;

        public PessoaFisicaService(PessoaFisicaDAO pessoaFisicaDAO){
            this.pessoaFisicaDAO = pessoaFisicaDAO;
        }
    }

    public class PessoaJuridicaService{
        private final PessoaJuridicaDAO pessoaJuridicaDAO;

        public PessoaJuridicaService(PessoaJuridicaDAO pessoaJuridicaDAO){
            this.pessoaJuridicaDAO = pessoaJuridicaDAO;
        }
    }
}
