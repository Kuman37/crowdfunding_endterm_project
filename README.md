# Crowdfunding Platform API

## Project Overview
This project is a **Spring Boot RESTful API** for a crowdfunding platform, built as part of the final assignment for an advanced Java course. It demonstrates the application of **SOLID principles**, **advanced OOP features**, **design patterns** (Singleton, Factory, Builder), **component principles** (REP, CCP, CRP), and a **clean layered architecture** (Controller → Service → Repository → Database). The system supports creating users, projects, pledges, and payments, with full CRUD operations and validation.
## SOLID Principles Documentation
- **SRP**: Each class has single responsibility (Controller, Service, Repository)
- **OCP**: BaseEntity allows extension without modification
- **LSP**: All subclasses substitutable for BaseEntity
- **ISP**: Narrow interfaces (Validatable, Printable, Searchable)
- **DIP**: Dependency injection through constructor

## Advanced OOP Features
- **Generics**: GenericRepository<T>, SortingUtils
- **Lambdas**: Sorting, filtering, comparators
- **Reflection**: ReflectionUtils for runtime inspection
- **Interface Features**: Default and static methods

## Design Patterns
- **Singleton**: DatabaseConfig
- **Factory**: EntityFactory
- **Builder**: ProjectBuilder

## Database Schema
<img width="864" height="879" alt="uml" src="https://github.com/user-attachments/assets/0d7f8bb6-86b6-431d-897d-5719712e9eca" />



## Execution Instructions
1. Install Java 25, Maven, PostgreSQL
2. Create database: `crowdfunding`
3. Update application.properties
4. Run: `mvn spring-boot:run`
5. Access: http://localhost:8080/api/projects

## Screenshots
<img width="1920" height="1080" alt="postman_output" src="https://github.com/user-attachments/assets/ba344ee0-0ea4-490f-bfcd-cad22243e76c" />


## Reflection
SOLID principles are not just theoretical; applying them in a Spring Boot project leads to cleaner, maintainable code. Dependency Injection and interface segregation made the code easy to extend and test.

Advanced OOP features like generics, lambdas, and reflection are powerful tools for writing reusable and concise code. For example, SortingUtils with generics can be reused across any entity type.

Design patterns (Singleton, Factory, Builder) solved real problems: managing configuration, centralizing object creation, and building complex objects with many optional fields.

Layered architecture combined with Spring Boot simplifies development and enforces separation of concerns.
