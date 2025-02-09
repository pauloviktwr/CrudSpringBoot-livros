# Livros Web Application

![Java](https://img.shields.io/badge/Java-17+-orange?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-green?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0+-brightgreen?logo=thymeleaf&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-yellow?logo=hibernate&logoColor=white)

![Vídeo sem título ‐ Feito com o Clipchamp (1) (1)](https://github.com/pauloviktwr/CrudSpringBoot-livros/assets/127359543/df975e71-b652-4c05-ad39-d5473330b22d)
---

## **Description**

The **Livros Web Application** is a Spring Boot-based project designed to manage books. It provides a server-rendered web interface using Thymeleaf for user interaction and supports CRUD operations (Create, Read, Update, Delete). The project uses MySQL as the database and follows a clean architecture with proper separation of concerns.

---

## **Technologies Used**

Below is a graphical representation of the technologies used in this project:

| Technology    | Purpose                                 |
|---------------|-----------------------------------------|
| ![Java](https://img.shields.io/badge/Java-17+-orange?logo=java&logoColor=white) | Backend programming language.        |
| ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-green?logo=springboot&logoColor=white) | Framework for application development. |
| ![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue?logo=mysql&logoColor=white) | Relational database for data storage. |
| ![Maven](https://img.shields.io/badge/Maven-3.6+-red?logo=apachemaven&logoColor=white) | Dependency management and build tool. |
| ![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0+-brightgreen?logo=thymeleaf&logoColor=white) | Template engine for dynamic HTML views. |
| ![Hibernate](https://img.shields.io/badge/Hibernate-ORM-yellow?logo=hibernate&logoColor=white) | ORM framework for database interaction.|

---

## **Setup Instructions**

### Prerequisites

Ensure you have the following installed:
- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Steps to Run the Project

1. Clone the repository:

git clone <repository-url>
cd <repository-folder>

2. Configure the database:
- Ensure MySQL is running.
- Update `application.properties` in `src/main/resources` with your database credentials:
  ```
  spring.datasource.url=jdbc:mysql://localhost/meuslivros?createDatabaseIfNotExist=true
  spring.datasource.username=<your-username>
  spring.datasource.password=<your-password>
  spring.jpa.hibernate.ddl-auto=update
  ```

3. Build and run the application:

mvn clean install
mvn spring-boot:run


4. Access the application:
- Web Interface: `http://localhost:8080/livros`

---

## **Features**

1. **Book Management**:
- Add, edit, delete, and list books.
2. **Dynamic Web Views**:
- Thymeleaf templates for forms and lists.
3. **Database Integration**:
- Persistent storage using MySQL.
4. **Clean Architecture**:
- Separation of concerns between controllers, services, and models.

---

## Project Structure
```
src/
├── main/
│ ├── java/com/portfolio/livros/
│ │ ├── controller/
│ │ │ └── LivroController.java
│ │ ├── model/
│ │ │ ├── DadosCadastraLivro.java
│ │ │ ├── DadosEditarLivro.java
│ │ │ ├── Livro.java
│ │ │ └── LivroRepository.java
│ │ └── LivrosApplication.java
│ ├── resources/
│ ├── static/css/
│ │ └── estilos.css
│ ├── templates/livros/
│ │ ├── formulario.html
│ │ ├── lista.html
│ │ └── template.html
│ └── application.properties
└── test/
└── java/com/portfolio/livros/
```


---

## **API Endpoints**

Although this is primarily a web application, some controller methods mimic API-like behavior.

| HTTP Method | Endpoint       | Description                  |
|-------------|----------------|------------------------------|
| GET         | /livros        | Display list of all books    |
| GET         | /livros/formulario?id={id} | Load form for editing a book |
| POST        | /livros        | Add a new book               |
| PUT         | /livros        | Update an existing book      |
| DELETE      | /livros        | Delete a book by ID          |

---

## **Project Timeline**

### Current Status: In Progress

#### Tasks and Milestones

1. **Creation of the Service Layer**
    - Status: ❌ Not Completed  
    - Activities:
      - Create `service` package under `com.portfolio.livros`.
      - Implement `LivroService` to centralize business logic (`save`, `edit`, `delete` methods).
      - Move data manipulation logic from `LivroController` to `LivroService`.

2. **Separation of Entities and DTOs**
    - Status: ❌ Not Completed  
    - Activities:
      - Create a subpackage `dto` under `model`.
      - Move `DadosCadastraLivro` and `DadosEditarLivro` to the new package.

3. **Review and Improvement of Validations**
    - Status: ❌ Not Completed  
    - Activities:
      - Add validations in DTOs using Bean Validation annotations (`@NotBlank`, `@Size`, etc.).
      - Ensure controller methods validate incoming data using `@Valid`.

4. **Organization and Improvement of Views**
    - Status: ❌ Not Completed  
    - Activities:
      - Review Thymeleaf templates for integration with controllers.
      - Add user-friendly error messages using Thymeleaf.

5. **Testing and Validation**
    - Status: ❌ Not Completed  
    - Activities:
      - Test CRUD functionality manually.
      - Create unit tests for service methods.

6. **Final Documentation**
    - Status: ❌ Not Completed  
    - Activities:
      - Update this README file with final project details.
---

## **Author**

Developed by Victor. Feel free to reach out for questions or contributions!
