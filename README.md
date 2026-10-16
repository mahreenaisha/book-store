# Book Store REST API

A Spring Boot RESTful API service for managing a bookstore inventory, backed by MongoDB.

---

## 📌 Table of Contents
- [Overview](#overview)
- [Architecture & Tech Stack](#architecture--tech-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [1. Clone Repository](#1-clone-repository)
  - [2. MongoDB Setup](#2-mongodb-setup)
  - [3. Run the Application](#3-run-the-application)
- [API Documentation](#api-documentation)
  - [Base URL](#base-url)
  - [Endpoints](#endpoints)
  - [Sample Requests & Responses](#sample-requests--responses)
- [Exception Handling](#exception-handling)
- [Testing](#testing)
- [License](#license)

---

## 📖 Overview

The **Book Store** application provides CRUD (Create, Read, Update, Delete) operations for books. It uses a clean layered architecture with DTOs, mappers, services, custom repository queries, and global exception handling.

---

## 🛠 Architecture & Tech Stack

- **Java**: 25
- **Framework**: Spring Boot 3.5.7
- **Database**: MongoDB (via `spring-boot-starter-data-mongodb`)
- **Web**: Spring Web MVC (`spring-boot-starter-web`)
- **Containerization / Development**: Docker Compose (`spring-boot-docker-compose`, `compose.yaml`)
- **Build Tool**: Apache Maven (wrapper included: `mvnw` / `mvnw.cmd`)

---

## 📂 Project Structure

```text
book-store/
├── compose.yaml                                 # Docker Compose config for MongoDB
├── pom.xml                                      # Maven dependencies and configuration
├── src/
│   ├── main/
│   │   ├── java/com/mahreen/book_store/
│   │   │   ├── BookStoreApplication.java        # Main Spring Boot entry point
│   │   │   ├── controller/
│   │   │   │   └── BookController.java          # REST Controller endpoints
│   │   │   ├── dto/
│   │   │   │   └── BookDto.java                 # Record DTO for API contracts
│   │   │   ├── entity/
│   │   │   │   └── Book.java                    # MongoDB entity record
│   │   │   ├── exception/
│   │   │   │   ├── BookNotFoundException.java   # Custom not found runtime exception
│   │   │   │   └── GlobalExceptionHandler.java  # ControllerAdvice for error responses
│   │   │   ├── mapper/
│   │   │   │   └── BookMapper.java              # Conversion between Entity and DTO
│   │   │   ├── repository/
│   │   │   │   └── BookRepository.java          # Spring Data MongoDB repository
│   │   │   └── service/
│   │   │       ├── BookService.java             # Service interface
│   │   │       └── impl/
│   │   │           └── BookServiceImpl.java     # Business logic implementation
│   │   └── resources/
│   │       └── application.properties           # Application configuration
│   └── test/
│       └── java/com/mahreen/book_store/
│           └── BookStoreApplicationTests.java   # Context load tests
└── README.md
```

---

## ⚙️ Prerequisites

- **Java Development Kit (JDK)**: Java 25 or compatible version installed and configured (`JAVA_HOME`).
- **Docker & Docker Compose** (optional, recommended for running MongoDB locally).
- **Git**

---

## 🚀 Getting Started

### 1. Clone Repository

```bash
git clone https://github.com/mahreenaisha/book-store.git
cd book-store
```

### 2. MongoDB Setup

You can run MongoDB using Docker Compose:

```bash
docker compose up -d
```

> **Default credentials in [`compose.yaml`](compose.yaml):**
> - Database: `mydatabase`
> - Username: `root`
> - Password: `secret`
> - Port: `27017`

*Note: If `spring-boot-docker-compose` is enabled, Spring Boot will automatically manage the container during application startup when Docker Desktop / daemon is active.*

### 3. Run the Application

Using Maven wrapper on Windows (PowerShell / Command Prompt):

```powershell
.\mvnw.cmd spring-boot:run
```

Or on Linux / macOS:

```bash
./mvnw spring-boot:run
```

The server starts by default on port `8080`.

---

## 📡 API Documentation

### Base URL
```
http://localhost:8080/book-store
```

### Endpoints

| HTTP Method | Endpoint | Description | Request Body | Status Code |
| :--- | :--- | :--- | :--- | :--- |
| `GET` | `/book-store/` | Get all books | *None* | `200 OK` |
| `GET` | `/book-store/{bookId}` | Get a single book by ID | *None* | `200 OK` / `404 Not Found` |
| `POST` | `/book-store/` | Add a new book | JSON (`BookDto`) | `200 OK` |
| `PUT` | `/book-store/` | Update book name | JSON (`BookDto`) | `200 OK` / `404 Not Found` |
| `DELETE` | `/book-store/{bookId}` | Delete a book by ID | *None* | `200 OK` / `404 Not Found` |

---

### Sample Requests & Responses

#### 1. Create a Book
- **Endpoint**: `POST /book-store/`
- **Request Body**:
```json
{
  "bookId": "101",
  "name": "Atomic Habits",
  "price": "20.00",
  "author": "James Clear",
  "description": "An easy and proven way to build good habits and break bad ones."
}
```
- **Response** (`200 OK`):
```json
{
  "bookId": "101",
  "name": "Atomic Habits",
  "price": "20.00",
  "author": "James Clear",
  "description": "An easy and proven way to build good habits and break bad ones."
}
```

#### 2. Get All Books
- **Endpoint**: `GET /book-store/`
- **Response** (`200 OK`):
```json
[
  {
    "bookId": "101",
    "name": "Atomic Habits",
    "price": "20.00",
    "author": "James Clear",
    "description": "An easy and proven way to build good habits and break bad ones."
  }
]
```

#### 3. Get Book by ID
- **Endpoint**: `GET /book-store/101`
- **Response** (`200 OK`):
```json
{
  "bookId": "101",
  "name": "Atomic Habits",
  "price": "20.00",
  "author": "James Clear",
  "description": "An easy and proven way to build good habits and break bad ones."
}
```

#### 4. Update Book Name
- **Endpoint**: `PUT /book-store/`
- **Request Body**:
```json
{
  "bookId": "101",
  "name": "Atomic Habits (Hardcover Edition)",
  "price": "20.00",
  "author": "James Clear",
  "description": "An easy and proven way to build good habits and break bad ones."
}
```
- **Response** (`200 OK`):
```json
{
  "bookId": "101",
  "name": "Atomic Habits (Hardcover Edition)",
  "price": "20.00",
  "author": "James Clear",
  "description": "An easy and proven way to build good habits and break bad ones."
}
```

#### 5. Delete a Book
- **Endpoint**: `DELETE /book-store/101`
- **Response** (`200 OK`):
```text
Book Deleted Successfully
```

---

## ⚠️ Exception Handling

The application includes centralized error handling via [`GlobalExceptionHandler`](src/main/java/com/mahreen/book_store/exception/GlobalExceptionHandler.java):

- **`BookNotFoundException`**: Returns HTTP `404 Not Found` with the error message when a requested book is not found or when searching with an empty inventory.
- **Generic `Exception`**: Returns HTTP `500 Internal Server Error` with details if an unexpected error occurs.

---

## 🧪 Testing

Run test suites using the Maven wrapper:

```powershell
.\mvnw.cmd test
```
*(or `./mvnw test` on Linux / macOS)*

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
