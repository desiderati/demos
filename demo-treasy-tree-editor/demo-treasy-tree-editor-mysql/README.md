# Treasy Tree Editor - MySQL Database

## Overview

This module provides the database component for the Treasy Tree Editor application. It uses MySQL to store the
hierarchical tree data structure and is configured with Docker for easy setup and deployment.

## Features

* Pre-configured MySQL database for tree data storage
* Docker-based setup for consistent deployment
* Initialization scripts to create the required schema and tables
* Secure default credentials

## Prerequisites

* Docker and Docker Compose

## Running the Database

### Using Docker Compose (Recommended)

```bash
git clone https://github.com/desiderati/demos.git

cd demos/demo-treasy-tree-editor/demo-treasy-tree-editor-mysql

docker-compose up -d && docker-compose logs -f
```

This command will:

1. Start a MySQL container with the required configuration
2. Initialize the database with the necessary schema and tables
3. Display the logs for monitoring

## Database Connection Information

Use these details to connect to the database from your application or database client:

```
URL Connection = jdbc:mysql://localhost:3306/treasy?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8
User = treasy
Password = treasy
Database Name = treasy
Port = 3306 (default MySQL port)
```

## Verifying the Database Setup

To verify that the database is running correctly and the user was created successfully:

```bash
docker exec -it mysql_db mysql -u treasy -p
```

When prompted, enter the password: `treasy`

You should now be connected to the MySQL console. You can run SQL commands to verify the database structure:

```mysql
USE treasy;
SHOW TABLES;
```

## Stopping the Database

To stop the database container:

```bash
docker-compose down
```

## Data Persistence

The database data is persisted in a Docker volume. To completely remove the database including all data:

```bash
docker-compose down -v
```

## Troubleshooting

If you encounter connection issues:

1. Ensure Docker is running
2. Check if the container is running with `docker ps`
3. View container logs with `docker-compose logs -f`
4. Verify that port 3306 is not being used by another service
