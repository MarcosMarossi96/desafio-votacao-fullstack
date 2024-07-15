# Voting React

## Descrição
Projeto da API para o desafio Fullstack para votações cooperativas.

## Índice
1. [Instalação](#instalação)
2. [Uso](#uso)
3. [Funcionalidades](#funcionalidades)
4. [Requisitos](#requisitos)

## Instalação
Instruções sobre como instalar e configurar o ambiente para rodar o projeto.

```bash
#Clone o repositório
git clone https://github.com/MarcosMarossi96/desafio-votacao-fullstack
```

### Entre no diretório do projeto
cd VotingAPI

### Instale as dependências

```bash
mvn clean package
## Ou 
mvn install
```

## Uso

Para utilizar o projeto basta executar:

```bash
mvn spring-boot:run
```

A base de dados é MySQL, para executar a aplicação é necessário ter uma instância na máquina e criar o banco de dados: voting_api_db.

Também é possível executar a API completa via docker, basta entrar no diretório raiz e executar: docker compose up -d

Com isso não é nessário criar um schema local, o próprio docker compose faz esse processo.

## Funcionalidades

O usuário pode criar novos associados e pautas, depois iniciar uma sessão de votação e cadastrar um voto por associado.

## Requisitos

Ter o Java 17 instalado na máquina e o maven.

Acessar o swagger em: http://localhost:8080/vote/v1/swagger-ui/index.html#/
