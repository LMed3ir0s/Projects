# 🏦 Sistema Bancário em Java

![Java](https://img.shields.io/badge/Java-17-blue)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

---

## 📌 Descrição

Projeto simples de sistema bancário em Java, com simulação de operações como depósito e saque, utilizando entrada e saída de dados com `JOptionPane`.

O objetivo principal deste projeto é praticar conceitos de **orientação a objetos (POO)**, criação de classes como `Cliente` e `Conta`, e organização de código em camadas **MVC**.

---

## Ferramentas:

* Java 17
* IntelliJ IDEA (Community Edition)

---

## ⚙️ Instalação

### 📦 Requisitos

* Java 17+
* IntelliJ IDEA (ou outro IDE compatível)
* Git

### 🛠 Passo a passo:

```bash
# Clone o repositório principal
git clone https://github.com/LMed3ir0s/Projects

# Acesse a pasta do projeto
cd Projects/Java/SistemaBancario

```

📂 Estrutura planejada

SistemaBancario/
├── model/
│   ├── Cliente.java
│   └── Conta.java
├── controller/
│   └── ContaController.java (planejado)
├── service/
│   └── ContaService.java (planejado)
└── view/
    └── Main.java

O projeto está sendo estruturado com foco em organização e boas práticas de desenvolvimento.

---

## 📂 Estrutura atual do projeto

```bash
SistemaBancario/
├── model/
│   ├── Cliente.java
│   └── Conta.java
├── view/
│   └── Main.java

🔧 Em breve: será criado o pacote controller/ com a classe ContaController.

▶️ Funcionalidades atuais
✅ Cadastro de clientes com validação de dados

✅ Criação de conta bancária vinculada ao cliente

✅ Operações de depósito e saque com verificação de saldo

✅ Interface com JOptionPane para entrada e exibição de dados

🧪 Progresso

* [x] Estruturação inicial da pasta do projeto

* [x] Implementação das classes Cliente e Conta

* [x] Desenvolvimento inicial da interface com JOptionPane

* [x] Lógica das operações concentrada no Main.java (pendente refatoração para controller)

* [ ] Desenvolvimento das camadas Controller e Service

* [ ] Futuro desenvolvimento de testes unitários

📄 Licença
Este projeto está licenciado sob a licença MIT.

👨‍💻 Autor
Lucas Medeiros
Desenvolvedor Java / Python | QA Tester | Engenheiro Civil