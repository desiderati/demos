# Treasy Tree Editor

## Overview

This project demonstrates a complete tree management solution that allows users to insert, update, and delete nodes in a
hierarchical tree structure. It's built using a modern microservices architecture with separate components for the
database, backend server, and frontend interface.

## Project Architecture

The project consists of three main modules:

1. **MySQL Database** (`demo-treasy-tree-editor-mysql`): Stores the tree structure data.
2. **Backend Server** (`demo-treasy-tree-editor-server`): Java Spring Boot application that provides REST APIs for tree
   operations.
3. **Frontend Application** (`demo-treasy-tree-editor-static`): Web interface built with modern JavaScript frameworks
   for user interaction.

## Prerequisites

* JDK 21
* Node v12.9.1
* NPM v6.10.2
* Apache Maven 3.6.0
* Docker and Docker Compose (optional, for containerized deployment)

## Dependencies

Before running the project, you must install the **SpringBloom.dev** project in your local environment:

```bash
git clone https://github.com/desiderati/springbloom.git

cd springbloom

mvn clean install
```

## Getting Started

You can run each component separately by following the instructions in their respective README files:

1. [Database Setup (MySQL)](demo-treasy-tree-editor-mysql/README.md)
2. [Backend Server](demo-treasy-tree-editor-server/README.md)
3. [Frontend Application](demo-treasy-tree-editor-static/README.md)

## Running the Complete Application

For the best experience, start the components in the following order:

1. Start the MySQL database
2. Start the backend server
3. Start the frontend application

Once all components are running, you can access the application at http://localhost:8000

## Features

* Create, read, update, and delete tree nodes
* Drag and drop interface for tree manipulation
* RESTful API for programmatic access
* Persistent storage with MySQL

## Docker Support

All components support Docker deployment. See individual component READMEs for details.

## License

This project is licensed under the MIT License.
