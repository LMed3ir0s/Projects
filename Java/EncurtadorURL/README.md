# 🔗 Encurtador de URLs com Spring Boot

Projeto backend de encurtador de URLs desenvolvido em **Java 17** com **Spring Boot**. Transforma URLs longas em versões curtas e úteis. Este sistema pode ser utilizado em aplicações web, redes sociais e sistemas de marketing.

### Tecnologias utilizadas:

* Java 17
* Spring Boot 3.2.5
* PostgreSQL (planejado)

---

### ▶️ Como usar

```bash
1. Acesse: http://localhost:8080/shorten
2. Envie uma URL longa no corpo da requisição via POST
3. Receba a resposta com a URL encurtada
```



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

---

### 🧪 Progresso:

* [x] Estrutura inicial do projeto
* [x] Controller, Service, Repository e Model
* [x] Lógica de encurtamento com UUID
* [ ] Implementar redirecionamento para a URL original
  * Ainda será desenvolvida a lógica de redirecionamento local (localhost) para testes do programa
* [ ] Testes unitários e de integração

---
