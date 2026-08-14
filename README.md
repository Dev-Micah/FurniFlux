# Furniflux Backend API

A clean and scalable Spring Boot + Kotlin backend for ecommerce backend.

---

## Tech Stack

- Spring Boot with Kotlin

---

## Database Setup

This project requires a PostgreSQL database before the app can start.

### 1. Make sure PostgreSQL is running

```bash
# Linux/macOS
sudo systemctl status postgresql

# Or check if the server is ready
pg_isready
```

### 2. Create the database

```bash
psql -U postgres
CREATE DATABASE furniflux;
\q
```

Or in one line:

```bash
createdb -U postgres furniflux
```

### 3. Configure your connection

Ensure your `application.properties` matches your local setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/furniflux
spring.datasource.username=postgres
spring.datasource.password=your_password
```

> If you're using environment variables or a `.env` file, those will take precedence over `application.properties`.

---
