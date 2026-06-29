# Livros Web Application

![Java](https://img.shields.io/badge/Java-21+-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2+-green?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0+-brightgreen?logo=thymeleaf&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-yellow?logo=hibernate&logoColor=white)

Aplicação de gerenciamento de livros em **Java 21** com **Spring Boot 3.2**, arquitetura em camadas e API REST.

---

## 1) Objetivo

Entregar projeto Java:
- Arquitetura em camadas: `Controller`, `Service`, `Repository`, `DTO`
- APIs REST com Spring Boot
- Validação com Jakarta Bean Validation
- Persistência com JPA/Hibernate
- Testes automatizados com JUnit 5 e Mockito
- Containerização com Docker
- Integração com MySQL e perfil de testes H2
- Microsserviços e mensageria.

---

## 2) Arquitetura e Padrões
- `Controller` → `Service` → `Repository` com Spring Data JPA
- DTOs de entrada e saída para isolar a entidade de persistência
- `@RestControllerAdvice` para tratamento global de exceções
- MVC com Thymeleaf para interface web e API REST coexistentes

---

## 3) Como executar
Pré-requisitos: Java 21, Maven 3.9+

### Local
```bash
./mvnw clean package
```

### Jar
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

### Testes
```bash
./mvnw test
```

---

## 4) Containerização
- `Dockerfile` multi-stage para build e runtime
- `docker-compose.yml` orquestra o app com MySQL
- app na porta `8080`
- MySQL na porta `3306`

### Executar
```bash
docker compose up --build
```

### Parar
```bash
docker compose down
```

---

## 5) Testes
- JUnit 5, Mockito, AssertJ
- Testes de serviço e controller REST
- Arquivos principais:
  - `src/test/java/com/portfolio/livros/service/LivroServiceTest.java`
  - `src/test/java/com/portfolio/livros/controller/LivroRestControllerTest.java`
  - `src/test/java/com/portfolio/livros/infra/exception/GlobalExceptionHandlerTest.java`

---

## Competências demonstradas
- Desenvolvimento backend Java com Spring Boot
- Arquitetura limpa e desacoplada
- API REST e validação de entrada
- Persistência relacional e configuração de perfis
- Testes automatizados e CI-ready
- Docker local com compose
