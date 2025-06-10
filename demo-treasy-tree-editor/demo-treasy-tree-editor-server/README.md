# Treasy Tree Editor - Backend Server

## Overview

This module provides the backend server component for the Treasy Tree Editor application. It's built with Java and
Spring Boot, offering a RESTful API for managing tree data structures. The server connects to the MySQL database and
provides endpoints for creating, reading, updating, and deleting tree nodes.

## Features

* RESTful API for tree data management
* Spring Boot architecture for a robust enterprise application
* Swagger UI for API documentation and testing
* Docker support for containerized deployment
* Connection to MySQL database for data persistence

## Prerequisites

* JDK 21
* Apache Maven 3.6.0
* MySQL database (see [treasy-tree-editor-mysql](../demo-treasy-tree-editor-mysql/README.md))

## Dependencies

Before running the project, you must install the SpringBloom.dev Project in your local environment:

```bash
git clone https://github.com/desiderati/springbloom.git

cd springbloom

mvn clean install
```

## Running the Project from Scratch

### Option 1: Using Maven and Java

```bash
git clone https://github.com/desiderati/demos.git

cd demos/demo-treasy-tree-editor/demo-treasy-tree-editor-server

mvn clean install

java -Xms256m -Xmx512m -jar target/demo-treasy-tree-editor-server-0.0.1-SNAPSHOT-exec.jar
```

### Option 2: Using Docker Compose (Recommended)

```bash
docker-compose up -d && docker-compose logs -f
```

### Option 3: Using Docker Directly

```bash
# Get your user ID
id -u ${USER}
# Example output: 1000

# Run the Docker container
docker run -it -p 9090:9090 --rm \
  -e LOCAL_USER_ID=1000 \
  -e SPRING_APPLICATION_JSON='{"spring.datasource.url":"jdbc:mysql://172.17.0.1:3306/treasy"}' \
  desiderati/treasy-tree-editor-server:latest
```

## Building the Docker Image

If you want to build and push your own Docker image:

```bash
docker build -t desiderati/treasy-tree-editor-server:latest .

docker push desiderati/treasy-tree-editor-server:latest
```

## API Documentation and Testing

The server includes Swagger UI for API documentation and testing. Once the server is running, you can access it at:

```
http://localhost:9090/treasy-tree-editor/swagger-ui.html
```

## Available API Endpoints

The server provides the following main endpoints:

* `GET /api/trees` - List all trees
* `GET /api/trees/{id}` - Get a specific tree by ID
* `POST /api/trees` - Create a new tree
* `PUT /api/trees/{id}` - Update a tree
* `DELETE /api/trees/{id}` - Delete a tree

For a complete list of endpoints and their parameters, refer to the Swagger UI documentation.

## Configuration

The server can be configured using environment variables or application properties. Key configuration options include:

* `spring.datasource.url` - Database connection URL
* `spring.datasource.username` - Database username
* `spring.datasource.password` - Database password
* `server.port` - Server port (default: 9090)

## Troubleshooting

If you encounter issues:

1. Ensure the MySQL database is running and accessible
2. Check the server logs for error messages
3. Verify that port 9090 is not being used by another service
4. Ensure all prerequisites are installed correctly
