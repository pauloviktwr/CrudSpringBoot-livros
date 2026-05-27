# Livros Web Application

![Java](https://img.shields.io/badge/Java-17+-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-green?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0+-brightgreen?logo=thymeleaf&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-yellow?logo=hibernate&logoColor=white)

![Vídeo descritivo do sistema](https://github.com/pauloviktwr/CrudSpringBoot-livros/assets/127359543/df975e71-b652-4c05-ad39-d5473330b22d)

---

Aplicação de gerenciamento de livros desenvolvida com **Spring Boot**, **Spring Data JPA** e **Thymeleaf**.

## Objetivo

Demonstrar competências de desenvolvedor Java júnior em:
- Arquitetura em camadas: `Controller`, `Service`, `Repository`, `DTO`
- Construção de API REST com `@RestController`
- Validação de dados com Jakarta Bean Validation
- Persistência com JPA/Hibernate
- Testes unitários com JUnit 5 e Mockito
- Tratamento global de exceções

## Tecnologias

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Thymeleaf
- Maven
- Jakarta Bean Validation
- JUnit 5 + Mockito
- MySQL (produção)
- H2 (testes)

## Funcionalidades

- CRUD completo para livros
- Endpoints REST em `/api/livros`
- Interface web em `/livros`
- DTOs para entrada de dados
- Validação de campos com `@NotBlank` e `@Size`
- Tratamento de erros centralizado com `@RestControllerAdvice`

## Estrutura do projeto

- `controller/` — controllers REST e MVC
- `service/` — regras de negócio e transações
- `repository/` — persistência JPA
- `model/` — entidade `Livro`
- `model/dto/` — DTOs de entrada
- `infra/exception/` — tratamento global de erros
- `test/` — testes unitários

## Como executar

1. Atualize `src/main/resources/application.properties` com as credenciais do MySQL.
2. Execute:

```bash
mvn clean install
mvn spring-boot:run
```

3. Acesse:
- Interface web: `http://localhost:8080/livros`
- API REST: `http://localhost:8080/api/livros`

## Endpoints principais

| Método | Rota | Descrição |
|---|---|---|
| GET | `/api/livros` | Lista todos os livros |
| GET | `/api/livros/{id}` | Busca livro por ID |
| POST | `/api/livros` | Cria novo livro | 
| PUT | `/api/livros/{id}` | Atualiza livro existente |
| DELETE | `/api/livros/{id}` | Remove livro |

## Testes

- `src/test/java/com/portfolio/livros/service/LivroServiceTest.java`
- `src/test/java/com/portfolio/livros/controller/LivroRestControllerTest.java`

Os testes cobrem comportamento de serviço, validações esperadas e contratos REST básicos.

## Competências aplicadas

- Separação por camadas
- Design de APIs REST
- Uso de `@Valid` e DTOs
- Mocking com Mockito
- Tratamento de exceções com `@ExceptionHandler`
- mapeamento de entidade JPA

## Observações

A aplicação é adequada para demonstrar conhecimentos em Java backend com Spring Boot, arquitetura limpa e testes automatizados.
