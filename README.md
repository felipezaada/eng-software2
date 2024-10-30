# Cadastro API

Uma API RESTful para gerenciamento de usuários, desenvolvida em Java com Spring Boot, JPA, Flyway e testes utilizando JUnit.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação.
- **Spring Boot**: Framework para construção de aplicações.
- **JPA**: Para mapeamento objeto-relacional.
- **Flyway**: Para migrações de banco de dados.
- **PostgreSQL**: Banco de dados.
- **JUnit**: Para testes unitários e de integração.

## Pré-requisitos

- Docker e Docker Compose instalados.
- Java 17 ou superior.

## Estrutura do Projeto

- **Controllers**: Contém a lógica de controle da API.
- **Models**: Define a estrutura dos dados (entidades).
- **Repositories**: Interfaces para acesso ao banco de dados.
- **Migrations**: Scripts de migração para o Flyway.
- **Tests**: Testes unitários e de integração para garantir o funcionamento correto da API.

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone <URL do repositório>
   cd <nome do repositório>

2. **Suba os containers Docker:**
   ```bash
   docker compose up

**Isso iniciará os serviços do PostgreSQL e do Flyway.**

3. **Execute as migrações**
    ```bash
   O Flyway irá automaticamente aplicar as migrações definidas na pasta 
   migrations ao iniciar.

4. **Acesse a API:**
    ```bash
   A API estará disponível em http://localhost:8090/cadastro/swagger-ui/index.html.
   
## Endpoints

- **GET /user/all**: Retorna todos os usuários.
- **GET /user/searchID?id=<UUID>**: Busca um usuário pelo ID.
- **GET /user/searchName?name=<nome>**: Busca usuários pelo nome.
- **POST /user/post**: Salva um novo usuário.
- **DELETE /user/delete?login=<login>**: Remove um usuário pelo login.

## Testes

**`./mvnw test`**

## © 2024 Felipe Eduardo. Todos os direitos reservados.
    

 