# TMS - CRUD Grau A

API REST em Spring Boot para cadastro de categorias, transportadoras e contatos.

## O que precisa

- Java 21 ou mais novo
- MySQL rodando em `localhost:3306`

Não precisa instalar o Maven, o projeto usa o Maven Wrapper (`mvnw`).

## Banco de dados

O banco `tms` e as tabelas são criados automaticamente quando a aplicação sobe.

Por padrão a aplicação usa o usuário `root` com a senha `admin`. Se o seu MySQL usar outra senha, defina as variáveis antes de rodar:

```powershell
$env:DB_USER = "root"
$env:DB_PASSWORD = "sua_senha"
```

## Como rodar

Windows:

```powershell
.\mvnw spring-boot:run
```

Linux / Mac:

```bash
sh mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`. Na primeira execução o banco já é populado com alguns dados de exemplo.

## Endpoints

- `/categorias`
- `/transportadoras`
- `/transportadoras/{transportadoraId}/contatos`

Documentação da API -> [![Postman](https://img.shields.io/badge/Postman-Collection-orange)](https://www.postman.com/bernardo013-9220741/workspace/grau-a-api-tms/collection/55174951-d39f0e23-c58c-4f51-a908-d27d5ea5a7e2?action=share&source=copy-link&creator=55174951)
