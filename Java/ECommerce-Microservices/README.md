# ecommerce-microservices

1. Requisitos

- **Requisitos funcionais**:
    - Cadastro e autenticação de usuários.
    - Catálogo de produtos com categorias.
    - Carrinho de compras.
    - Pedidos com status (pendente, pago, enviado, entregue).
    - Integração com serviço de pagamento (simulado).
    - Notificações (e-mail ou mensagens sobre pedidos).
    - Painel administrativo (CRUD de produtos e pedidos).
- **Requisitos não funcionais**:
    - Escalabilidade → microserviços independentes.
    - Segurança → autenticação JWT.
    - Manutenibilidade → arquitetura limpa e modular.
    - Banco de dados → PostgreSQL.

2. Arquitetura do Sistema

Modelo baseado em **microserviços desacoplados**:

- **User Service** (Spring Boot + PostgreSQL)
    - Cadastro, login, autenticação JWT.
- **Product Service** (Spring Boot + PostgreSQL)
    - Gerenciamento de produtos, categorias e estoque.
- **Order Service** (Spring Boot + PostgreSQL)
    - Criação e gerenciamento de pedidos.
- **Payment Service** (Spring Boot + API externa simulada)
    - Processamento de pagamento (mock ou integração sandbox).
- **Notification Service** (Spring Boot + e-mail via SMTP ou API externa).

Infraestrutura de suporte:

- **API Gateway** (Spring Cloud Gateway) → entrada única para os serviços.
- **Service Discovery** (Eureka) → registro e descoberta automática dos microserviços.
- **Config Server** (Spring Cloud Config) → centralizar configurações.
- **Banco de dados**: PostgreSQL (cada serviço com seu schema).
- **Cache/Mensageria**: Redis ou RabbitMQ (para fila de pedidos).
- **Frontend**: React + TailwindCSS (SPA) → consumo das APIs.