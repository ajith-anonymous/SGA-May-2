# Orders & Customers Management System

A Spring Boot web application to manage **Orders** and **Customers** using JPA, JSP, and H2 in-memory database.

## Tech Stack

- **Spring Boot 3.2.5** (Spring MVC, Spring Data JPA)
- **H2 Database** (In-memory)
- **JSP + JSTL** (View layer)
- **JUnit 5 + Mockito** (Testing)
- **Maven** (Build tool)
- **Java 17**

## Entity Relationship

```
Customer (1) ──── (∞) Order
```

- **Customer**: id, name, email, phone, address
- **Order**: id, productName, quantity, totalPrice, orderDate, status, customer_id (FK)

## Project Structure

```
src/main/java/com/project
├── entity/           → JPA entity classes (Customer, Order)
├── repository/       → JpaRepository interfaces with custom queries
├── service/          → Business logic layer
├── controller/       → Spring MVC controllers

src/main/webapp/WEB-INF/jsp
├── home.jsp          → Landing page
├── customer-list.jsp → List all customers
├── customer-add.jsp  → Add customer form
├── customer-update.jsp → Update customer form
├── order-list.jsp    → List all orders (INNER JOIN)
├── order-add.jsp     → Add order form
├── order-update.jsp  → Update order form

src/main/resources
├── application.properties
├── data.sql          → Sample data (10 rows per table)

src/test/java/com/project
├── repository/RepositoryTests.java → Repository unit tests
├── service/ServiceTests.java       → Service unit tests (Mockito)
```

## How to Run

```bash
# Clone the repository
git clone <repo-url>
cd SGA-May-2

# Build and run
mvn spring-boot:run

# Open in browser
http://localhost:8080
```

## Features

- ✅ Create, Read, Update operations for Customers & Orders
- ✅ INNER JOIN custom query between Orders and Customers
- ✅ Form validation with error messages
- ✅ Exception handling for data integrity violations
- ✅ 10 sample records pre-populated in each table
- ✅ Modern glassmorphism UI with CSS styling
- ✅ Unit tests with JUnit 5 and Mockito
