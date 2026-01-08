# ⚙️ API - Target Sistemas

API desenvolvida em **NestJS**, organizada em módulos visando independência e escalabilidade do sistema.

Atualmente, todas as operações trabalham em memória (arquivos JSON em `src/data`), mas os serviços já foram pensados com métodos assíncronos para facilitar uma futura troca por chamadas a banco de dados ou APIs externas

---

### Módulos :

- **Sales**: Cálcula comissões a partir das vendas carregadas dos arquivos JSON (simulação de um fluxo ETL fixo) e já possui um método preparado para receber vendas via body request em uma futura evolução.
- **Inventory**: Aplica movimentações de estoque de entrada (`IN`) e saída (`OUT`) sobre um inventário inicial.
- **Finance**: Realiza cálculos financeiros de juros/multa diária sobre pagamentos em atraso.

### Camadas :

- `config/`: Centraliza configurações e "números mágicos" usados pelos módulos.
  - `config/commission`: Define as faixas de valor e porcentagens de comissão.
  - `config/interest`: Define a taxa de juros diária e parâmetros de cálculo financeiro.
  - `config/inventory`: Define o estoque inicial e dados base dos produtos.
- `shared/`: Funções utilitárias genéricas, reutilizadas entre os módulos (datas, juros, dinheiro).
- `modules/`: Agrupa os módulos de negócio (`sales`, `inventory`, `finance`).
  - `controllers/`: Controllers HTTP responsáveis pelas rotas.
  - `services/`: Services que contêm a lógica de cálculo e orquestram domínio + config + utils.
  - `dto/`: DTOs para validar entrada e saída das rotas.
  - `domain/`: Modelos e entidades de domínio (em memória).

### Endpoints :

- `http://localhost:3000/docs`  
  Interface **Swagger** com a documentação interativa da API.

- `GET http://localhost:3000/sales/commissions`  
  Calcula e retorna as comissões por vendedor usando os dados de vendas do JSON padrão.

- `POST http://localhost:3000/sales/commissions` *(feature preparada)*  
  Calcula comissões a partir de vendas enviadas no body (DTO `CalculateCommissionRequestDto`).

- `POST http://localhost:3000/inventory/movements`  
  Aplica movimentação de estoque (`IN`/`OUT`) e retorna quantidade final.

- `POST http://localhost:3000/finance/overdue-payment`  
  Calcula juros de pagamento em atraso (2,5% ao dia) com DTO de entrada/saída.
  
---

### Como rodar o projeto :

1. Certifique-se de ter **Node.js** (e npm) instalado.
2. Acesse a pasta raiz do projeto (`package.json`).
3. execute ambiente de desenvolvimento:
    - npm run start:dev, sobe API e expõe as endpoints mencionadas acima.
    - npm test, para executar a suíte de testes configurada com Jest.