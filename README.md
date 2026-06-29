# Livros Web Application

![Java](https://img.shields.io/badge/Java-21+-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2+-green?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0+-brightgreen?logo=thymeleaf&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-yellow?logo=hibernate&logoColor=white)

Aplicação de gerenciamento de livros desenvolvida em **Java** com **Spring Boot** e interface web MVC usando **Thymeleaf**.

![Vídeo descritivo do sistema](https://github.com/pauloviktwr/CrudSpringBoot-livros/assets/127359543/df975e71-b652-4c05-ad39-d5473330b22d)


---

## Objetivo

Demonstrar competências de desenvolvedor Java em:
- Arquitetura em camadas: `Controller`, `Service`, `Repository`, `DTO`
- Construção de API REST com `@RestController`
- Validação de dados com Jakarta Bean Validation
- Persistência com JPA/Hibernate
- Interface web MVC com Thymeleaf
- MySQL (produção)
- H2 (testes)
- Maven
- Testes unitários com JUnit 5 e Mockito
- Tratamento global de exceções
- Observabilidade (Actuator + Micrometer)
- Mensageria (RabbitMQ / Kafka)
- Microserviços / arquitetura distribuída como evolução natural do projeto
- Containerização (Docker) e receitas para CI/CD (GitHub Actions)

---

## 1) Arquitetura e Padrões (resumo)
- Camadas: `Controller` → `Service` → `Repository` (Spring Boot + Spring Data JPA)
- DTOs para entrada; entidades limitadas à persistência
- Design alinhado a princípios de microserviços: APIs REST desacopladas, domínio encapsulado e integração por mensageria
- Tratamento de exceções centralizado com `@RestControllerAdvice` e `ResponseStatusException`
- Transações declarativas com `@Transactional`
- MVC: camada de apresentação implementada seguindo o padrão MVC (Controllers + Views/Thymeleaf)

---

## 2) Aderência a Requisitos Técnicos (concluído vs roadmap)
| Requisito | Status | Próxima ação (técnica) |
|---|---:|---|
| Java, POO | Concluído | — |
| Spring Boot | Concluído | — |
| APIs REST (HTTP/JSON) | Concluído | — |
| Git | Concluído | — |
| Relacional (SQL) | Concluído | — |
| Clean Code / Patterns | Parcial | Refatorar DTO/Entity separação onde necessário |
| Testes unitários (JUnit/Mockito) | Concluído | Expandir coverage para cenários críticos (Service/Controller) |
| Mensageria (RabbitMQ/Kafka) | Gap | Adicionar `spring-boot-starter-amqp` + producer/consumer (Sprint 2) |
| Observabilidade (métricas/logs/tracing) | Gap | Adicionar Micrometer + Actuator + Prometheus scrape endpoint |
| Docker / Compose | Gap | Criar `Dockerfile` multi-stage e `docker-compose.yml` com RabbitMQ (Sprint 2) |
| CI/CD | Gap | Criar GitHub Actions com JDK21 + testes |

---

## 3) Como Executar (build e run)

Para começar de forma simples, a melhor opção é usar o perfil local com H2. Assim você consegue rodar a aplicação sem precisar configurar MySQL logo no início.

### Pré-requisitos
- Java 21 instalado e configurado no seu ambiente
- Git instalado
- Opcional: Docker Desktop, se você quiser rodar o banco MySQL com o Docker Compose

### Passo 1: Compilar o projeto
No Windows:
```powershell
mvnw.cmd clean package
```

No macOS/Linux:
```bash
./mvnw clean package
```

Esse comando compila o projeto, executa os testes e gera o arquivo JAR dentro da pasta target.

### Passo 2: Rodar a aplicação localmente
Para uma execução rápida e sem dependências extras, use o perfil local:

No Windows:
```powershell
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=local
```

No macOS/Linux:
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

Esse perfil usa o banco H2 em memória, então você não precisa configurar MySQL para testar a aplicação.

### Passo 3: Abrir a aplicação
Depois que o Spring Boot iniciar, acesse:
- http://localhost:8080/livros

### Passo 4: Executar os testes
```bash
./mvnw test
```

### Se quiser usar MySQL em vez de H2
Se preferir rodar com o banco MySQL, primeiro inicie o ambiente com Docker:
```bash
docker compose up -d mysql
```

Em seguida, rode:
```bash
./mvnw spring-boot:run
```

### Executar o JAR gerado
```bash
java -jar target/livros-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

---

## 4) Microserviços e Evolução Distribuída
- Projeto construído com APIs REST bem definidas e separação de camadas que facilita a futura decomposição em microsserviços.
- A integração via mensageria (RabbitMQ / Kafka) e a observabilidade com Actuator/Micrometer são pilares para um ambiente distribuído confiável.
- Próximo passo: extrair o módulo de livros em um serviço independente e manter a comunicação entre serviços por eventos e APIs HTTP.

## 5) Suíte de Testes
- Frameworks: JUnit 5, Mockito, AssertJ
- Arquivos principais:
	- `src/test/java/com/portfolio/livros/service/LivroServiceTest.java`
	- `src/test/java/com/portfolio/livros/controller/LivroRestControllerTest.java`
	- `src/test/java/com/portfolio/livros/infra/exception/GlobalExceptionHandlerTest.java`
- Execução:
```bash
mvn test
```

## 📊 Gestão de Projeto & Metodologia Ágil

Projeto gerenciado utilizando práticas de **Scrum** através do **Jira**.

### 🗺️ Visão do Épico e Planejamento da Sprint
As entregas são organizadas em Épicos de arquitetura e estimadas utilizando *Story Points* para mensurar a complexidade e a capacidade de entrega de cada ciclo de desenvolvimento.

**Épico Atual:** `LAP-EPIC-1: Estruturação de Infraestrutura, Mensageria e Observabilidade`

| Código da Issue | Tipo de Item | Épico Relacionado | Descrição da Funcionalidade / Infraestrutura | Estimativa (Points) |
| :--- | :--- | :--- | :--- | :---: |
| 🟢 **LAP-1** | `Feature` | `LAP-EPIC-1` | Implementação do Message Broker com RabbitMQ | 8 pts |
| 🟢 **LAP-2** | `Feature` | `LAP-EPIC-1` | Configuração de métricas e monitoramento com Prometheus | 5 pts |
| 🛠️ **LAP-3** | `Infra` | `LAP-EPIC-1` | Conteinerização do ambiente de desenvolvimento com Docker | 5 pts |
| | | | **Capacidade Total da Sprint:** | **18 pts** |

---

### 🔄 Fluxo de Trabalho (Workflow & Git Branching)
Git Flow integrado dsiretamente aos status das tarefas no Jira, garantindo a rastreabilidade do código e a estabilidade das branches.

```text
 [ Backlog ] ──> [ In Progress ] ──> [ Code Review ] ──> [ Done ]
                       │                     │              │
                (Criação de Branch)    (Abertura de PR)   (Merge)

---