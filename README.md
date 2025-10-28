# Banking Application - Spring Boot API with Angular UI

## 📋 Project Summary
A comprehensive enterprise-grade banking management system built with Spring Boot backend and Angular frontend. This application enables customers to create accounts, perform transactions, transfer funds, and manage their banking activities securely. The system features robust authentication, transaction history tracking, and an admin dashboard for account management. Designed following microservices principles and best practices for Java enterprise applications, it showcases production-ready architecture with layered design patterns.

## 🛠️ Tech Stack
**Backend (Java/Spring Boot):**
- Java 8+ - Core programming language
- Spring Boot 2.x - Application framework
- Spring Data JPA - ORM and database abstraction
- Spring Security - Authentication and authorization
- Spring Web MVC - RESTful API development
- Hibernate - JPA implementation
- MySQL/H2 Database - Relational database
- Maven - Dependency management and build tool
- JWT - Token-based authentication
- Lombok - Reducing boilerplate code
- Bean Validation - Input validation

**Frontend (Angular):**
- Angular 11+ - Component-based UI framework
- TypeScript - Type-safe JavaScript
- RxJS - Reactive programming
- Angular Router - Client-side routing
- Angular Forms - Form handling and validation
- Bootstrap - Responsive design
- HttpClient - HTTP communication with backend

**Tools & DevOps:**
- Git & GitHub - Version control
- Postman - API testing
- IntelliJ IDEA / Eclipse - IDE
- npm - Frontend package management
- Maven Wrapper - Build automation

## ✨ Main Features
- **User Authentication & Authorization:** Secure login/registration with JWT tokens and role-based access control
- **Account Management:** Create, view, and manage multiple bank accounts (Savings, Current)
- **Transaction Operations:** 
  - Deposit funds
  - Withdraw funds
  - Transfer money between accounts
  - View transaction history with date filters
- **Account Dashboard:** Real-time balance display and recent transactions
- **Admin Panel:** Manage user accounts, view all transactions, and system analytics
- **RESTful API:** Well-structured endpoints following REST principles
- **Exception Handling:** Global error handling with custom exception responses
- **Input Validation:** Backend validation using Bean Validation API
- **Responsive UI:** Mobile-friendly Angular interface with Bootstrap
- **Security Features:** Password encryption, CORS configuration, JWT token expiration

## 🏗️ Architecture and Workflow

**System Architecture:**
```
Angular Frontend (Port 4200)
       ↓
   HTTP/REST API
       ↓
Spring Boot Backend (Port 8080)
       ↓
   ┌─────────────────┐
   │  Controller Layer│  ← REST endpoints
   └────────┬────────┘
            ↓
   ┌────────────────┐
   │  Service Layer  │  ← Business logic
   └────────┬───────┘
            ↓
   ┌─────────────────┐
   │ Repository Layer│  ← Data access (JPA)
   └────────┬────────┘
            ↓
   MySQL/H2 Database
```

**Application Workflow:**

1. **Authentication Flow:**
   - User submits credentials → Spring Security validates → JWT token generated → Token stored in localStorage
   - Subsequent requests include JWT in Authorization header → Token validated by JWT filter

2. **Transaction Flow:**
   - User initiates transaction from Angular UI
   - HTTP request sent to Spring Boot REST endpoint
   - Controller validates request and delegates to Service layer
   - Service layer applies business rules (balance check, transaction limits)
   - Repository layer persists transaction via JPA/Hibernate
   - Database updated and response sent back to frontend
   - Angular updates UI reactively

**Database Schema:**
- **Users:** User credentials, roles (CUSTOMER, ADMIN), profile information
- **Accounts:** Account number, type, balance, user reference
- **Transactions:** Transaction ID, type (DEPOSIT/WITHDRAWAL/TRANSFER), amount, timestamp, account references

**Key Design Patterns:**
- **MVC Pattern:** Clear separation of Controller, Service, and Repository layers
- **DTO Pattern:** Data Transfer Objects for API communication
- **Repository Pattern:** Data access abstraction
- **Dependency Injection:** Spring IoC container for loose coupling
- **Builder Pattern:** Entity object construction

## 🚀 Setup Instructions

**Prerequisites:**
- Java 8 or higher (JDK 11 recommended)
- Node.js (v12+) and npm
- MySQL (or use embedded H2 for development)
- Maven (included via Maven Wrapper)

**Backend Setup:**

1. Clone the repository:
```bash
git clone https://github.com/Neelamgajbhiye/BankingApplication.git
cd BankingApplication
```

2. Configure database (MySQL):
```bash
# Edit src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/banking_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

3. Build and run the Spring Boot backend:
```bash
cd server
./mvnw clean install
./mvnw spring-boot:run
```

Backend will start on `http://localhost:8080`

**Frontend Setup:**

1. Navigate to client folder:
```bash
cd client
```

2. Install dependencies:
```bash
npm install
```

3. Configure API endpoint (if needed):
```typescript
// Edit src/environments/environment.ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};
```

4. Start Angular development server:
```bash
npm start
```

Frontend will be available at `http://localhost:4200`

**Testing:**
- Access the application at `http://localhost:4200`
- Register a new user or use test credentials (if provided)
- Create accounts and perform transactions

## 🎥 Demo
[Demo video/screenshots to be added]

## 💼 Why This Project Matters for Java Full-Stack Development

This Banking Application is a **flagship project** that demonstrates professional-level Java Full-Stack development expertise highly valued by recruiters:

**Enterprise Java Skills:**
- **Spring Framework Mastery:** Comprehensive use of Spring Boot, Spring Data JPA, Spring Security, and Spring MVC
- **RESTful API Design:** Production-ready REST endpoints with proper HTTP methods, status codes, and error handling
- **JPA/Hibernate ORM:** Entity relationships, lazy/eager loading, transaction management, and query optimization
- **Security Implementation:** JWT authentication, password encryption, role-based authorization, and CORS configuration
- **Layered Architecture:** Professional separation of concerns (Controller → Service → Repository → Entity)

**Full-Stack Integration:**
- **End-to-End Development:** Complete integration between Angular frontend and Spring Boot backend
- **API Contract Design:** Well-defined DTOs and API contracts between layers
- **State Management:** Proper handling of authentication state and user sessions
- **Error Handling:** Comprehensive exception handling on both frontend and backend

**Industry Best Practices:**
- **Design Patterns:** Implementation of industry-standard patterns (Repository, DTO, MVC, Dependency Injection)
- **Clean Code:** Modular, maintainable code following SOLID principles
- **Database Design:** Normalized schema with proper entity relationships and constraints
- **Build Tools:** Maven for dependency management and project structure

**Banking Domain Knowledge:**
- Demonstrates understanding of financial transactions, account management, and audit trails
- Implements critical features like transaction atomicity and balance consistency
- Shows awareness of security considerations in financial applications

For recruiters: This project proves my capability to build enterprise-level Java applications that can be deployed in production environments. The combination of Spring Boot backend expertise, Angular frontend skills, and understanding of system architecture makes me well-suited for Java Full-Stack Developer roles in banking, fintech, and enterprise software companies.
