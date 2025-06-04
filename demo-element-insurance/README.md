# Element Insurance

A comprehensive sample project demonstrating how to build a modern insurance quotation system using Spring Data REST
for backend CRUD operations and Angular for the frontend.

## Project Overview

Element Insurance is a demonstration project that showcases how to implement a complete web application
for managing insurance types and quotations.
The application allows users to:

- Create, read, update, and delete insurance types
- Create, read, update, and delete quotations
- Associate quotations with specific insurance types

## Architecture

The project follows a modular architecture with three main modules:

1. **Database Module** (`demo-element-insurance-db`): Provides the PostgreSQL database configuration using Docker.
2. **Server Module** (`demo-element-insurance-server`): Implements the backend REST API using Spring Boot
   and Spring Data REST.
3. **Static Module** (`demo-element-insurance-static`): Implements the frontend user interface using Angular.

## Technology Stack

### Backend

- Spring Boot
- Spring Data JPA
- Spring Data REST
- HAL Explorer for API documentation and testing
- PostgreSQL database

### Frontend

- Angular
- TypeScript
- HTML/CSS

## Domain Model

The application has two main entities:

1. **Insurance Type**: Represents a type of insurance product with properties like product name,
   minimum/maximum coverage, and risk percentage.
2. **Quotation**: Represents an insurance quote with properties like price, value, and associated insurance type.

## Prerequisites

* Angular 8
* Apache Maven 3.6.0
* Docker v27
* Docker Compose v2.28.1
* JDK 11
* Node v12.9.1
* NPM v6.10.2

## Dependencies

Before running the project, you must install the **SpringBloom.dev** Project in your local environment:

```bash
$ git clone https://github.com/desiderati/springbloom.git

$ cd springbloom

$ mvn clean install
```

## Running the Project

To run the complete application, follow these steps in order:

1. **Set up the database**:
    * [Database Setup Instructions](demo-element-insurance-db/README.md)

2. **Start the backend server**:
    * [Server Setup Instructions](demo-element-insurance-server/README.md)

3. **Launch the frontend application**:
    * [Frontend Setup Instructions](demo-element-insurance-static/README.md)

## API Documentation

Once the server is running, you can access the HAL Explorer to browse and test the REST API:

```
http://localhost:9090/element-insurance/api/explorer
```

## Project Structure

```
demo-element-insurance/
├── demo-element-insurance-db/      # Database configuration
├── demo-element-insurance-server/  # Backend server
│   ├── src/main/java/
│   │   └── br/tech/desiderati/demo/element/
│   │       ├── domain/             # Entity classes
│   │       ├── projection/         # REST projections
│   │       ├── repository/         # Spring Data repositories
│   │       └── WebApplication.java # Main application class
└── demo-element-insurance-static/  # Frontend application
```

## License

This project is licensed under the MIT License.
