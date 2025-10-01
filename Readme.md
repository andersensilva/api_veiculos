# Projeto de Cadastro de Carros (Kotlin)

Este projeto contém a estrutura de um banco de dados simples para cadastro de carros, modelos e marcas. Além disso, a aplicação possui uma API documentada via Swagger para facilitar testes.

## Estrutura do Banco de Dados

O banco é composto por três tabelas principais:

### Tabela `marca`
- **Descrição:** Armazena as marcas dos veículos.
- **Campos:**
    - `id` (PK, auto increment) – identificador único da marca.
    - `nomeMarca` – nome da marca do veículo.

### Tabela `modelo`
- **Descrição:** Contém os modelos de cada marca.
- **Campos:**
    - `id` (PK, auto increment) – identificador único do modelo.
    - `nome` – nome do modelo.
    - `valorFipe` – valor médio de mercado (tabela FIPE).
    - `marcaId` (FK → `marca.id`) – marca à qual o modelo pertence.

### Tabela `carro`
- **Descrição:** Registra os carros cadastrados.
- **Campos:**
    - `id` (PK, auto increment) – identificador único do carro.
    - `timestampCadastro` – data e hora de cadastro (formato UNIX timestamp).
    - `ano` – ano do veículo.
    - `combustivel` – tipo de combustível.
    - `numPortas` – quantidade de portas.
    - `cor` – cor do veículo.
    - `modeloId` (FK → `modelo.id`) – modelo do carro.

## Inserção de Dados

Os dados iniciais são carregados automaticamente pelo Spring Boot a partir do arquivo **`data.sql`** localizado em `src/main/resources`.

Exemplo do conteúdo do `data.sql`:

```sql
-- Inserir marcas
INSERT INTO marca (nome_marca) VALUES ('CHEVROLET');
INSERT INTO marca (nome_marca) VALUES ('VOLKSWAGEN');

-- Inserir modelos
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('ONIX PLUS', 50000, 1);
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('JETTA', 49000, 2);
INSERT INTO modelo (nome, valor_fipe, marca_id) VALUES ('HILLUX SW4', 47500, 2);

-- Inserir carros
INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696539488, 2015, 'FLEX', 4, 'BEGE', 1);

INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696531234, 2014, 'FLEX', 4, 'AZUL', 2);

INSERT INTO carro (timestamp_cadastro, ano, combustivel, num_portas, cor, modelo_id)
VALUES (1696535432, 1993, 'DIESEL', 4, 'AZUL', 3);
```

## Inicialização e Dados

O Spring Boot executa o script `data.sql` automaticamente ao iniciar a aplicação, garantindo que as tabelas já contenham dados para testes.

## Tecnologias Utilizadas

- **Kotlin** → linguagem principal do projeto.
- **Spring Boot 3.5.6** → framework para inicialização rápida e configuração simplificada.
- **Spring Web (spring-boot-starter-web)** → criação dos endpoints REST.
- **Spring Data JPA** → abstração para acesso e persistência de dados.
- **Spring Security + JWT** → gerenciamento de autenticação e autorização.
- **H2 Database** → banco em memória para testes.
- **Swagger / OpenAPI** → documentação interativa da API.
- **JUnit 5 & MockMvc** → testes unitários e de integração.

## Segurança da API

- Autenticação baseada em **JWT**.
- Endpoint de login: `/auth/login`.
- Token JWT deve ser enviado no **header**: Authorization: Bearer <seu_token>

- Endpoints públicos: `/auth/**`, `/swagger-ui/**`, `/v3/api-docs/**`.
- Todos os demais endpoints exigem autenticação.

### Usuário de teste
- **Usuário:** `admin`
- **Senha:** `123456`

## Configuração de CORS

Foi configurado o **CORS** para permitir que um frontend (ex: React, Next.js) consuma a API sem problemas de origem cruzada.

- A origem permitida é definida na propriedade:

```properties
app.cors.allowed-origin=http://localhost:3000
```

## Como Executar e Testar a API

1. Acesse a documentação via Swagger: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
2. Teste os endpoints de `marca`, `modelo` e `carro`.
3. Endpoints suportam criação, consulta, atualização e exclusão.

## Testes

O projeto possui testes unitários e de integração utilizando **JUnit 5** e **MockMvc**.

### Rodar os testes (Maven)

```bash
mvn test
```
