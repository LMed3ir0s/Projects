# 🔗 Encurtador de URLs com Spring Boot

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![Status](https://img.shields.io/badge/status-em%20desenvolvimento-yellow)

---

## 📌 Descrição

Projeto backend de encurtador de URLs desenvolvido em **Java 17** com **Spring Boot**. Transforma URLs longas em versões curtas e úteis. Este sistema pode ser utilizado em aplicações web, redes sociais e sistemas de marketing.

### Ferramentas:

* Java 17
* Spring Boot 3.2.5
* Maven
* PostgreSQL (planejado)
* IntelliJ IDEA (Community Edition)
* Git

---

## ⚙️ Instalação

### 📦 Requisitos

* Java 17+
* Maven 3.8+
* PostgreSQL
* IntelliJ IDEA (ou outro IDE compatível)
* Git

### 🛠 Passo a passo:

```bash
# Clone o repositório principal
git clone https://github.com/LMed3ir0s/Projects

# Acesse a pasta do projeto
cd Projects/Java/EncurtadorURL

# Instale as dependências
mvn install

# Rode a aplicação
mvn spring-boot:run
```

---

## ▶️ Como usar

```bash
1. Acesse: http://localhost:8080/shorten
2. Envie uma URL longa no corpo da requisição via POST
3. Receba a resposta com a URL encurtada
```

---

## 📡 API Endpoint

**POST /shorten**

### Corpo da requisição:

```json
"https://exemplo.com/minha-url"
```

### Resposta esperada:

```json
{
  "originalUrl": "https://exemplo.com/minha-url",
  "shortURL": "abc123"
}
```

> *Um endpoint é um ponto de acesso da API para interagir com recursos do backend.*

---

## 🧪 Progresso

* [x] Estrutura inicial do projeto
* [x] Controller, Service, Repository e Model
* [x] Lógica de encurtamento com UUID
* [ ] Implementar redirecionamento para a URL original
  * Ainda será desenvolvida a lógica de redirecionamento local (localhost) para testes do programa
* [ ] Testes unitários e de integração

---

## 📄 Licença

Este projeto está licenciado sob a licença MIT.

Você pode usar, modificar, distribuir e até comercializar este software livremente, desde que mantenha o aviso de copyright e esta permissão em todas as cópias ou partes substanciais do software.

Aviso:
Este software é fornecido "no estado em que se encontra", sem garantias de qualquer tipo, expressas ou implícitas.
O autor não se responsabiliza por danos, perdas ou mau uso decorrentes do uso deste programa.

---

## 👨‍💻 Autor

**Lucas Medeiros**
Desenvolvedor Java / Python | QA Tester | Engenheiro Civil
[GitHub](https://github.com/LMed3ir0s)

---


