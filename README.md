# Livros Web Application

![Java](https://img.shields.io/badge/Java-17+-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-green?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0+-brightgreen?logo=thymeleaf&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-yellow?logo=hibernate&logoColor=white)

![Vídeo descritivo do sistema](https://github.com/pauloviktwr/CrudSpringBoot-livros/assets/127359543/df975e71-b652-4c05-ad39-d5473330b22d)

---

## **Descrição**

O **Livros Web Application** é uma aplicação desenvolvida com Spring Boot projetada para o gerenciamento de acervos literários. O sistema renderiza interfaces dinâmicas no servidor utilizando Thymeleaf para interação do usuário, suportando o ciclo completo de operações CRUD (Create, Read, Update, Delete). O projeto adota uma arquitetura em camadas clara, garantindo a separação de responsabilidades e persistência em banco de dados relacional.

---

## **Tecnologias Utilizadas**

| Tecnologia | Função na Aplicação |
| :--- | :--- |
| **Java 17+** | Linguagem de programação principal do ecossistema backend. |
| **Spring Boot 3.x** | Framework base para configuração, injeção de dependências e servidor embutido. |
| **MySQL** | Banco de dados relacional para armazenamento persistente dos dados. |
| **Maven** | Gerenciador de dependências e automação do build do projeto. |
| **Thymeleaf** | Motor de templates para renderização dinâmica de páginas HTML no servidor. |
| **Hibernate (JPA)** | Framework de ORM para mapeamento de entidades e comunicação abstrata com o banco. |

---

## **Configuração e Instalação**

### Pré-requisitos
Antes de iniciar, certifique-se de possuir instalado:
- Java 17 ou superior
- Maven 3.6 ou superior
- MySQL Server 8.0 ou superior

### Como Executar a Aplicação

1. **Clone o repositório**

2. **Configure o banco de dados MySQL e atualize o arquivo application.properties em src/main/resources:**
```
spring.datasource.url=jdbc:mysql://localhost/meuslivros?createDatabaseIfNotExist=true
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
```

3. **Compile e execute o projeto via terminal:**
```
mvn clean install
mvn spring-boot:run
```
4. **Acesse o sistema no navegador:**

Interface Web: http://localhost:8080/livros

### **Funcionalidades**

Gerenciamento do Catálogo (CRUD): Cadastro, listagem, edição e exclusão de livros.

Tráfego Seguro de Dados: Uso de Records (DTOs) para entrada e saída de dados, evitando exposição de entidades.

Camada de Serviço Isolada: Lógica de negócios centralizada e desacoplada dos controladores web.

Validação Automatizada: Consistência de dados usando Bean Validation (@NotBlank, @Size).


## **Estrutura do Projeto**
```
src/
├── main/
│   ├── java/com/portfolio/livros/
│   │   ├── controller/      # Controladores da interface Web
│   │   ├── model/           # Entidades JPA e Repositórios
│   │   │   └── dto/         # DTOs (Records) de entrada de dados
│   │   ├── service/         # Camada de Regras de Negócio e Serviços
│   │   └── LivrosApplication.java
│   └── resources/
│       ├── static/css/      # Estilização da aplicação
│       ├── templates/       # Páginas HTML (Thymeleaf)
│       └── application.properties
└── test/                    # Estrutura de Testes Automatizados
```

## **Rotas e Endpoints da Aplicação**


| Método HTTP | Endpoint       | Descrição                  |
|-------------|----------------|------------------------------|
| GET         | /livros        | Exibe a listagem completa de livros cadastrados.    |
| GET         | /livros/formulario?id={id} | Carrega a página do formulário (Cadastro ou Edição se houver ID). |
| POST        | /livros        | Processa o envio dos dados para salvar um novo livro.               |
| PUT         | /livros        | Processa a atualização de um livro existente.      |
| DELETE      | /livros        | Remove um livro do catálogo com base no ID informado.          |

---

## **Linha do Tempo e Evolução do Projeto**

### Fases Concluídas

Arquitetura em Camadas (Service Layer): Implementação do LivroService, desacoplando completamente a lógica de persistência e validações do LivroController.

Refatoração de Segurança (DTOs): Criação de pacotes específicos de DTO utilizando Java Records para blindar a entrada de dados.

Testes Unitários da Camada Service: Cobertura de 100% da lógica de negócio do LivroService usando JUnit 5 e Mockito, validando caminhos felizes, lançamento de exceções custodiadas e controle de interações via verify().


#### 🎯 Próximas Tarefas e Roadmap de Testes

Abaixo estão listadas as atividades planejadas para consolidar a qualidade de software do projeto nas próximas etapas de desenvolvimento:
1. **Testes de Integração (Camada de Dados)**
```
[ ] Configurar um ambiente de banco de dados em memória isolado utilizando H2 Database em src/test/resources/application-test.properties.
[ ] Implementar testes de integração focados no LivroRepository.
[ ] Validar o ciclo de vida do Hibernate, restrições de integridade, anotações de mapeamento (@Column, @Table) e geração automática de IDs.
```
2. **Testes de Controladores (Camada Web/HTTP)**
```
[ ] Implementar testes de componentes utilizando @WebMvcTest e MockMvc.
[ ] Validar as respostas de requisições HTTP (Status 200 OK, 302 Redirect, 404 Not Found).
[ ] Testar as restrições do Bean Validation simulando o envio de DTOs inválidos nos payloads e verificando se os erros retornam corretamente para a View.
```
3. **Ajustes de Interface (View)**
```
[ ] Atualizar as telas em Thymeleaf para exibir as mensagens de erro customizadas interceptadas pelo framework no processo de validação.
```
## **Author**

Desenvolvido por Paulo Victor. Sinta-se à vontade para entrar em contato para dúvidas ou contribuições!