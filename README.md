# Assessment Java - Project Management System

A robust backend application developed with **Java 17** and **Spring Boot 3** for managing projects and tasks. This project implements a **Hexagonal Architecture** (Ports & Adapters) to decouple business logic from infrastructure details, ensuring maintainability and scalability.

## Technology Stack

*   **Core**: Java 17, Spring Boot 3.2.1
*   **Database**: PostgreSQL
*   **Database Migration**: Flyway
*   **Security**: Spring Security, JWT (Stateless)
*   **API Documentation**: Swagger / OpenAPI 3
*   **Testing**: JUnit 5, Mockito
*   **Containerization**: Docker, Docker Compose
*   **Frontend**: HTML5, Bootstrap 5, Vanilla JS

## Architectural Decisions

The project follows strict **Hexagonal Architecture** principles:
*   **Domain Layer**: Contains pure business entities (`User`, `Project`, `Task`) with no framework dependencies.
*   **Application Layer**: Defines `UseCases` (Input Ports) and Interfaces for external communication (Output Ports). Implementation of business rules lies here.
*   **Infrastructure Layer**: Implements the adapters (REST Controllers, JPA Repositories, Security Filters, Email Notifications).
*   **Dependency Injection**: Spring Boot is used as the glue to wire adapters to ports.

## Technical decisions

The project was built in the following strategic phases:

1.  **Application Core**: Defined the domain models and implemented the core business logic (Use Cases for Projects and Tasks).
2.  **Persistence Infrastructure**: Configured PostgreSQL with Spring Data JPA and defined entities/repositories.
3.  **Security Implementation**: Integrated Spring Security with JWT Authentication (`JwtAuthFilter`, `CustomUserDetailsService`) to secure endpoints.
4.  **REST API Exposition**: Developed REST Controllers (`AuthController`, `ProjectController`, `TaskController`) with Global Exception Handling.
5.  **Unit Testing**: Implemented unit tests using Mockito to validate business rules (`ActivateProject`, `CompleteTask`).
6.  **Frontend Module**: Created a responsive HTML/JS Dashboard to interact with the API (Login, Create Projects, Manage Tasks).
7.  **Containerization**: Created a multi-stage `Dockerfile` and `docker-compose.yml` for orchestration.
8.  **Documentation & Polish**: Integrated Swagger UI, configured Flyway for data migration, and finalized documentation.

## Getting Started (Docker)

The easiest way to run the application is using Docker Compose. This will set up the Backend and PostgreSQL database automatically.

**Prerequisites**: Docker & Docker Compose.

```bash
# Clone the repository
git clone <repo-url>
cd AssesmentJava

# Run with Docker Compose (builds app and starts DB)
# Note: Use -v to clean volumes if you want to reset the database fresh
docker compose down -v 
docker compose up -d --build
```

### Access Points
*   **Frontend Dashboard**: [http://localhost:8080/](http://localhost:8080/)
*   **Swagger API Docs**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Test Data (Flyway Migrations)

On the first run, **Flyway** will automatically execute migration scripts to:
1.  Create the database schema (`users`, `projects`, `tasks`).
2.  Establish foreign key relationships.
3.  **Seed the database with test data**.

You can use the following pre-generated accounts to login:

| Username | Password | Data |
|----------|----------|------|
| **user1** | `secret123` | 7 Projects, 35 Tasks |
| **user2** | `secret123` | 7 Projects, 35 Tasks |
| **user3** | `secret123` | 7 Projects, 35 Tasks |

Note: The test data includes a mix of active projects and completed/pending tasks randomized for testing purposes.

## Running Tests

To run the unit tests manually (requires Java installed):

```bash
./mvnw test
```

## Project Structure

```
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── riwi
    │   │           └── assesmentjava
    │   │               ├── application
    │   │               │   ├── dto
    │   │               │   │   ├── CreateProjectCommand.java
    │   │               │   │   ├── CreateTaskCommand.java
    │   │               │   │   ├── ProjectDTO.java
    │   │               │   │   └── TaskDTO.java
    │   │               │   ├── exception
    │   │               │   │   ├── BusinessRuleException.java
    │   │               │   │   ├── EntityNotFoundException.java
    │   │               │   │   └── UnauthorizedAccessException.java
    │   │               │   ├── ports
    │   │               │   │   ├── in
    │   │               │   │   │   ├── ActivateProjectUseCase.java
    │   │               │   │   │   ├── CompleteTaskUseCase.java
    │   │               │   │   │   ├── CreateProjectUseCase.java
    │   │               │   │   │   ├── CreateTaskUseCase.java
    │   │               │   │   │   ├── GetProjectTasksUseCase.java
    │   │               │   │   │   ├── GetUserProjectsUseCase.java
    │   │               │   │   │   └── RegisterUserUseCase.java
    │   │               │   │   └── out
    │   │               │   │       ├── AuditLogPort.java
    │   │               │   │       ├── CurrentUserPort.java
    │   │               │   │       ├── NotificationPort.java
    │   │               │   │       ├── ProjectRepositoryPort.java
    │   │               │   │       ├── TaskRepositoryPort.java
    │   │               │   │       └── UserRepositoryPort.java
    │   │               │   └── usecases
    │   │               │       ├── ActivateProjectUseCaseImpl.java
    │   │               │       ├── CompleteTaskUseCaseImpl.java
    │   │               │       ├── CreateProjectUseCaseImpl.java
    │   │               │       ├── CreateTaskUseCaseImpl.java
    │   │               │       ├── GetProjectTasksUseCaseImpl.java
    │   │               │       ├── GetUserProjectsUseCaseImpl.java
    │   │               │       └── RegisterUserUseCaseImpl.java
    │   │               ├── AssesmentJavaApplication.java
    │   │               ├── domain
    │   │               │   └── model
    │   │               │       ├── Proyect.java
    │   │               │       ├── Tasks.java
    │   │               │       └── User.java
    │   │               └── infrastructure
    │   │                   ├── adapters
    │   │                   │   ├── in
    │   │                   │   │   └── rest
    │   │                   │   │       ├── AuthController.java
    │   │                   │   │       ├── dto
    │   │                   │   │       │   ├── AuthResponse.java
    │   │                   │   │       │   ├── ErrorResponse.java
    │   │                   │   │       │   ├── LoginRequest.java
    │   │                   │   │       │   └── RegisterRequest.java
    │   │                   │   │       ├── exception
    │   │                   │   │       │   └── GlobalExceptionHandler.java
    │   │                   │   │       ├── ProjectController.java
    │   │                   │   │       └── TaskController.java
    │   │                   │   └── out
    │   │                   │       ├── audit
    │   │                   │       │   └── AuditLogAdapter.java
    │   │                   │       ├── notification
    │   │                   │       │   └── NotificationAdapter.java
    │   │                   │       ├── persistence
    │   │                   │       │   ├── adapters
    │   │                   │       │   │   ├── ProjectRepositoryAdapter.java
    │   │                   │       │   │   ├── TaskRepositoryAdapter.java
    │   │                   │       │   │   └── UserRepositoryAdapter.java
    │   │                   │       │   ├── entities
    │   │                   │       │   │   ├── ProjectEntity.java
    │   │                   │       │   │   ├── TaskEntity.java
    │   │                   │       │   │   └── UserEntity.java
    │   │                   │       │   ├── mappers
    │   │                   │       │   │   ├── ProjectMapper.java
    │   │                   │       │   │   ├── TaskMapper.java
    │   │                   │       │   │   └── UserMapper.java
    │   │                   │       │   └── repositories
    │   │                   │       │       ├── ProjectJpaRepository.java
    │   │                   │       │       ├── TaskJpaRepository.java
    │   │                   │       │       └── UserJpaRepository.java
    │   │                   │       └── security
    │   │                   │           ├── CurrentUserAdapter.java
    │   │                   │           ├── CustomUserDetailsService.java
    │   │                   │           └── jwt
    │   │                   │               ├── JwtAuthFilter.java
    │   │                   │               └── JwtUtil.java
    │   │                   └── config
    │   │                       ├── ApplicationConfig.java
    │   │                       ├── SecurityConfig.java
    │   │                       └── SwaggerConfig.java
    │   └── resources
    │       ├── application.yml
    │       ├── db
    │       │   └── migration
    │       │       ├── V1__Create_Tables.sql
    │       │       ├── V2__Add_Relations.sql
    │       │       └── V3__Insert_Test_Data.sql
    │       ├── static
    │       │   ├── dashboard.html
    │       │   └── index.html
    │       └── templates
    └── test
        └── java
            └── com
                └── riwi
                    └── assesmentjava
                        ├── application
                        │   └── usecases
                        │       └── UseCaseTests.java
                        └── AssesmentJavaApplicationTests.java
```
