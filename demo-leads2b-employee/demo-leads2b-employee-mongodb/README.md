# Leads2B Employee MongoDB Module

This module provides the database layer for the Leads2B Employee Management System. It sets up a MongoDB database for
storing employee data and includes Mongo Express, a web-based MongoDB administration interface.

## Overview

The module consists of two main containers:

1. **MongoDB**: NoSQL database running on port 27017
    - Stores all employee data
    - Used by the backend server for CRUD operations

2. **Mongo Express**: Web-based MongoDB admin interface running on port 8081
    - Allows direct database management and exploration
    - Useful for development and debugging

## Prerequisites

* Docker and Docker Compose

## Configuration

The MongoDB configuration is defined in the `docker-compose.yml` file, which:

- Sets up MongoDB 8
- Configures data persistence using a local volume
- Creates a network for communication between services
- Sets up Mongo Express for database administration

## Running the Database

To start the MongoDB database and admin interface:

```bash
docker-compose up -d && docker-compose logs -f
```

This command:

- Starts both MongoDB and Mongo Express in detached mode
- Shows the logs from both containers

## Accessing the Database

### Direct MongoDB Access

The MongoDB database is accessible at:

- Host: localhost
- Port: 27017
- No authentication required (development setup)

### MongoDB Admin Interface

The Mongo Express web interface is accessible at:

```
http://localhost:8081
```

Through this interface, you can:

- View, create, and edit databases and collections
- Import and export data
- Run MongoDB queries
- Monitor database performance

#### Configuration

Environment variables are passed to the run command for configuring a mongo-express container.

| Name                            | Default | Description                           |
|---------------------------------|---------|---------------------------------------|
| ME_CONFIG_BASICAUTH_USERNAME    | ''      | mongo-express web username            |
| ME_CONFIG_BASICAUTH_PASSWORD    | ''      | mongo-express web password            |
| ME_CONFIG_MONGODB_ENABLE_ADMIN  | 'true'  | Enable admin access to all databases. |
| ME_CONFIG_MONGODB_ADMINUSERNAME | ''      | MongoDB admin username                |
| ME_CONFIG_MONGODB_ADMINPASSWORD | ''      | MongoDB admin password                |
| ME_CONFIG_MONGODB_PORT          | 27017   | MongoDB port                          |
| ME_CONFIG_MONGODB_SERVER        | 'mongo' | MongoDB container name.               |

Read [here](https://hub.docker.com/_/mongo-express) for more information.

The following is only needed if ME_CONFIG_MONGODB_ENABLE_ADMIN is "false"

| Name                            | Default | Description       |
|---------------------------------|---------|-------------------|
| ME_CONFIG_MONGODB_AUTH_DATABASE | 'db'    | Database name     |
| ME_CONFIG_MONGODB_AUTH_USERNAME | 'admin' | Database username |
| ME_CONFIG_MONGODB_AUTH_PASSWORD | 'pass'  | Database password |

## Stopping the Database

To stop the MongoDB database and admin interface:

```bash
docker-compose down
```

## Data Persistence

Database data is persisted in the `.mongodb/data` directory. This ensures that your data remains intact between
container restarts.

## Integration with Other Modules

This module is a prerequisite for running the server module. The server connects to this MongoDB instance to store and
retrieve employee data.
