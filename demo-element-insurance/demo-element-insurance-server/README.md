# Element Insurance Server Module

This module implements the backend REST API for the Element Insurance application
using Spring Boot and Spring Data REST.

## Overview

The server module provides a RESTful API for managing insurance types and quotations.
It uses Spring Data REST to automatically expose CRUD operations for the domain entities,
following the HAL (Hypertext Application Language) standard for hypermedia-driven APIs.

## Features

- RESTful API for insurance types and quotations
- Automatic CRUD operations using Spring Data REST
- HAL Explorer for API documentation and testing
- Validation for entity properties
- Cross-origin resource sharing (CORS) support

## Domain Model

The application has two main entities:

1. **InsuranceType**: Represents a type of insurance product with the following properties:
    - id: Unique identifier
    - product: Name of the insurance product
    - minCoverage: Minimum coverage amount
    - maxCoverage: Maximum coverage amount
    - risk: Risk percentage (0-100)

2. **Quotation**: Represents an insurance quote with the following properties:
    - id: Unique identifier
    - price: Price of the quotation
    - value: Value of the quotation
    - insuranceType: Reference to the associated insurance type

## Prerequisites

* Apache Maven 3.6.0
* HSQLDB database (provided by the demo-element-insurance-db module)
* JDK 11

## Dependencies

Before running the server, you must install the **SpringBloom.dev** Project in your local environment:

```bash
$ git clone https://github.com/desiderati/springbloom.git

$ cd springbloom

$ mvn clean install
```

## Building and Running the Server

### Building the Project

```bash
$ git clone https://github.com/desiderati/demos.git

$ cd demos/demo-element-insurance/demo-element-insurance-server

$ mvn clean install
```

### Running the Server

```bash
$ java -Xms256m -Xmx512m -jar target/demo-element-insurance-server-0.0.1-SNAPSHOT-exec.war
```

## API Endpoints

The server exposes the following main endpoints:

- `/element-insurance/api/insurancetypes`: CRUD operations for insurance types
- `/element-insurance/api/quotations`: CRUD operations for quotations
- `/element-insurance/api/explorer`: HAL Explorer for browsing and testing the API

## Using the Application

Access the application at:

```
http://localhost:9090/element-insurance/
```

## API Documentation

You can explore and test the API using the HAL Explorer at:

```
http://localhost:9090/element-insurance/api/explorer
```

## Configuration

The server is configured to run on port 9090 with the context path `/element-insurance`.
These settings can be modified in the application properties file.

## Troubleshooting

If you encounter issues with the server:

1. Ensure the database is running (see `demo-element-insurance-db` module)
2. Check the server logs for error messages
3. Verify the database connection settings
4. Make sure all required dependencies are installed
