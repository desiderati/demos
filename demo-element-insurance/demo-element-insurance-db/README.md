# Element Insurance Database Module

This module provides the HSQLDB (HyperSQL Database) configuration for the Element Insurance application using Docker.

## Overview

The database module sets up a HSQLDB instance using Docker and Docker Compose. It includes:

- HSQLDB database configuration
- Initial schema creation
- Sample data population (if applicable)

## Prerequisites

* Docker v27
* Docker Compose v2.28.1

## Database Configuration

The module uses Docker Compose to configure and run a HSQLDB database with the following settings:

- Database name: hsqldb
- Default port: 9001
- Default credentials (configurable in the Docker Compose file)

## Running the Database

To start the database container:

```shell
docker compose up -d && docker compose logs -f
```

This command:

1. Starts the HSQLDB container in detached mode (-d)
2. Shows the container logs in follow mode (-f)

## Stopping the Database

To stop the database container:

```shell
docker compose down
```

## Data Persistence

The database data is persisted in a Docker volume, so your data will not be lost when the container is stopped or
restarted.

## Connecting to the Database

You can connect to the database using any HSQLDB client with the following connection details:

- Host: localhost
- Port: 9001
- Database: hsqldb
- Username: sa (as configured in Docker Compose)
- Password: (empty by default, as configured in Docker Compose)

## Troubleshooting

If you encounter issues with the database container:

1. Check the container logs: `docker compose logs`
2. Ensure the required ports are available
3. Verify Docker is running correctly
