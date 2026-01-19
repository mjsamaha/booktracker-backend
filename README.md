# Personal Book Tracker Backend

## Overview

This is the **backend component** of the Personal Book Tracker (PBT) – a minimal viable product (MVP) demonstrating full-stack CRUD operations, database integration, and a polished UI. The full application consists of:
- **Backend**: Spring Boot + Spring Data JPA + H2/MySQL (this repository)
- **Frontend**: Angular + PrimeNG (separate repository)

The goal is a lightweight app to track books you've read, are reading, or plan to read. It showcases modern web development practices with a clean, responsive interface.

## Features

### Book Management
- Add a new book (Title, Author, Genre, Status, Notes)
- Edit existing book details
- View book details in a modal or page
- List all books with search/filter by title or genre

### Status Tracking
- Track progress: `NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`

### MVP Scope
- ✅ No authentication required (single-user demo)
- RESTful API for full CRUD operations
- Database persistence with H2 (development) or MySQL (production)
- Validation and error handling
- CORS configuration for frontend integration

## Tech Stack

- **Backend**: Spring Boot 4.0.1 + Spring Data JPA + Spring Web MVC
- **Database**: H2 (file-based for development) / MySQL (optional)
- **Language**: Java 21
- **Build Tool**: Maven
- **Other**: Lombok, Validation, Actuator, DevTools

## Data Model

### Book Entity
```java
Book {
    Long id;                    // Auto-generated
    String title;               // Required
    String author;              // Required
    String genre;               // Required
    BookStatus status;          // NOT_STARTED / IN_PROGRESS / COMPLETED
    String notes;               // Optional, up to 1000 chars
    LocalDateTime createdAt;    // Auto-managed
    LocalDateTime updatedAt;    // Auto-managed
}
```

### BookStatus Enum
```java
enum BookStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED
}
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | List all books |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Create a new book |
| PUT | `/api/books/{id}` | Update an existing book |
| DELETE | `/api/books/{id}` | Delete a book by ID |

### API Examples

#### Get All Books
```bash
GET /api/books
```

#### Create a Book
```bash
POST /api/books
Content-Type: application/json

{
  "title": "The Great Gatsby",
  "author": "F. Scott Fitzgerald",
  "genre": "Fiction",
  "status": "COMPLETED",
  "notes": "A classic American novel"
}
```

#### Update a Book
```bash
PUT /api/books/1
Content-Type: application/json

{
  "title": "Updated Title",
  "author": "Updated Author",
  "genre": "Updated Genre",
  "status": "IN_PROGRESS",
  "notes": "Updated notes"
}
```

## Prerequisites

- Java 21 (JDK)
- Maven 3.6+
- Git

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/booktracker-backend.git
   cd booktracker-backend
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

## Running Locally

1. **Start the application**:
   ```bash
   mvn spring-boot:run
   ```

   The server will start on `http://localhost:8080`.

2. **Access H2 Database Console**:
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:file:./data/booktrackerdb`
   - Username: `sa`
   - Password: (leave blank)

3. **Test the API**:
   Use tools like Postman, curl, or your browser to interact with the endpoints.

   Example curl command:
   ```bash
   curl -X GET http://localhost:8080/api/books
   ```

## Configuration

### Database
- **Default (Development)**: H2 file-based database (`./data/booktrackerdb.mv.db`)
- **Production**: Switch to MySQL by updating `application.properties`:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/booktracker
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
  ```

### Application Properties
Located in `src/main/resources/application.properties`:
- Server port: Default 8080
- JPA settings: DDL auto-update, SQL logging enabled
- H2 console: Enabled for development

### CORS
Configured to allow requests from the Angular frontend (default: `http://localhost:4200`).

## Usage for Developers

1. **Local Development**:
   - Run the backend as described above.
   - Start the Angular frontend separately.
   - Use the H2 console to inspect database state.

2. **API Testing**:
   - Use Postman or similar to test CRUD operations.
   - Check logs for SQL queries (enabled in dev mode).

3. **Extending the API**:
   - Add new endpoints in `BookController.java`.
   - Implement business logic in `BookService.java`.
   - Update the entity or add new entities as needed.

4. **Database Migrations**:
   - JPA handles schema updates automatically in development.
   - For production, consider Flyway or Liquibase for migrations.

## Project Structure

```
src/
├── main/
│   ├── java/com/mjsamaha/booktracker_backend/
│   │   ├── BooktrackerBackendApplication.java  # Main app
│   │   ├── config/                             # Configuration classes
│   │   ├── controller/                         # REST controllers
│   │   ├── dto/                                # Request/Response DTOs
│   │   ├── models/                             # JPA entities
│   │   ├── repository/                         # JPA repositories
│   │   └── service/                            # Business logic
│   └── resources/
│       └── application.properties              # App config
└── test/                                       # Unit tests
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is for demonstration purposes. Feel free to use and modify as needed.
