# 🛒 E-commerce Microservices Architecture

Backend robusto para uma plataforma de e-commerce desenvolvido com **Java e Spring Boot**, projetado com uma arquitetura escalável que evoluiu de um modelo monolítico para **microsserviços distribuídos**, utilizando conteinerização e boas práticas de engenharia de software.

---

## 🚀 Tecnologias e Arquitetura

* **Backend & Core:** Java, Spring Boot, Spring MVC, Spring Data JPA / Hibernate
* **Banco de Dados:** PostgreSQL, H2 (testes)
* **Arquitetura:** Microsserviços independentes, Padrão DTO, API Gateway, Camada de Serviço desacoplada
* **DevOps & Infraestrutura:** Docker, Docker Compose, Docker Networking
* **Observabilidade:** Spring Boot Actuator (métricas, saúde e diagnóstico)

---

## 💡 Visão Geral do Sistema

O projeto simula um ecossistema de e-commerce real, estruturado para garantir alta coesão e baixo acoplamento entre os domínios de negócio:

1. **User Service:** Gerenciamento de usuários, perfis e mapeamento de endereços com relacionamentos JPA otimizados.
2. **Product Service:** Catálogo de produtos, operações CRUD avançadas e processamento de dados com Java Streams.
3. **Order & Cart Service:** Lógica de carrinho de compras, controle de sessões de usuário e fluxo transacional de fechamento de pedidos.

---

## 📊 Observabilidade e Monitoramento
A aplicação integra o **Spring Boot Actuator** para monitoramento de saúde e métricas em tempo real, expondo endpoints estratégicos (`/health`, `/metrics`, `/beans`, `/loggers`) para garantir visibilidade e facilitar o diagnóstico de falhas em ambiente de produção.

---

## 🐳 Infraestrutura e Execução com Docker

O ecossistema é totalmente conteinerizado, permitindo o provisionamento rápido da aplicação integrada ao banco de dados relacional.

### Pré-requisitos
* **Java JDK 17+**
* **Docker & Docker Compose**

### Rodando o Ambiente
1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/seu-repositorio.git](https://github.com/seu-usuario/seu-repositorio.git)
   cd seu-repositorio


### 📝 Licença
Projeto desenvolvido por CasaliTech.